package com.doctorAppointmentBookingSystem.entity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by Edi on 04-May-17.
 */
@RunWith(SpringRunner.class)
public class UserTest {
    public static final String EXPECTED_ROLE_NAME = "ROLE_USER";

    private User user;

    @Mock
    private Role role;

    @Before
    public void setUp() throws Exception {
        //Arrange
        this.user = new User();
        when(this.role.getAuthority()).thenReturn(EXPECTED_ROLE_NAME);
    }

    @Test
    public void addRoleWhenUserRoleGiven_ShouldReturnCorrectRoleName() throws Exception {
        //Act
        this.user.addRole(this.role);
        //Assert
        String actualRoleName = this.user
                .getAuthorities()
                .iterator()
                .next()
                .getAuthority();
        assertEquals(EXPECTED_ROLE_NAME, actualRoleName);
    }
}