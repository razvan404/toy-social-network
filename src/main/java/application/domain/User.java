package application.domain;

import application.domain.exceptions.ValidationException;
import application.utils.Encoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The class <b>User</b> represents the client using the social media app.
 */
public class User extends Entity<UUID> {
    private final MailAddress mailAddress;
    private final String firstName;
    private final String lastName;
    private final String password;
    private final LocalDate registerDate;
    private final LocalDate birthDate;
    private final String biography;

    /**
     * Constructs a new User, the data should be validated, if you need to create a new User, use User.create() instead.
     *
     * @param id            UUID, the identifier of the User
     * @param mailAddress   String, the email address of the User, already validated
     * @param firstName     String, the first name of the User, validated
     * @param lastName      String, the last name of the User, validated
     * @param password      String, the password of the User, validated
     * @param registerDate  LocalDate, the register-date of the User, validated
     * @param birthDate     LocalDate, the birthdate of the User, validated
     * @param biography     String, the biography of the User, validated
     */
    public User(UUID id, String mailAddress, String firstName, String lastName, String password,
                LocalDate registerDate, LocalDate birthDate, String biography) {
        super(id);
        this.mailAddress = MailAddress.of(mailAddress);
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.registerDate = registerDate;
        this.birthDate = birthDate;
        this.biography = biography;
    }

    public static User create(String mailAddress, String firstName, String lastName, String password,
                              LocalDate birthDate, String biography) {
        String error = "";
        try {
            MailAddress.of(mailAddress);
        }
        catch (Exception e) {
            error += e.getMessage() + " | ";
        }

        if (firstName == null || firstName.equals("") || firstName.equalsIgnoreCase("null")) {
            error += "The first name must not be null | ";
        }
        else if (firstName.length() < 2) {
            error += "The first name is too short | ";
        }
        else if (firstName.length() > 99) {
            error += "The first name is too long | ";
        }
        else if (!firstName.matches("^[A-Z][a-zA-Z-0-9]*$")) {
            error += "The first name doesn't respect the format | ";
        }

        if (lastName == null || lastName.equals("") || lastName.equalsIgnoreCase("null")) {
            error += "The last name must not be null | ";
        }
        else if (lastName.length() < 2) {
            error += "The last name is too short | ";
        }
        else if (lastName.length() > 99) {
            error += "The last name is too long | ";
        }
        else if (!lastName.matches("^([A-Z][a-zA-Z-0-9]* *)+$")) {
            error += "The last name doesn't respect the format | ";
        }

        if (password == null) {
            error += "The password shouldn't be null | ";
        }
        else if (password.length() < 5) {
            error += "The password should have at least 5 characters | ";
        }

        if (birthDate == null) {
            error += "The birth date must not be null | ";
        }
        else if (birthDate.isAfter(LocalDate.now()) || birthDate.plusYears(120).isBefore(LocalDate.now())) {
            error += "Invalid birth date | ";
        }

        if (biography != null && biography.length() > 100) {
            error += "The biography is too long | ";
        }

        if (!error.isEmpty()) {
            throw new ValidationException(error.substring(0, error.length() - 3));
        }
        return new User(UUID.randomUUID(), mailAddress, firstName, lastName, Encoder.encode(password), LocalDate.now(), birthDate, biography);
    }

    /**
     * @return the email address of the User
     */
    public String getMailAddress() {
        return mailAddress.toString();
    }

    /**
     * @return the first name of the User
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return the last name of the User
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @return the full name of the User
     */
    public String getName() {
        return firstName + ' ' + lastName;
    }

    /**
     * @return the encoded password of the User
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the register date of the User
     */
    public LocalDate getRegisterDate() {
        return registerDate;
    }

    /**
     * @return the birthdate of the User
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * @return the biography of the User
     */
    public String getBiography() {
        return biography;
    }

    @Override
    public String toString() {
        return firstName + ' ' + lastName;
    }

    /**
     * Verify if the name of the User can match with a given substring.
     * @param subString the substring to match the name of the User
     * @return true if the given substring matches the name of the User, false otherwise
     */
    public boolean match(String subString) {
        return (firstName + ' ' + lastName).toLowerCase().contains(subString.toLowerCase())
                || (lastName + ' ' + firstName).toLowerCase().contains(subString.toLowerCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getID(), user.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }
}
