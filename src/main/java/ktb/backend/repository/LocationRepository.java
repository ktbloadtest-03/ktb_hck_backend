package ktb.backend.repository;

import ktb.backend.dto.LocationCoordinatesProjection;
import ktb.backend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

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
    @Query(value = """
        SELECT
            r.id AS reportId,
            ST_Y(l.coordinate) AS latitude,
            ST_X(l.coordinate) AS longitude
        FROM report r
        JOIN location l ON r.location_id = l.id
        WHERE r.id IN :reportIds
        """, nativeQuery = true)
    List<LocationCoordinatesProjection> findCoordinatesByReportIds(
            @Param("reportIds") List<Long> reportIds
    );
}
