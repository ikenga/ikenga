package me.ikenga.persistence.repository;


import me.ikenga.persistence.entity.MetricEntity;
import me.ikenga.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends
        JpaRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

    @Query("select u.username from UserEntity u " +
            "where u.team = :team")
    List<String> findUsernamesByTeam(@Param("team") String team);

    UserEntity findByEmail(String email);

    @Query("select distinct u.team from UserEntity u")
    List<String> findDistinctTeams();

    @Query("select u from UserEntity u "
            + "where (u.username = :usernameOrEmail "
            + "or u.email = :usernameOrEmail) "
            + "and u.hashedPassword = :hashedPassword")
    UserEntity findByUsernameOrEmailAndHashedPassword(@Param("usernameOrEmail") String usernameOrEmail, @Param("hashedPassword") String hashedPassword);
}
