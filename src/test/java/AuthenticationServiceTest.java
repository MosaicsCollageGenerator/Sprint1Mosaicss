package test.java;

import main.java.builder.CollageBuilder;
import main.java.model.User;
import main.java.repository.UserRepository;
import main.java.service.AuthenticationService;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    @Test
    public void test_Hashing_IsDeterministic() {
        String test = "test";
        String hashedTest = AuthenticationService.hashPassword(test);
        assertEquals(hashedTest, AuthenticationService.hashPassword(test));
    }

    @Test
    public void test_Hashing_AuthenticatingUserShouldWorkProperly() {
        User newUser = new User("hello", AuthenticationService.hashPassword("test"));
        String test = "test";
        assertTrue(AuthenticationService.checkPassword(newUser, test));
    }
}