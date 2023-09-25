package homework14.facade.impl;

import homework14.dto.SculptureDto;
import homework14.entity.Sculptor;
import homework14.entity.Sculpture;
import homework14.facade.SculptureFacade;
import homework14.service.SculptorService;
import homework14.service.SculptureService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class SculptureFacadeImpl implements SculptureFacade {

    @Autowired
    private SculptorService sculptorService;

    @Autowired
    private SculptureService sculptureService;

    @Override
    public void create(SculptureDto sculptureDto) {
        Sculpture sculpture = new Sculpture();
        sculpture.setName(sculptureDto.getName());
        sculpture.setMaterialOfSculpture(sculptureDto.getMaterialOfSculpture());
        sculpture.setArtisticStyle(sculptureDto.getArtisticStyle());
        sculpture.setYearOfPublishing(sculptureDto.getYearOfPublishing());

        sculptureService.create(sculpture);
    }

    @Override
    public void update(Long id, SculptureDto sculptureDto) {
        Sculpture sculpture = sculptureService.findById(id);
        sculpture.setName(sculptureDto.getName());
        sculpture.setMaterialOfSculpture(sculptureDto.getMaterialOfSculpture());
        sculpture.setArtisticStyle(sculptureDto.getArtisticStyle());
        sculpture.setYearOfPublishing(sculptureDto.getYearOfPublishing());

        sculptureService.update(sculpture);
    }

    @Override
    public void delete(Long id) {
        sculptureService.delete(id);
    }

    @Override
    public SculptureDto findById(Long id) {
        return new SculptureDto(sculptureService.findById(id));
    }

    @Override
    public Collection<SculptureDto> findAll() {
        return sculptureService.findAll().stream().map(SculptureDto::new).toList();
    }

    @Override
    public Collection<SculptureDto> findBySculptor(Long id) {
        Sculptor sculptor = sculptorService.findById(id);
        Collection<Sculpture> sculptures = Collections.emptyList();
        if (CollectionUtils.isNotEmpty(sculptor.getSculptures())) {
            sculptures = sculptor.getSculptures();
        }
        return sculptures.stream().map(SculptureDto::new).toList();
    }

    @Override
    public List<SculptureDto> findAllSculpturesBySculptor(Long sculptorId) {
        return sculptureService.findAllSculpturesBySculptor(sculptorId).stream().map(SculptureDto::new).toList();
    }

}

