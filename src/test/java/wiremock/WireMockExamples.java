package wiremock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockExamples {
    private WireMockServer mockServer = new WireMockServer(8096);

    private void stubPost_returnBody(String path){
        mockServer.stubFor(post(path).willReturn(aResponse()
                .withBody(responseBody())));
    }

    private void badResponse(String path){
        mockServer.stubFor(post(path).willReturn(aResponse().withStatus(500)));
    }

    public void verifyBodyIs(String url, String body){
        mockServer.verify(postRequestedFor(urlEqualTo(url)).withRequestBody(equalTo(body)));
    }

    private String responseBody() {
        return new Gson().toJson(ImmutableMap.of(
                "score", 0.2,
                "shouldBePublished", true,
                "recommendationStrength", 0.65,
                "simulatedPublishAdvice", true

        ));
    }
}
