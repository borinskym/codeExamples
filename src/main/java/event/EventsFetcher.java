package event;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface EventsFetcher {
    Event getActiveEventByGameId(String gameId);


    class EventsFetcherException extends RuntimeException {
        public EventsFetcherException(String message) {
            super(message);
        }

        public EventsFetcherException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    class EventNotFoundException extends RuntimeException {

        public EventNotFoundException(String message) {
            super(message);
        }
    }
}
