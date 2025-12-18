package ktb.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevController {
    @Operation(summary = "test용 API")
    @PostMapping("/dev/test")
    public String test() {
        return "test sucecss";
    }
}
