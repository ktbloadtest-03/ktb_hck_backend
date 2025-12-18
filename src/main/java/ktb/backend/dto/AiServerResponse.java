package ktb.backend.dto;

public record AiServerResponse(
        double accuracy,
        String leafletImageBase64
) {
}
