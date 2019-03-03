package stub;

import org.rockm.blink.BlinkServer;

import java.io.IOException;

public class SimpleServerStub {
    private BlinkServer blink;
    String body;

    public void stub(String path) throws IOException {
        blink = new BlinkServer(8096);
        blink.contentType("application/json");

        blink.post(path, (request, response) ->{
            body = request.body();
            return "";
        }) ;
    }

    public String body(){
        return body;
    }

    public void stop(){
        blink.stop();
    }
}
