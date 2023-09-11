package module.facade.impl;

import module.dto.AccountRequestDto;
import module.dto.AccountResponseDto;
import module.entity.Account;
import module.facade.AccountFacade;
import module.service.AccountService;
import module.service.impl.AccountServiceImpl;


import java.util.ArrayList;
import java.util.List;

public class AccountFacadeImpl implements AccountFacade {
    private final AccountService accountService = new AccountServiceImpl();

    @Override
    public void create(AccountRequestDto accountRequestDto){
        Account account = new Account();
        account.setAccountNumber(accountRequestDto.accountNumber());
        account.setBalance(accountRequestDto.balance());
        accountService.create(account);
    }

    @Override
    public void update(AccountRequestDto accountRequestDto, Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            account.setAccountNumber(accountRequestDto.accountNumber());
            accountService.update(account);
        }
    }

    @Override
    public void delete(Long id) {
        accountService.delete(id);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        Account account = accountService.findById(id);
        if (account != null) {
            return new AccountResponseDto(account.getId(), account.getAccountNumber(), account.getBalance());
        }
        return null;
    }

    @Override
    public List<AccountResponseDto> findAll() {
        List<Account> accounts = accountService.findAll();
        List<AccountResponseDto> responseDtos = new ArrayList<>();
        for (Account account : accounts) {
            responseDtos.add(new AccountResponseDto(account.getId(), account.getAccountNumber(), account.getBalance()));
        }
        return responseDtos;
    }
}
