import java.util.Objects;

/**
 * Represents a person with attributes first name, last name, gender, and year of birth.
 * This class provides methods for setting and getting these attributes, as well as custom
 * implementations for equality comparison, toString method and hash code generation.
 *
 * @author Alabama Montez
 * @version 1.0
 * @since 2023-11-22
 */
public class Person {
    static final int CURRENT_YEAR = 2023;
    static final short CHAR_TO_INT = 96;
    private String firstName;
    private String lastName;
    private boolean isMale;
    private short yearOfBirth;

    /**
     * Constructs a new Person object with default values.
     * The default values are first name 'John' and last name 'Smith', male gender, and a birth year of 1950.
     */
    public Person() {
        firstName = "John";
        lastName = "Smith";
        isMale = true;
        yearOfBirth = 1950;
    }

    /**
     * Constructs a new Person with the specified attributes.
     *
     * @param fName  the first name of the person
     * @param lName  the last name of the person
     * @param gender the gender of the person (true for male, false for female)
     * @param year   the year of birth of the person
     * @throws Error if any of the input parameters are invalid
     */
    public Person(String fName, String lName, boolean gender, short year) {
        //short circuit wise, I should run the tests first, but I feel that sacrifices readability,
        //and assignments are not time intensive
        isTextfull(fName);
        firstName = fName;

        isTextfull(lName);
        lastName = lName;

        isMale = gender;

        isTimefull(year);
        yearOfBirth = year;
    }

    /**
     * Sets the first name of the person.
     *
     * @param firstName the new first name to set
     * @throws Error if the provided first name is null or empty
     */
    public void setFirstName(String firstName) {
        isTextfull(firstName);
        this.firstName = firstName;
    }

    /**
     * Sets the last name of the person.
     *
     * @param lastName the new first name to set
     * @throws Error if the provided first name is null or empty
     */
    public void setLastName(String lastName) {
        isTextfull(lastName);
        this.lastName = lastName;
    }

    /**
     * Sets the gender of the person.
     *
     * @param gender the new (true for male, false for female)
     */
    public void setIsMale(boolean gender) {
        this.isMale = gender;
    }

    /**
     * Sets the year of birth of the person.
     *
     * @param yearOfBirth the new year of birth to set
     * @throws Error if the provided year is out of bounds
     */
    public void setYearOfBirth(short yearOfBirth) {
        isTimefull(yearOfBirth);
        this.yearOfBirth = yearOfBirth;
    }

    /**
     * Gets the first name of the person.
     *
     * @return the first name of the person
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets the last name of the person.
     *
     * @return the last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the gender of the person.
     *
     * @return true if the person is male, false if the person is female
     */
    public boolean getIsMale() {
        return isMale;
    }

    /**
     * Gets the year of birth of the person.
     *
     * @return the year of birth of the person
     */
    public short getYearOfBirth() {
        return yearOfBirth;
    }

    private void isNull(Object o) {
        if (o == null) {
            throw new IllegalArgumentException("May not set a person field to null");
        }
    }

    private void isTextfull(String input) {
        isNull(input);
        if (input.isEmpty())
            throw new IllegalArgumentException("may not fill a person field with an empty string");
    }

    private void isTimefull(short year) {
        if (year < 1950 || year >= CURRENT_YEAR)
            throw new IllegalArgumentException("year out of bounds");
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String gender = isMale ? "Man" : "Woman";

        return firstName + " " + lastName + "\nis a " + (CURRENT_YEAR - yearOfBirth) + " year old " + gender + "\n";
    } // should provide an annotated display of Person info

    /**
     * Indicates whether some other object is "equal to" this one.
     * Equality is based on the first name, last name, gender, and year of birth.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the o argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Person input = (Person) o;

        return Objects.equals(input.getFirstName(), firstName) && Objects.equals(input.getLastName(), lastName) && input.getIsMale() == isMale && input.getYearOfBirth() == yearOfBirth;
    }

    /**
     * Returns a hash code value for the person.
     * The hash code is generated based on a custom method involving the first name,
     * last name, gender, and year of birth.
     *
     * @return a hash code value for the person
     */
    @Override
    public int hashCode() {
        //uses a custom method of generating unique integer values for all integer pairs.
        //The system simply counts all integer pairs, starting with 1,0 = 1, and 1,1 = 2.
        //gender is captured via parity encoding. This system is good for encoding binary fields.
        long hashVal = 0;

        hashVal = nameHasher(hashVal, firstName);

        hashVal = nameHasher(hashVal, lastName);

        hashVal = hashVal > yearOfBirth ? pairCounter(hashVal, yearOfBirth): pairCounter(yearOfBirth, hashVal);

        //parity now reveals gender
        hashVal = isMale ? hashVal * 2 + 1 : hashVal * 2;

        return (int) hashVal;
    } // hand-code this method without using Objects static methods

    /**
     * Generates a custom hash value based on a given algorithm for string names.
     * <p>
     * This method converts each character of the given name to its integer representation
     * and combines them using a collision-less pair counting approach.
     *
     * @param currentHashValue the current hash value to be updated
     * @param name             the name to be hashed
     * @return the updated hash value
     */
    private long nameHasher(long currentHashValue, String name) {
        name = name.toLowerCase();
        long hashedBrown = currentHashValue;
        int convertedChar;

        //A method of my own devising for collision-less integer generation
        for (int i = 0; i < name.length(); i++) {
            convertedChar = (int) name.charAt(i) - CHAR_TO_INT;

            hashedBrown = Math.abs(hashedBrown) > convertedChar ? pairCounter(hashedBrown, convertedChar) : pairCounter(convertedChar, hashedBrown);
        }

        return hashedBrown;
    }

    /**
     * Computes a unique integer value for a pair of integers.
     *
     * This method uses a formula for generating unique integer values based on the
     * combination of two integers, ensuring a collision-less mapping.
     *
     * @param greater the greater of the two integers
     * @param lesser  the lesser of the two integers
     * @return the computed unique integer value
     */
    private long pairCounter(long greater, long lesser) {
        return (greater * (greater + 1) / 2) - 1 + lesser;
    }
}