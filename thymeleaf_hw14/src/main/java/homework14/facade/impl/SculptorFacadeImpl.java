package homework14.facade.impl;

import homework14.dto.SculptorDto;
import homework14.dto.SculptureDto;
import homework14.entity.Sculptor;
import homework14.entity.Sculpture;
import homework14.facade.SculptorFacade;
import homework14.service.SculptorService;
import homework14.service.SculptureService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SculptorFacadeImpl implements SculptorFacade {

    @Autowired
    private SculptorService sculptorService;

    @Autowired
    private SculptureService sculptureService;

    @Override
    public void create(SculptorDto sculptorDto) {
        Sculptor sculptor = new Sculptor();
        sculptor.setFirstName(sculptorDto.getFirstName());
        sculptor.setLastName(sculptorDto.getLastName());
        sculptor.setAge(sculptorDto.getAge());

        sculptorService.create(sculptor);
    }

    @Override
    public void update(Long id, SculptorDto sculptorDto) {
        Sculptor sculptor = sculptorService.findById(id);
        sculptor.setFirstName(sculptorDto.getFirstName());
        sculptor.setLastName(sculptorDto.getLastName());
        sculptor.setAge(sculptorDto.getAge());

        sculptorService.update(sculptor);
    }

    @Override
    public void delete(Long id) {
        sculptorService.delete(id);
    }

    @Override
    public SculptorDto findById(Long id) {
        return new SculptorDto(sculptorService.findById(id));
    }

    @Override
    public Collection<SculptorDto> findAll() {
        return sculptorService.findAll().stream().map(SculptorDto::new).toList();
    }

    @Override
    public Collection<SculptorDto> findBySculpture(Long id) {
        Sculpture sculpture = sculptureService.findById(id);
        Collection<Sculptor> sculptors = Collections.emptyList();
        if (CollectionUtils.isNotEmpty(sculpture.getSculptors())) {
            sculptors = sculpture.getSculptors();
        }
        return sculptors.stream().map(SculptorDto::new).toList();
    }

    @Override
    public List<SculptorDto> findAllSculptorsByExcludeSculpture(Long sculptureId) {
        return sculptorService.findAllSculptorsByExcludeSculpture(sculptureId).stream().map(SculptorDto::new).toList();
    }

    @Override
    public List<SculptorDto> findAllSculptorsBySculpture(Long sculptureId) {
        return sculptorService.findAllSculptorsBySculpture(sculptureId).stream().map(SculptorDto::new).toList();
    }
}
