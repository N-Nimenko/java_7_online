package homework14.service.impl;

import homework14.dto.SculptureDto;
import homework14.entity.Sculptor;
import homework14.entity.Sculpture;
import homework14.repository.SculptureRepository;
import homework14.service.SculptureService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class SculptureServiceImpl implements SculptureService {

    @Autowired
    private SculptureRepository sculptureRepository;

    @Override
    public void create(Sculpture entity) {
        sculptureRepository.save(entity);
    }

    @Override
    public void update(Sculpture entity) {
        checkExists(entity.getId());
        sculptureRepository.save(entity);
    }


    @Override
    public void delete(Long id) {
        checkExists(id);
        sculptureRepository.deleteById(id);
    }

    @Override
    public Sculpture findById(Long id) {
        checkExists(id);
        return sculptureRepository.findById(id).get();
    }

    @Override
    public Collection<Sculpture> findAll() {
        return sculptureRepository.findAll();
    }

    @Override
    public void checkExists(Long id) {
        if (!sculptureRepository.existsById(id)) {
            throw new RuntimeException("Sculpture not found");
        }
    }

    @Override
    public List<Sculpture> findAllSculpturesBySculptor(Long sculptorId) {
        return sculptureRepository.findAllSculpturesBySculptor(sculptorId);
    }
}
