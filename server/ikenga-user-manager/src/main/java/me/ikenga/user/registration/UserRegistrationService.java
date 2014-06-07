package me.ikenga.user.registration;

import me.ikenga.user.PasswordHasher;
import me.ikenga.user.User;
import me.ikenga.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHasher passwordHasher;

    /**
     * Tries to register the given User in the database.
     *
     * @param registrationData object containing all data needed for the user registration.
     * @throws me.ikenga.user.registration.UsernameAlreadyExistsException if there is another user in the database with the same username.
     * @throws me.ikenga.user.registration.EmailAlreadyExistsException    if there is another user in the database with the same email address.
     */
    public void register(UserRegistrationData registrationData) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {

        if (usernameExists(registrationData.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (emailExists(registrationData.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        User user = mapRegistrationData(registrationData);
        userRepository.save(user);

    }

    private User mapRegistrationData(UserRegistrationData registrationData) {
        User user = new User();
        user.setHashedPassword(passwordHasher.hashPassword(registrationData.getPassword()));
        user.setUsername(registrationData.getUsername());
        user.setEmail(registrationData.getEmail());
        return user;
    }

    private boolean usernameExists(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    private boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
