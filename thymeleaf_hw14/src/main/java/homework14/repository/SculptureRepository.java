package homework14.repository;

import homework14.entity.Sculpture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SculptureRepository extends BaseRepository<Sculpture>{
    @Query(value = "select e from Sculpture e join e.sculptors ed where ed.id = :sculptorId")
    List<Sculpture> findAllSculpturesBySculptor(@Param("sculptorId") Long sculptorId);
}
