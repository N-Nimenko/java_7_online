package module.facade.impl;

import module.dto.PiggyBankRequestDto;
import module.dto.PiggyBankResponseDto;
import module.entity.PiggyBank;
import module.facade.PiggyBankFacade;
import module.service.PiggyBankService;
import module.service.impl.PiggyBankServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class PiggyBankFacadeImpl implements PiggyBankFacade {

    private final PiggyBankService piggyBankService = new PiggyBankServiceImpl();

    @Override
    public void create(PiggyBankRequestDto piggyBankRequestDto) {
        PiggyBank piggyBank = new PiggyBank();
        piggyBank.setBalance(piggyBankRequestDto.balance());
        piggyBankService.create(piggyBank);
    }

    @Override
    public void update(PiggyBankRequestDto piggyBankRequestDto, Long id) {
        PiggyBank piggyBank = piggyBankService.findById(id);
        if (piggyBank != null) {
            piggyBank.setBalance(piggyBankRequestDto.balance());
            piggyBankService.update(piggyBank);
        }
    }

    @Override
    public void delete(Long id) {
        piggyBankService.delete(id);
    }

    @Override
    public PiggyBankResponseDto findById(Long id) {
        PiggyBank piggyBank = piggyBankService.findById(id);
        if (piggyBank != null) {
            return new PiggyBankResponseDto(piggyBank.getId(), piggyBank.getBalance());
        }
        return null;
    }

    @Override
    public List<PiggyBankResponseDto> findAll() {
        List<PiggyBank> piggyBanks = piggyBankService.findAll();
        List<PiggyBankResponseDto> responseDtos = new ArrayList<>();
        for (PiggyBank piggyBank : piggyBanks) {
            responseDtos.add(new PiggyBankResponseDto(piggyBank.getId(), piggyBank.getBalance()));
        }
        return responseDtos;
    }
}
