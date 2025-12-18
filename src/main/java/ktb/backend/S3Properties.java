package ktb.backend;

public record S3Properties(
        String accessKey,
        String secretKey,
        String bucket
) {

}
