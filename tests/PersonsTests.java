import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests of all supported methods of Persons.
 */
class PersonsTest {
    Persons DavidChaimelwitz;
    @BeforeEach
    void setUp(){
        DavidChaimelwitz.setMale(true);
        DavidChaimelwitz.setFirstName("David");
        DavidChaimelwitz.setLastName("Chaimelwitz");
        DavidChaimelwitz.setYearOfBirth((short) 2000);

    }

    @Test
    void constructorTester(){

    }
}