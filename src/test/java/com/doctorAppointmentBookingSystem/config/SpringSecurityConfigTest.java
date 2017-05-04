package com.doctorAppointmentBookingSystem.config;

import com.doctorAppointmentBookingSystem.controller.UserController;
import com.doctorAppointmentBookingSystem.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Edi on 04-May-17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class SpringSecurityConfigTest {
    public static final String USERNAME = "test@test.bg";
    public static final String WRONG_USERNAME = "wrong_username";
    public static final String PASSWORD = "123456";
    public static final String WRONG_PASSWORD = "wrongPassword";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testLoginWithValidParameters_ShouldLogin() throws Exception {
        this.mvc
                .perform(formLogin().user(USERNAME).password(PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(authenticated().withUsername(USERNAME));
    }

    @Test
    public void testLoginWithInvalidUsername_ShouldNotLogin() throws Exception {
        this.mvc
                .perform(formLogin().user(WRONG_USERNAME).password(PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(unauthenticated());
    }

    @Test
    public void testLoginWithInvalidPassword_ShouldNotLogin() throws Exception {
        this.mvc
                .perform(formLogin().user(USERNAME).password(WRONG_PASSWORD))
                .andExpect(status().is3xxRedirection())
                .andExpect(unauthenticated());
    }

    @Configuration
    static class TestSpringConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .inMemoryAuthentication()
                    .withUser(USERNAME)
                    .password(PASSWORD)
                    .roles("USER");
        }
    }
}