package me.ikenga.user.registration;

import me.ikenga.user.PasswordHasher;
import me.ikenga.persistence.entity.UserEntity;
import me.ikenga.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

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
    public void register(RegistrationData registrationData) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {

        if (usernameExists(registrationData.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if (emailExists(registrationData.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        UserEntity user = mapRegistrationData(registrationData);
        userRepository.save(user);

    }

    private UserEntity mapRegistrationData(RegistrationData registrationData) {
        UserEntity user = new UserEntity(registrationData.getUsername());
        user.setHashedPassword(passwordHasher.hashPassword(registrationData.getPassword()));
        user.setEmail(registrationData.getEmail());
        return user;
    }

    private boolean usernameExists(String username) {
        UserEntity user = userRepository.findByUsername(username);
        return user != null;
    }

    private boolean emailExists(String email) {
        UserEntity user = userRepository.findByEmail(email);
        return user != null;
    }
}
