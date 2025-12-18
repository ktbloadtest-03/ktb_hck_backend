package ktb.backend.dto;

public record AiFlyerRequest(
    String location,
    String breed,
    String name,
    String contact,
    String notes
) {
}
