package ktb.backend.dto;

public record APIResponse<T>(
        boolean success,
        T data
) {
    public static <T> APIResponse<T> success(Boolean success, T data) {
        return new APIResponse<>(success, data);
    }
}
