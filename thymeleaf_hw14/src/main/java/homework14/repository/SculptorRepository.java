package homework14.repository;

import homework14.entity.Sculptor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SculptorRepository extends BaseRepository<Sculptor>{
    @Query("select e from Sculptor e where e.id not in (select e.id from Sculptor e join e.sculptures ed where ed.id = :sculptureId)")
    List<Sculptor> findAllSculptorsBySculpture(@Param("sculptureId") Long sculptureId);
    @Query(value = "select e from Sculptor e join e.sculptures ed where ed.id = :sculptureId")
    List<Sculptor> findAllSculptorsByExcludeSculpture(@Param("sculptureId") Long sculptureId);
}
