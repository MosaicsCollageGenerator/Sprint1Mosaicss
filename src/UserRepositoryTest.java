import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserRepositoryTest {
    private UserRepository userRepository;

    @Test
    public void test_initialUserExistsAsTest() {
        userRepository = new UserRepository(true);
        User retrievedUser = userRepository.findByUsername("test");
        assertEquals("test", retrievedUser.getUsername());
        assertEquals("test", retrievedUser.getPassword());
    }

    @Test
    public void test_saveUser() {
        userRepository = new UserRepository(true);
        User newUser = new User("hello", "password");
        userRepository.saveUser(newUser);
        User retrievedUser = userRepository.findByUsername("hello");
        assertEquals("hello", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }

    @Test
    public void test_getAllCollageFromUser() {
        userRepository = null;
        userRepository = new UserRepository(true);
        User newUser1 = new User("hello1", "password1");
        User newUser2 = new User("hello2", "password2");
        User newUser3 = new User("hello3", "password3");
        userRepository.saveUser(newUser1);
        userRepository.saveUser(newUser2);
        userRepository.saveUser(newUser3);
        List<User> users = userRepository.getAllUsers();
        assertEquals(4, users.size());
    }

}
