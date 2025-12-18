package ktb.backend.dto;

public record AiScoreResponse(
    Long id,
    double score,
    double visual_score,
    double text_score
) {
}
