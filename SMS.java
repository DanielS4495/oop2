import java.util.Objects;

public class SMS implements Notification {

    @Override
    public void sendNotification(String message, Person person) {

        System.out.println("Sending sms to " + person.getName() + " with " + person.getPhone() + " : " + message);

    }
    @Override
    public String toString() {
        return "SMS";
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
