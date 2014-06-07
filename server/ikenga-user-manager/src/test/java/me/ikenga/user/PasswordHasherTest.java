package me.ikenga.user;

import me.ikenga.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PasswordHasherTest extends BaseTest {

    @Autowired
    private PasswordHasher hasher;

    @Test
    public void testHashing() {
        // given
        String plainPassword = "123456";

        // when
        String hashedPassword = hasher.hashPassword(plainPassword);

        // then
        // we only check that the plain password is not the same as the hashed password
        // since the concrete hash algorithm is an implementation detail
        Assert.assertNotEquals(plainPassword, hashedPassword);
    }

}
