package me.ikenga.user;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends
        CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    User findByUsernameAndHashedPassword(String username, String hashedPassword);

}
