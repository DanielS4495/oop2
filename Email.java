import java.util.Objects;

public class Email implements Notification {

    @Override
    public void sendNotification(String message, Person person) {

        System.out.println("Sending email to " + person.getName() + " with " + person.getEmail() + " : " + message);

    }
    @Override
    public String toString() {
        return "Email";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null ) return false;
        if (this == o||getClass()==o.getClass()) return true;
        return Objects.equals(toString(), o.getClass().toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }
}
