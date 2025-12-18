package ktb.backend.dto.response;

import java.util.List;

public record ImageUrlResponse(
        List<String> urls
) {
    public static ImageUrlResponse from(List<String> urls) {
        return new ImageUrlResponse(urls);
    }
}
