package ktb.backend.dto;

public record AiAnalysisResult(
        double accuracy,
        byte[] leafletImage
) {

}
