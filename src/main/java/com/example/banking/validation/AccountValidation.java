package com.example.banking.validation;
import com.example.banking.dto.UserCreateDto;
import com.example.banking.models.Account;
import com.example.banking.models.User;
import com.example.banking.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountValidation implements Validator {
    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        if(accountRepository.findByName(account.getName()).isPresent()){
            errors.rejectValue("name","not difference","this account exists");
        }
    }
}
