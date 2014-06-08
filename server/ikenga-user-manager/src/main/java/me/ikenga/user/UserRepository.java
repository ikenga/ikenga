package me.ikenga.user;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends
        CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    @Query("select u from User u "
            + "where (u.username = :usernameOrEmail "
            + "or u.email = :usernameOrEmail) "
            + "and u.hashedPassword = :hashedPassword")
    User findByUsernameOrEmailAndHashedPassword(@Param("usernameOrEmail") String usernameOrEmail, @Param("hashedPassword") String hashedPassword);

}
