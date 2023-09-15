package servlet.facade.impl;

import servlet.dto.SculptorRequestDto;
import servlet.dto.SculptorResponseDto;
import servlet.entity.Sculptor;
import servlet.facade.SculptorFacade;
import servlet.service.SculptorService;
import servlet.service.impl.SculptorServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SculptorFacadeImpl implements SculptorFacade {

    private final SculptorService sculptorService = new SculptorServiceImpl();

    @Override
    public void create(SculptorRequestDto sculptorRequestDto) {
        Sculptor sculptor = new Sculptor();
        sculptor.setFirstName(sculptorRequestDto.firstName());
        sculptor.setLastName(sculptorRequestDto.lastName());
        sculptor.setAge(sculptorRequestDto.age());

        sculptorService.create(sculptor);
    }

    @Override
    public void update(SculptorRequestDto sculptorRequestDto, Long id) {
        Sculptor sculptor = sculptorService.findById(id);
        if (sculptor != null) {
            sculptor.setFirstName(sculptorRequestDto.firstName());
            sculptor.setLastName(sculptorRequestDto.lastName());
            sculptor.setAge(sculptorRequestDto.age());

            sculptorService.update(sculptor);
        }
    }

    @Override
    public void delete(Long id) {
        sculptorService.delete(id);
    }

    @Override
    public SculptorResponseDto findById(Long id) {
        Sculptor sculptor = sculptorService.findById(id);
        if (sculptor != null) {
            return new SculptorResponseDto(
                    sculptor.getId(),
                    sculptor.getFirstName(),
                    sculptor.getLastName(),
                    sculptor.getAge()
            );
        }
        return null;
    }

    @Override
    public List<SculptorResponseDto> findAll() {
        List<Sculptor> sculptors = sculptorService.findAll();
        List<SculptorResponseDto> responseDtos = new ArrayList<>();
        for (Sculptor sculptor : sculptors) {
            responseDtos.add(new SculptorResponseDto(
                    sculptor.getId(),
                    sculptor.getFirstName(),
                    sculptor.getLastName(),
                    sculptor.getAge()
            ));
        }
        return responseDtos;
    }
}
