package ktb.backend.service;

import ktb.backend.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Properties s3Properties;
    private final S3Presigner preSigner;

    private String getPreSignedGetUrl(String folder, String filename) {
        return preSigner
                .presignGetObject(getObjectPresignRequest(folder, filename))
                .url()
                .toString();
    }

    private String getPresignedPutUrl(String folder, String filename) {
        return preSigner
                .presignPutObject(putObjectPresignRequest(folder, filename))
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

    private PutObjectPresignRequest putObjectPresignRequest(String folder, String filename) {
        return PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(1))
                .putObjectRequest(objectRequest ->
                        objectRequest
                                .bucket(s3Properties.bucket())
                                .key(String.join("/", folder, filename))
                )
                .build();
    }
}
