package me.ikenga.user.login;

import me.ikenga.user.PasswordHasher;
import me.ikenga.user.User;
import me.ikenga.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordHasher passwordHasher;

    /**
     * Checks the given credentials with the database. If the credentials are valid, returns a LoginData object
     * with the user's data. If not, an InvalidLoginCredentialsException is thrown.
     *
     * @param credentials the credentials the user provided for login.
     * @return a LoginData object containing the user's data to be used in the UI session.
     * @throws InvalidLoginCredentialsException if the given credentials are not valid.
     */
    public LoginData login(LoginCredentials credentials) throws InvalidLoginCredentialsException {
        String username = credentials.getUsernameOrEmail();
        String hashedPassword = passwordHasher.hashPassword(credentials.getPassword());
        User user = userRepository.findByUsernameOrEmailAndHashedPassword(username, hashedPassword);
        if (user == null) {
            throw new InvalidLoginCredentialsException();
        } else {
            LoginData loginData = new LoginData();
            loginData.setUsername(username);
            return loginData;
        }
    }

}
