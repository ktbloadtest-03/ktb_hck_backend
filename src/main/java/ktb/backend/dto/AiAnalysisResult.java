package ktb.backend.dto;

import java.util.Base64;

public record AiAnalysisResult(
        double accuracy,
        byte[] leafletImage
) {

    public static AiAnalysisResult from(AiServerResponse aiServerResponse) {
        return new AiAnalysisResult(aiServerResponse.accuracy(), Base64.getDecoder().decode(aiServerResponse.leafletImageBase64()));
    }
}
