
public class Person {

    private final long personId;
    private String name;
    private String email;
    private String password;
    private boolean login;
    private long phone;

    public Person(long personId, String name, long phone, String email, String password) {
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.login = false;
        this.phone = phone;
    }

    public long getPersonId() {
        return personId;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoginOut() {
        this.login = false;
    }

    public void setPassword(String oldPassword, String newPassword) {
        if (oldPassword.equals(this.password)) {
            this.password = newPassword;
        }
    }

    public boolean loginWithId(long id, String password) {
        if (this.personId == id && this.password.equals(password)) {
            this.login = true;
            return true;
        }
        return false;
    } // For login functionality

    public boolean loginWithEmail(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.login = true;
            return true;
        }
        return false;
    } // For login functionality

    public boolean loginWithName(String name, String password) {
        if (this.name.equals(name) && this.password.equals(password)) {
            this.login = true;
            return true;
        }
        return false;
    } // For login functionality

    public boolean loginWithPhone(long phone, String password) {
        if (this.phone == phone && this.password.equals(password)) {
            this.login = true;
            return true;
        }
        return false;
    } // For login functionality

    // public boolean login(String loginType, long personId, String email,String name, long phone, String password) {
    //     switch (loginType.toLowerCase()) {
    //         case "id":
    //             return this.personId == personId && this.password.equals(password);
    //         case "email":
    //             return this.email.equals(email) && this.password.equals(password);
    //         case "name":
    //             return this.name.equals(name) && this.password.equals(password);
    //         case "phone":
    //             return this.phone == phone && this.password.equals(password);
    //         default:
    //             return false;
    //     }
    // }
}
