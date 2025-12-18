package ktb.backend.service;

import ktb.backend.S3Properties;
import ktb.backend.dto.response.ImageUrlResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class S3Service {
    private static final String PET_IMAGE_FOLDER = "pet-images";
    private static final String AI_GENERATED_IMAGE_FOLDER = "ai-generated-images";

    private final S3Properties s3Properties;
    private final S3Client s3Client;
    private final S3Presigner preSigner;

    public void uploadPetImage(Long imageId, MultipartFile file) {
        try {
            String key = buildKey(PET_IMAGE_FOLDER, imageId);

            PutObjectRequest request = makePutObjectRequest(key, file);

            s3Client.putObject(request, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void uploadAiGeneratedImage(Long imageId, byte[] bytes) {
        String key = buildKey(AI_GENERATED_IMAGE_FOLDER, imageId);

        PutObjectRequest request = makePutBytesRequest(key);

        s3Client.putObject(request, RequestBody.fromBytes(bytes));
    }

    public List<ImageUrlResponse> getPresignedUrls(List<Long> imageEntityIds) {

    }

    private String buildKey(String folder, Long imageId) {
        return String.join("/", folder, imageId.toString());
    }

    private PutObjectRequest makePutObjectRequest(String key, MultipartFile file) {
        return PutObjectRequest.builder()
                .bucket(s3Properties.bucket())
                .key(key)
                .contentType(file.getContentType())
                .build();
    }

    private PutObjectRequest makePutBytesRequest(String key) {
        return PutObjectRequest.builder()
                .bucket(s3Properties.bucket())
                .key(key)
                .contentType("image/jpeg")
                .build();
    }

    private String getPreSignedUrl(String folder, String filename) {
        return preSigner
                .presignGetObject(getObjectPresignRequest(folder, filename))
                .url()
                .toString();
    }

    private GetObjectPresignRequest getObjectPresignRequest(String folder, String filename) {
        return GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(1))
                .getObjectRequest(objectRequest ->
                        objectRequest
                                .bucket(s3Properties.bucket())
                                .key(String.join("/", folder, filename)))
                .build();
    }
}
