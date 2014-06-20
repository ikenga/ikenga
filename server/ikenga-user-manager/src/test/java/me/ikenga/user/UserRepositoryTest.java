package me.ikenga.user;

import me.ikenga.BaseTest;
import me.ikenga.persistence.entity.UserEntity;
import me.ikenga.persistence.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends BaseTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        // given
        UserEntity user = createDummyUser();
        user.setUsername("tom");

        // when
        userRepository.save(user);

        // then
        UserEntity foundUser = userRepository.findByUsername("tom");
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void testFindByUsernameNull() {
        UserEntity foundUser = userRepository.findByUsername("tom");
        Assert.assertNull(foundUser);
    }

    @Test
    public void testFindByEmail() {
        // given
        UserEntity user = createDummyUser();
        user.setEmail("tom@ikenga.me");

        // when
        userRepository.save(user);

        // then
        UserEntity foundUser = userRepository.findByEmail("tom@ikenga.me");
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void testFindByEmailNull() {
        UserEntity foundUser = userRepository.findByEmail("tom@ikenga.me");
        Assert.assertNull(foundUser);
    }

    @Test
    public void testFindByUsernameAndHashedPassword() {
        // given
        UserEntity user = createDummyUser();
        user.setUsername("tom");
        user.setHashedPassword("pw");


        // when
        userRepository.save(user);

        // then
        UserEntity foundUser = userRepository.findByUsernameOrEmailAndHashedPassword("tom", "pw");
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void testFindByUsernameAndHashedPasswordNull() {
        UserEntity foundUser = userRepository.findByUsernameOrEmailAndHashedPassword("tom", "pw");
        Assert.assertNull(foundUser);
    }

    @Test
    public void testFindByEmailAndHashedPassword() {
        // given
        UserEntity user = createDummyUser();
        user.setEmail("tom@ikenga.me");
        user.setHashedPassword("pw");


        // when
        userRepository.save(user);

        // then
        UserEntity foundUser = userRepository.findByUsernameOrEmailAndHashedPassword("tom@ikenga.me", "pw");
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void testFindByEmailAndHashedPasswordNull() {
        UserEntity foundUser = userRepository.findByUsernameOrEmailAndHashedPassword("tom@ikenga.me", "pw");
        Assert.assertNull(foundUser);
    }

    private UserEntity createDummyUser() {
        UserEntity user = new UserEntity("tom");
        user.setEmail("tom@ikenga.me");
        user.setUsername("tom");
        user.setHashedPassword("123");
        return user;
    }
}