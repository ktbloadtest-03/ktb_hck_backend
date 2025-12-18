package ktb.backend.repository;

import ktb.backend.entity.AiGeneratedImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AiGeneratedImageRepository extends JpaRepository<AiGeneratedImage, Long> {
}
