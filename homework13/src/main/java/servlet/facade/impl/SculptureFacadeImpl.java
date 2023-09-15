package servlet.facade.impl;

import servlet.dto.SculptureRequestDto;
import servlet.dto.SculptureResponseDto;
import servlet.entity.Sculpture;
import servlet.facade.SculptureFacade;
import servlet.service.SculptureService;
import servlet.service.impl.SculptureServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SculptureFacadeImpl implements SculptureFacade {

    SculptureService sculptureService = new SculptureServiceImpl();

    @Override
    public void create(SculptureRequestDto sculptureRequestDto) {
        Sculpture sculpture = new Sculpture();
        sculpture.setName(sculptureRequestDto.name());
        sculpture.setMaterialOfSculpture(sculptureRequestDto.materialOfSculpture());
        sculpture.setArtisticStyle(sculptureRequestDto.artisticStyle());
        sculpture.setYearOfPublishing(sculptureRequestDto.yearOfPublishing());

        sculptureService.create(sculpture);
    }

    @Override
    public void update(SculptureRequestDto sculptureRequestDto, Long id) {
        Sculpture sculpture = sculptureService.findById(id);
        if (sculpture != null) {
            sculpture.setName(sculptureRequestDto.name());
            sculpture.setMaterialOfSculpture(sculptureRequestDto.materialOfSculpture());
            sculpture.setArtisticStyle(sculptureRequestDto.artisticStyle());
            sculpture.setYearOfPublishing(sculptureRequestDto.yearOfPublishing());

            sculptureService.update(sculpture);
        }
    }

    @Override
    public void delete(Long id) {
        sculptureService.delete(id);
    }

    @Override
    public SculptureResponseDto findById(Long id) {
        Sculpture sculpture = sculptureService.findById(id);
        if (sculpture != null) {
            return new SculptureResponseDto(
                    sculpture.getId(),
                    sculpture.getName(),
                    sculpture.getMaterialOfSculpture(),
                    sculpture.getArtisticStyle(),
                    sculpture.getYearOfPublishing()
            );
        }
        return null;
    }

    @Override
    public List<SculptureResponseDto> findAll() {
        List<Sculpture> sculptures = sculptureService.findAll();
        List<SculptureResponseDto> responseDtos = new ArrayList<>();
        for (Sculpture sculpture : sculptures) {
            responseDtos.add(new SculptureResponseDto(
                    sculpture.getId(),
                    sculpture.getName(),
                    sculpture.getMaterialOfSculpture(),
                    sculpture.getArtisticStyle(),
                    sculpture.getYearOfPublishing()
            ));
        }
        return responseDtos;
    }
}

