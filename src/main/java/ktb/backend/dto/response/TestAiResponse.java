package ktb.backend.dto.response;

public record TestAiResponse(
        String status,
        Long registered_id,
        Long count,
        String description
) {
}
