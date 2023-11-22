public class Persons {
    static final int CURRENTYEAR = 2023;
    private String firstName;
    private String lastName;
    private boolean isMale;
    private short yearOfBirth;

    public Persons() {
        firstName = "";
        lastName = "";
        isMale = true;
        yearOfBirth = 1950;
    }

    public Persons(String fName, String lName, boolean gender, short year){
        isTextfull(fName);
        firstName = fName;

        isTextfull(lName);
        lastName = lName;

        isMale = gender;

        isTimefull(year);
        yearOfBirth = year;
    }

    public void setFirstName(String firstName){
        isNull(firstName);
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        isNull(lastName);
        this.lastName = lastName;
    }

    public void setMale(boolean isMale){
        isNull(firstName);
        this.isMale = isMale;
    }

    public void setYearOfBirth(short yearOfBirth){
        isTimefull(yearOfBirth);
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

    private void isNull(Object o){
        if (o == null) {
            throw new Error("May not set a person field to null");
        }
    }

    private void isTextfull(String input){
        isNull(input);
        if(input.isEmpty())
            throw new Error("may not fill a person field with an empty string");
    }
    private void isTimefull(short year){
        if(year < 1950 || year >= CURRENTYEAR)
            throw new Error("year out of bounds");
    }

    /**
     * Returns a string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
//        if (firstName == null) {
//            firstName = "";
//        }

        String gender = isMale ? "Man" : "Woman";

        return firstName + " " + lastName + "\n is a " + (CURRENTYEAR - yearOfBirth) + " year old " + gender + "\n";
    } // should provide an annotated display of Person info

    @Override
    public boolean equals(Object o) {
        if(o == null)
            return false;
        //TODO test of same class
        if (!o.getClass().equals(this.getClass()))
            return false;

        return o.hashCode() == this.hashCode();
    }

    @Override
    public int hashCode() {
        //uses Godel's method for encoding of multiple values to capture three fields. best for encoding a small number of data fields.
        //gender is captured via parity encoding. good for encoding one field.
        int hashVal;

        hashVal = (int) Math.pow(2, nameHasher(firstName));

        hashVal *= (int) Math.pow(3, nameHasher(firstName));

        hashVal *= (int) Math.pow(5, yearOfBirth);

        //parity now reveals gender
        hashVal = isMale ? hashVal * 2 + 1 : hashVal * 2;

        return hashVal;
    } // hand-code this method without using Objects static methods

    private int nameHasher(String name){
        int hashedBrown = 0;

        for (int i = 0; i < name.length(); i++) {
             hashedBrown += (int) name.charAt(i);
        }

        return hashedBrown;
    }
}