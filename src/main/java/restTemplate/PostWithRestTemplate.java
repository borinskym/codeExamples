package restTemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class PostWithRestTemplate {

    public void postRestTemplate(RestTemplate restTemplate){
        restTemplate.exchange("PATH",
                HttpMethod.POST,
                new HttpEntity<>("BODY", createDefaultHeaders()), String.class);
    }

    private static HttpHeaders createDefaultHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
