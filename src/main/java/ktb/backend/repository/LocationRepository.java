package ktb.backend.repository;

import ktb.backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Modifying
    @Query(
        value = """
              INSERT INTO location(id, coordinate, detail)
              VALUES (:reportId, POINT(:lng, :lat), :detail)
            """,
        nativeQuery = true
    )
    void saveLocation(
        @Param("reportId") Long reportId,
        @Param("lat") double lat,
        @Param("lng") double lng,
        @Param("detail") String detail
    );
}
