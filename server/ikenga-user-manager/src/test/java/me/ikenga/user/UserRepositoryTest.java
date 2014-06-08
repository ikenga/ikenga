package me.ikenga.user;

import me.ikenga.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRepositoryTest extends BaseTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        // given
        User user = createDummyUser();
        user.setUsername("tom");

        // when
        userRepository.save(user);

        // then
        User foundUser = userRepository.findByUsername("tom");
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void testFindByUsernameNull() {
        User foundUser = userRepository.findByUsername("tom");
        Assert.assertNull(foundUser);
    }

    @Test
    public void testFindByEmail() {
        // given
        User user = createDummyUser();
        user.setEmail("tom@ikenga.me");

        // when
        userRepository.save(user);

        // then
        User foundUser = userRepository.findByEmail("tom@ikenga.me");
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void testFindByEmailNull() {
        User foundUser = userRepository.findByEmail("tom@ikenga.me");
        Assert.assertNull(foundUser);
    }

    @Test
    public void testFindByUsernameAndHashedPassword() {
        // given
        User user = createDummyUser();
        user.setUsername("tom");
        user.setHashedPassword("pw");


        // when
        userRepository.save(user);

        // then
        User foundUser = userRepository.findByUsernameAndHashedPassword("tom", "pw");
        Assert.assertNotNull(foundUser);
    }

    @Test
    public void testFindByUsernameAndHashedPasswordNull() {
        User foundUser = userRepository.findByUsernameAndHashedPassword("tom", "pw");
        Assert.assertNull(foundUser);
    }

    private User createDummyUser() {
        User user = new User();
        user.setEmail("tom@ikenga.me");
        user.setUsername("tom");
        user.setHashedPassword("123");
        return user;
    }
}