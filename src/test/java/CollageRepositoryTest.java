package test.java;
import org.junit.Test;

import main.java.model.Collage;
import main.java.repository.CollageRepository;
import main.java.repository.UserRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;


public class CollageRepositoryTest {
    private UserRepository userRepository;
    private CollageRepository collageRepository;

    @Test
    public void test_InitiallyNoProductExists() {
        collageRepository = new CollageRepository(true);
        // Initial user has collage id 1
        List<Collage> collages = collageRepository.getAllCollageFromUser("1");
        assertEquals(0, collages.size());
    }

    @Test
    public void test_saveCollage() {
        collageRepository = new CollageRepository(true);
        Collage newCollage = new Collage("test", "testing.com", "1");
        collageRepository.saveCollage(newCollage);
        List<Collage> retrievedCollages = collageRepository.getAllCollageFromUser("1");
        assertEquals(1, retrievedCollages.size());
        assertEquals("test", retrievedCollages.get(0).getTitle());
        assertEquals("testing.com", retrievedCollages.get(0).getSrc());
    }

    @Test
    public void test_getAllCollagesFromUser() {
        collageRepository = new CollageRepository(true);
        Collage newCollage = new Collage("test", "testing.com", "1");
        Collage newCollage1 = new Collage("test", "testing.com", "1");
        Collage newCollage2 = new Collage("test", "testing.com", "1");
        Collage newCollage3 = new Collage("test", "testing.com", "1");
        collageRepository.saveCollage(newCollage);
        collageRepository.saveCollage(newCollage1);
        collageRepository.saveCollage(newCollage2);
        collageRepository.saveCollage(newCollage3);
        List<Collage> collages = collageRepository.getAllCollageFromUser("1");
        assertEquals(4, collages.size());
    }

}
