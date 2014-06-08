package me.ikenga.user.registration;

import me.ikenga.BaseTest;
import me.ikenga.user.User;
import me.ikenga.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

public class UserRegistrationServiceTest extends BaseTest {

    @Autowired
    @InjectMocks
    private UserRegistrationService registrationService;

    @Autowired
    @Mock
    private UserRepository userRepository;

    @Test
    public void testRegisterSuccess() {
        // given
        UserRegistrationData data = createDummyRegistrationData();
        when(userRepository.findByUsername(data.getUsername())).thenReturn(null);
        when(userRepository.findByEmail(data.getEmail())).thenReturn(null);

        // when
        registrationService.register(data);

        // then
        verify(userRepository).save(any(User.class));
    }

    @Test(expected = UsernameAlreadyExistsException.class)
    public void testRegisterUsernameExists() {
        // given
        UserRegistrationData data = createDummyRegistrationData();
        when(userRepository.findByUsername(data.getUsername())).thenReturn(new User());
        when(userRepository.findByEmail(data.getEmail())).thenReturn(null);

        // when
        registrationService.register(data);

        // then
        // expected exception
    }

    @Test(expected = EmailAlreadyExistsException.class)
    public void testRegisterFailEmail() {
        // given
        UserRegistrationData data = createDummyRegistrationData();
        when(userRepository.findByUsername(data.getUsername())).thenReturn(null);
        when(userRepository.findByEmail(data.getEmail())).thenReturn(new User());

        // when
        registrationService.register(data);

        // then
        // expected exception
    }

    private UserRegistrationData createDummyRegistrationData() {
        UserRegistrationData data = new UserRegistrationData();
        data.setUsername("tom");
        data.setEmail("tom@ikenga.me");
        data.setPassword("123");
        return data;
    }

}
