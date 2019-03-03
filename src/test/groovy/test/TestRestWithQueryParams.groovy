package test

import groovyx.net.http.RESTClient

class TestRestWithQueryParams {
    RESTClient client = new RESTClient('http://localhost:8080')

    def getScores(eventId) {
        def response = client.get(path: "/v1/scores",
                query: [eventId: eventId],
                contentType: "application/json"

        )
        assert response.status == 200
        response.data
    }
}
