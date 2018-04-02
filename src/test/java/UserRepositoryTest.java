package test.java;
import org.junit.*;

import main.java.model.User;
import main.java.repository.UserRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserRepositoryTest {
    private UserRepository userRepository;

    	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
    	    userRepository = new UserRepository(true);
	}

	@After
	public void tearDown() throws Exception {
    	    userRepository.resetDatabase();
	}

    @Test
    public void test_initialUserExistsAsTest() {
        User retrievedUser = userRepository.findByUsername("test");
        assertNotNull(retrievedUser);
        assertEquals("test", retrievedUser.getUsername());
        assertEquals("test", retrievedUser.getPassword());
    }

    @Test
    public void test_saveUser() {
        User newUser = new User("hello", "password");
        userRepository.saveUser(newUser);
        User retrievedUser = userRepository.findByUsername("hello");
        assertEquals("hello", retrievedUser.getUsername());
        assertEquals("password", retrievedUser.getPassword());
    }

    @Test
    public void test_getAllCollageFromUser() {
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
