package models;

import org.junit.*;
import java.util.Arrays;
import static org.junit.Assert.*;

public class SightingTest {

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void sighting_instantiatesCorrectly_true() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        assertEquals(true, testSighting instanceof Sighting);
    }

    @Test
    public void Sighting_instantiatesWithAnimalId_int() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        assertEquals(1, testSighting.getAnimalId());
    }

    @Test
    public void getLocation_sightingInstantiatesWithLocation_zoneA() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        assertEquals("ZoneA", testSighting.getLocation());
    }

    @Test
    public void getRangerName_sightingInstantiatesWithRangerName_Barsil() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        assertEquals("Barsil", testSighting.getRangerName());
    }


    @Test
    public void equals_returnsTrueIfInstancesAreSame() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        Sighting secondSighting = new Sighting(1, "ZoneA", "Barsil");
        assertTrue(testSighting.equals(secondSighting));
    }

    @Test
    public void save_insertsObjectIntoDatabase_Sighting() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        testSighting.save();
        assertTrue(Sighting.all().get(0).equals(testSighting));
    }

    @Test
    public void all_returnsAllInstancesOfSighting_true() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        testSighting.save();
        Sighting secondSighting = new Sighting(1, "ZoneA", "Barsil");
        secondSighting.save();
        assertEquals(true, Sighting.all().get(0).equals(testSighting));
        assertEquals(true, Sighting.all().get(1).equals(secondSighting));
    }

    @Test
    public void save_assignsIdToObject() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        testSighting.save();
        Sighting savedSighting = Sighting.all().get(0);
        assertEquals(testSighting.getId(), savedSighting.getId());
    }

    @Test
    public void find_returnsSightingWithSameId_secondSighting() {
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        testSighting.save();
        Sighting secondSighting = new Sighting(1, "ZoneA", "Barsil");
        secondSighting.save();
        assertEquals(Sighting.find(secondSighting.getId()), secondSighting);
    }

    @Test
    public void save_savesAnimalIdIntoDB_true() {
        Animal newAnimal = new Animal("cheetah", "sam", "feline");
        newAnimal.save();
        Sighting testSighting = new Sighting(1, "ZoneA", "Barsil");
        testSighting.save();
        Sighting savedSighting = Sighting.find(testSighting.getId());
        assertEquals(savedSighting.getAnimalId(), newAnimal.getId());
    }
}