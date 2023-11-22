import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests of all supported methods of Persons.
 */
class PersonsTest {

    @BeforeEach
    void setUp(){
        Person DavidChaimelwitz = new Person("David", "Chaimelwitz", true, (short)1996);
    }

    @Test
    void constructorTester(){
        String fName = "Steve";
        String lName = "Jobs";
        Person SteveJobs = new Person(fName, lName, true, (short)1955);

        assertTrue(true);
    }
}