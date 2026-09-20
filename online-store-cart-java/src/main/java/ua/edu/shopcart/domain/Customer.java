package ua.edu.shopcart.domain;

/**
 * Покупець інтернет-магазину.
 */
public final class Customer {
    /** Унікальний ідентифікатор покупця. */
    private final String id;

    /** Повне ім'я покупця. */
    private final String fullName;

    /** Електронна адреса для чека. */
    private final String email;

    /**
     * Створює покупця.
     *
     * @param id непорожній ідентифікатор
     * @param fullName непорожнє ім'я
     * @param email електронна адреса з символом {@code @}
     */
    public Customer(String id, String fullName, String email) {
        if (id == null || id.isBlank() || fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("id та fullName мають бути заповнені");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("email має бути коректним");
        }
        this.id = id;
        this.fullName = fullName;
        this.email = email;
    }

    /**
     * Повертає ідентифікатор покупця.
     *
     * @return ідентифікатор
     */
    public String getId() {
        return id;
    }

    /**
     * Повертає повне ім'я.
     *
     * @return ім'я покупця
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Повертає електронну адресу.
     *
     * @return email покупця
     */
    public String getEmail() {
        return email;
    }

    /**
     * Формує стислий опис покупця.
     *
     * @return ім'я та email
     */
    @Override
    public String toString() {
        return fullName + " <" + email + ">";
    }
}
