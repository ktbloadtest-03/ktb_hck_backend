package ktb.backend.service;

import ktb.backend.dto.AiAnalysisResult;
import ktb.backend.dto.AiServerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AiService {
    private final RestTemplate restTemplate;

    @Value("${ai.server.url}")
    private String analyzeUrl;

    public AiAnalysisResult analyze(List<MultipartFile> images, long id, String description) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        images.forEach(img -> body.add("files", img.getResource()));
        body.add("id", id);
        body.add("description", description);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<?> request = new HttpEntity<>(body, headers);

        ResponseEntity<AiServerResponse> response = restTemplate.postForEntity(analyzeUrl, request, AiServerResponse.class);

        AiServerResponse aiServerResponse = response.getBody();

        return AiAnalysisResult.from(Objects.requireNonNull(aiServerResponse));
    }
}
