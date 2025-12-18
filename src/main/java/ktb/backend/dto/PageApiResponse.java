package ktb.backend.dto;

import java.util.List;

public record PageApiResponse<T>(
        List<T> data,
        PageInfo pageInfo
) {
    public static <T> PageApiResponse<T> success(List<T> data, PageInfo pageInfo) {
        return new PageApiResponse<>(data, pageInfo);
    }
}
