package spring;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RestControllerExample {

    @PostMapping("/v2/run")
    public ResponseEntity<String> runSimulation() {
        throw new RestValidationException("some exception");
    }

    public static class RestValidationException extends RuntimeException{
        public RestValidationException(String message) {
            super(message);
        }
    }
}
