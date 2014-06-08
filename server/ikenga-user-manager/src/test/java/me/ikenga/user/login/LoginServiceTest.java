package me.ikenga.user.login;

import me.ikenga.BaseTest;
import me.ikenga.user.User;
import me.ikenga.user.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;

public class LoginServiceTest extends BaseTest {

    @Autowired
    @InjectMocks
    private LoginService loginService;

    @Autowired
    @Mock
    private UserRepository userRepository;

    @Test
    public void testLoginSuccess() {
        // given
        LoginCredentials credentials = createLoginCredentials();

        when(userRepository.findByUsernameOrEmailAndHashedPassword(anyString(), anyString())).thenReturn(createLoggedInUser());

        // when
        LoginData loginData = loginService.login(credentials);

        // then
        Assert.assertEquals("tom", loginData.getUsername());
    }

    @Test(expected = InvalidLoginCredentialsException.class)
    public void testLoginFailure() {
        // given
        LoginCredentials credentials = createLoginCredentials();
        when(userRepository.findByUsernameOrEmailAndHashedPassword(anyString(), anyString())).thenReturn(null);

        // when
        loginService.login(credentials);

        // then expected exception
    }

    private LoginCredentials createLoginCredentials() {
        LoginCredentials credentials = new LoginCredentials();
        credentials.setUsernameOrEmail("tom");
        credentials.setPassword("pw");
        return credentials;
    }

    private User createLoggedInUser() {
        User user = new User();
        user.setEmail("tom@ikenga.me");
        user.setHashedPassword("123");
        user.setUsername("tom");
        user.setId(1l);
        return user;
    }


}
