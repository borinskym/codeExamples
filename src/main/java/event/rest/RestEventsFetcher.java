package event.rest;


import com.agt.mes.trigger.event.Event;
import com.agt.mes.trigger.event.EventResponse;
import com.agt.mes.trigger.event.EventsFetcher;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.agt.mes.trigger.config.CachingConfig.EVENTS_CACHE;
import static java.lang.String.format;

@Component
@Slf4j
public class RestEventsFetcher implements EventsFetcher {

    private final String hostName;
    private final RestTemplate restTemplate;

    @Autowired
    public RestEventsFetcher(@Value("${event-service.url}") String hostName, RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.hostName = hostName;
    }

    private List<Event> getEventsByGameId(String gameId) {
        Map<String, String> params = new HashMap<>();
        params.put("game", gameId);
        params.put("status", "active");
        return makeQuery(params);
    }

    @Override
    @Cacheable(EVENTS_CACHE)
    public Event getActiveEventByGameId(String gameId) {
        List<Event> activeEvents = getEventsByGameId(gameId);
        validateEventsExists(activeEvents, gameId);
        logUnexpectedNumberOfActiveEvents(gameId, activeEvents);
        return activeEvents.get(0);
    }

    private void validateEventsExists(List<Event> activeEvents, String gameId) {
        if(activeEvents.isEmpty()) {
            throw new EventNotFoundException("No active events found for: " + gameId);
        }
    }

    private void logUnexpectedNumberOfActiveEvents(String gameId, List<Event> activeEvents) {
        if (activeEvents.size() != 1) {
            log.warn("Found {} active events for game: {}", activeEvents.size() , gameId);
        }
    }

    private List<Event> makeQuery(Map<String, String> queryParams) {
        try {
            ResponseEntity<String> responseEntity = invokeRestClient(queryParams);
            verifyResponse(responseEntity, queryParams);
            return extractEventsFrom(responseEntity);
        } catch (RestClientException ex) {
            throw new EventsFetcherException(format("Failed extract event for %s", queryParams), ex);
        }
    }

    private ResponseEntity<String> invokeRestClient(Map<String, String> queryParams) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(hostName + "/api/v1/heedevents");
        for (Map.Entry<String, String> queryParam : queryParams.entrySet()){
            builder.queryParam(queryParam.getKey(), queryParam.getValue());
        }
        String url = builder.toUriString();
        log.info("Sending GET request to: " + url);
        return restTemplate.getForEntity(url, String.class);
    }

    private List<Event> extractEventsFrom(ResponseEntity<String> responseEntity) {
        return new Gson().fromJson(responseEntity.getBody(), EventResponse.class).getHeedEvents();
    }

    private void verifyResponse(ResponseEntity<String> responseEntity, Map<String, String> queryParams) {
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            String message = (responseEntity.getBody()) != null ? responseEntity.getBody() : " no message";
            throw new EventsFetcherException(
                    format("Failed extract event for %s with status code '%s' and server message '%s'",
                    queryParams, responseEntity.getStatusCode().toString(), message));
        }
    }

}
