package homework14.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import homework14.repository.SculptorRepository;
import homework14.entity.Sculptor;
import homework14.service.SculptorService;

import java.util.Collection;
import java.util.List;

@Service
public class SculptorServiceImpl implements SculptorService {

    @Autowired
    SculptorRepository sculptorRepository;

    @Override
    public void create(Sculptor entity) {
        sculptorRepository.save(entity);
    }

    @Override
    public void update(Sculptor entity) {
        checkExists(entity.getId());
        sculptorRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        checkExists(id);
        sculptorRepository.deleteById(id);
    }

    @Override
    public Sculptor findById(Long id) {
        checkExists(id);
        return sculptorRepository.findById(id).get();
    }

    @Override
    public Collection<Sculptor> findAll() {
        return sculptorRepository.findAll();
    }

    @Override
    public void checkExists(Long id) {
        if (!sculptorRepository.existsById(id)) {
            throw new RuntimeException("Sculpture not found");
        }
    }

    @Override
    public List<Sculptor> findAllSculptorsBySculpture(Long sculptureId) {
        return sculptorRepository.findAllSculptorsBySculpture(sculptureId);
    }

    @Override
    public List<Sculptor> findAllSculptorsByExcludeSculpture(Long sculptureId) {
        return sculptorRepository.findAllSculptorsByExcludeSculpture(sculptureId);
    }

}
