public class Persons {
    String firstName;
    String lastName;
    boolean isMale;
    short yearOfBirth;

    public Persons() {
        firstName = "";
        lastName = "";
        isMale = true;
        yearOfBirth = 1950;
    }

    public void setFirstName(String firstName) throws Exception {
        isNull(firstName);
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws Exception {
        isNull(lastName);
        this.lastName = lastName;
    }

    public void setMale(boolean isMale) throws Exception {
        isNull(firstName);
        this.isMale = isMale;
    }

    public void setYearOfBirth(short yearOfBirth) throws Exception {
        isNull(yearOfBirth);
        this.yearOfBirth = yearOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public boolean getIsMale() {
        return isMale;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    private void isNull(Object o) throws Exception {
        if (o == null) {
            throw new Exception("May not set values to null");
        }
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if (firstName == null) {
            firstName = "";
        }

        return "Name is " + firstName + lastName + "\n The statment that this person is male is: " + isMale + "\n";
    } // should provide an annotated display of Person info

    @Override
    public boolean equals(Object o) {
        boolean isEqual = false;

        return isEqual;
    }

    @Override
    public int hashCode() {
        int hashVal = 0;
        for (int i = 0; i < firstName.length(); i++) {
            hashVal += (int) firstName.charAt(i);
        }
        for (int i = 0; i < lastName.length(); i++) {
            hashVal += (int) firstName.charAt(i);
        }
        hashVal += yearOfBirth;

        hashVal = isMale ? hashVal * 10 + 1 : hashVal * 10;

        return hashVal;
    } // hand-code this method without using Objects static methods
}