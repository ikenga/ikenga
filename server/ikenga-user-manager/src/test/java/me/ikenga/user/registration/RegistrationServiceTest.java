package me.ikenga.user.registration;

import me.ikenga.BaseTest;
import me.ikenga.user.User;
import me.ikenga.user.UserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.*;

public class RegistrationServiceTest extends BaseTest {

    @Autowired
    @InjectMocks
    private RegistrationService registrationService;

    @Autowired
    @Mock
    private UserRepository userRepository;

    @Test
    public void testRegisterSuccess() {
        // given
        RegistrationData data = createDummyRegistrationData();
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
        RegistrationData data = createDummyRegistrationData();
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
        RegistrationData data = createDummyRegistrationData();
        when(userRepository.findByUsername(data.getUsername())).thenReturn(null);
        when(userRepository.findByEmail(data.getEmail())).thenReturn(new User());

        // when
        registrationService.register(data);

        // then
        // expected exception
    }

    private RegistrationData createDummyRegistrationData() {
        RegistrationData data = new RegistrationData();
        data.setUsername("tom");
        data.setEmail("tom@ikenga.me");
        data.setPassword("123");
        return data;
    }

}
