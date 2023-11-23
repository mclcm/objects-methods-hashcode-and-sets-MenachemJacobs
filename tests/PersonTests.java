import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of all supported methods of Persons.
 */
class PersonsTest {
    static final int CURRENT_YEAR = 2023;
    Person DavidChaimelwitz;

    @BeforeEach
    void setUp(){
        DavidChaimelwitz = new Person("David", "Chaimelwitz", true, (short)1996);
    }

    @Test
    void constructorTester(){
        String fName = "Steve";
        String lName = "Jobs";
        boolean gender = true;
        short birthYear = 1995;
        Person SteveJobs = new Person(fName, lName, gender, birthYear);

        assertTrue(SteveJobs.getIsMale() && fName.equals(SteveJobs.getFirstName()) && lName.equals(SteveJobs.getLastName()) && birthYear == SteveJobs.getYearOfBirth());
    }

    @Test
    void toString_Normal(){
        String gender = DavidChaimelwitz.getIsMale() ? "Man" : "Woman";
        String assumed = DavidChaimelwitz.getFirstName() + " " + DavidChaimelwitz.getLastName() + "\n is a " + (CURRENT_YEAR - DavidChaimelwitz.getYearOfBirth()) + " year old " + gender + "\n";

        String testable = DavidChaimelwitz.toString();

        assertEquals(assumed, testable, "toString() isn't reporting right");
    }

    @Test
    void toString_Edge_Random(){
        Person Rando = randomBuilder();

        String gender = Rando.getIsMale() ? "Man" : "Woman";
        String assumed = Rando.getFirstName() + " " + Rando.getLastName() + "\n is a " + (CURRENT_YEAR - Rando.getYearOfBirth()) + " year old " + gender + "\n";

        String testable = Rando.toString();

        assertEquals(assumed, testable, "toString() isn't reporting right in the randomized tests");
    }

    @Test
    void equals_Normal(){
        Person testable = cloner(DavidChaimelwitz);

        assertEquals(DavidChaimelwitz, testable, "equals() fails in base case");

        testable.setFirstName("Arny");
        assertNotEquals(DavidChaimelwitz, testable, "equals() fails to detect differences data");
    }

    @Test
    void equals_Edge_Reset(){
        Person testable = cloner(DavidChaimelwitz);

        testable.setFirstName("Arny");
        testable.setFirstName(DavidChaimelwitz.getFirstName());

        assertEquals(DavidChaimelwitz, testable, "equals() fails in the case where values have been set to match");
    }

    @Test
    void hashCode_Normal(){
        int hashToMatch = DavidChaimelwitz.hashCode();
        int testableHash = cloner(DavidChaimelwitz).hashCode();
        assertEquals(hashToMatch, testableHash, "Duplicate objects are not creating duplicate hash codes");

        testableHash = randomBuilder().hashCode();
        assertNotEquals(hashToMatch, testableHash, "Non-Duplicate objects are creating duplicate hash codes");
    }

    @Test
    void hashCode_Edge_CaseWeird(){
        int hashToMatch = DavidChaimelwitz.hashCode();

        Person testPerson = cloner(DavidChaimelwitz);
        testPerson.setFirstName(testPerson.getFirstName().toUpperCase());
        int testableHash = cloner(DavidChaimelwitz).hashCode();

        assertEquals(hashToMatch, testableHash, "Hash code generation is not case invariant");
    }

    @Test
    void profProfligation(){
        Person[] peopleArray = new Person[10];

        for (int i = 0; i < 10; i++) {
            peopleArray[i] = randomBuilder();
        }

        Set<Person> peopleSet = new HashSet<>(Arrays.asList(peopleArray));
        Person[] peopleArrayTwo = new Person[10];
        int index = 0;

        for (Person guy : peopleSet) {
            peopleArrayTwo[index] = guy;
            index++;
        }

        for (Person guy : peopleArray) {
            assertTrue(peopleSet.contains(guy));
        }

        assertNotEquals(peopleArray, peopleArrayTwo);
    }
    
    Person cloner(Person Donor){
        String fName = Donor.getFirstName();
        String lName = Donor.getLastName();
        boolean gender = Donor.getIsMale();
        short birthYear = Donor.getYearOfBirth();

        return new Person(fName, lName, gender, birthYear);
    }

    Person randomBuilder(){
        String[] fNames = new String[]{"Aeriel", "Sam", "Seneca", "Valor", "Cartor"};
        String[] lNames = new String[]{"Aronovich", "Benson", "Carnak", "Von Burn", "Ibn Brahoot"};
        boolean[] genders = new boolean[]{true, false};

        int rand = (int)(5 * Math.random());
        String outFN = fNames[rand];

        rand = (int)(5 * Math.random());
        String outLN = lNames[rand];

        rand = (int)(2 * Math.random());
        boolean outGen = genders[rand];

        int outYear = 1950 + (int)(73 * Math.random());

        return new Person(outFN, outLN, outGen, (short)outYear);
    }
}