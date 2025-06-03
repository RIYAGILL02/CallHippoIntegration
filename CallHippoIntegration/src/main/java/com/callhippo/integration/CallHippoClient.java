package com.callhippo.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import com.callhippo.integration.CallLogResponse;

@Component
public class CallHippoClient {

    @Value("${callhippo.api.key}")
    private String apiKey;

    @Value("${callhippo.api.baseurl}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    // Fetch account information
    public ResponseEntity<String> getAccountInfo() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("apiToken", apiKey);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            String url = "https://web.callhippo.com/v1/user/list";
            return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Fetch call logs with query parameters
    public ResponseEntity<String> getCallLogs(String fromDate, String toDate, String msisdn) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apiToken", apiKey);

        // Prepare JSON body
        String requestJson = String.format(
                "{\"skip\": \"0\", \"limit\": \"100\", \"startDate\": \"%s\", \"endDate\": \"%s\"}",
                fromDate, toDate);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        String url = baseUrl + "/activityfeed";

        return restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
    }

    // Fetch and parse call logs into objects
    public List<CallLogResponse> getCallLogsAsObjects(String fromDate, String toDate, String msisdn) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(baseUrl + "/calls")
                .queryParam("from_date", fromDate)
                .queryParam("to_date", toDate);

        if (msisdn != null && !msisdn.isEmpty()) {
            uriBuilder.queryParam("msisdn", msisdn);
        }

        String url = uriBuilder.toUriString();
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = getCallLogs(fromDate, toDate, msisdn);
        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode dataArray = root.path("data"); // assuming call logs are inside "data"

            return mapper.readValue(
                    dataArray.toString(),
                    mapper.getTypeFactory().constructCollectionType(List.class, CallLogResponse.class));
        } else {
            throw new RuntimeException("Failed to fetch call logs");
        }
    }
}
