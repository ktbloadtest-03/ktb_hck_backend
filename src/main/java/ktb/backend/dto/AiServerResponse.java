package ktb.backend.dto;

public record AiServerResponse(
        String status,
        Long registered_id,
        Long count,
        String description
) {
}
