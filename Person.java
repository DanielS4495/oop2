public class Person {
    private final long personId;
    private String name;
    private String email;
    private String password;
    private boolean login;

    public Person(long personId, String name, String email, String password) {
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.login = false;
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

    public void setPassword(String oldPassword,String newPassword) {
        if(oldPassword.equals(this.password)){
            this.password = newPassword;
        }
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

}
