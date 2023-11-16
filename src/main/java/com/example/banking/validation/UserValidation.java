package com.example.banking.validation;
import com.example.banking.dto.UserCreateDto;
import com.example.banking.models.User;
import com.example.banking.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidation implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCreateDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreateDto userCreateDto = (UserCreateDto) target;

        User byLogin = userRepository.findByLogin(userCreateDto.getLogin());
        if(byLogin!=null){
            errors.rejectValue("login","EXIST","Login exist");
        }
    }
}
