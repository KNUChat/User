package KNUChat.User.auth.application;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:application-oauth.yml")
public class OAuthService {

    @Value("${google.client-id}")
    private String CLIENT_ID;
    @Value("${google.client-secret}")
    private String CLIENT_SECRET;
    @Value("${google.redirect-uri}")
    private String REDIRECTION_URI;
    @Value("${google.token-uri}")
    private String TOKEN_URI;
    @Value("${google.resource-uri}")
    private String RESOURCE_URI;

    private final Environment env;
    private final RestTemplate restTemplate = new RestTemplate();

    public String getUserEmail(String code) {
        String accessToken = getAccessToken(code);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity entity = new HttpEntity(headers);

        String email = restTemplate.exchange(RESOURCE_URI, HttpMethod.GET, entity, JsonNode.class).getBody().get("email").asText();
        return email;
    }

    private String getAccessToken(String code) {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", CLIENT_ID);
        params.add("client_secret", CLIENT_SECRET);
        params.add("redirect_uri", REDIRECTION_URI);
        params.add("grant_type", "authorization_code");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity entity = new HttpEntity(params, headers);

        ResponseEntity<JsonNode> responseNode = restTemplate.exchange(TOKEN_URI, HttpMethod.POST, entity, JsonNode.class);
        JsonNode accessTokenNode = responseNode.getBody();

        return accessTokenNode.get("access_token").asText();
    }
}
