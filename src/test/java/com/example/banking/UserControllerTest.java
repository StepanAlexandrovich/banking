package com.example.banking;

import com.example.banking.models.Account;
import com.example.banking.repositories.AccountRepository;
import com.example.banking.repositories.UserRepository;
import com.example.banking.services.impl.AccountServiceImpl;
import lombok.ToString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("user1")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountServiceImpl accountService;

    @Test
    void userAccountsTest() throws Exception {
        Account account = accountService.getById(18L);

        Mockito.when(accountRepository.findById(18L)).thenReturn(Optional.of(new Account(18L,44.0,null,null,null)));

        mockMvc.perform(MockMvcRequestBuilders.get("/user/account/{accountId}",18))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(model().attribute("account",account));
    }

    @Test
    public void testRedirect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/user/test_redirect"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/test_redirected"));
    }

    @Test
    void testPageTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/menu"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void accessDeniedTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"));
    }

    @Test
    public void correctLoginTest() throws Exception {
        mockMvc.perform(formLogin().user("user1").password("1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/menu"));
    }

}
