package ktb.backend.service;

import ktb.backend.dto.AiFlyerRequest;
import ktb.backend.dto.AiScoreResponse;
import ktb.backend.dto.AiServerResponse;
import ktb.backend.dto.request.MissingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiService {
    private final RestTemplate restTemplate;

    @Value("${ai.server.url}")
    private String analyzeUrl;

    public AiServerResponse analyze(List<MultipartFile> images, long id, String description) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        images.forEach(img -> body.add("files", img.getResource()));
        body.add("id", id);
        body.add("description", description);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<?> request = new HttpEntity<>(body, headers);

        ResponseEntity<AiServerResponse> response = restTemplate.postForEntity(analyzeUrl + "/register", request, AiServerResponse.class);

        return response.getBody();
    }

    public AiScoreResponse[] score(List<MultipartFile> images) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        images.forEach(img -> body.add("file", img.getResource()));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<?> request = new HttpEntity<>(body, headers);

        ResponseEntity<AiScoreResponse[]> response = restTemplate.postForEntity(analyzeUrl + "/search", request, AiScoreResponse[].class);
        return response.getBody();
    }

    public byte[] getFlyer(MultipartFile image, MissingRequest missingRequest) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("location", missingRequest.locationName() + " " + missingRequest.lostLocationDetail());
        body.add("breed", missingRequest.species());
        body.add("name", missingRequest.petName());
        body.add("contact", missingRequest.phoneNumber());
        body.add("notes", missingRequest.featureDetail());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<?> request = new HttpEntity<>(body, headers);

        ResponseEntity<byte[]> flyer = restTemplate.exchange(analyzeUrl + "/poster", HttpMethod.POST,request, byte[].class);
        return flyer.getBody();
    }
}
