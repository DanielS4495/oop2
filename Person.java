
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Person {

    protected final long id;
    protected String name;
    protected String email;
    protected String password;
    protected boolean login;
    protected long phone;
    protected List<Notification> notifications;
    public static long count = 0;

    public Person(String name, long phone, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.login = false;
        this.phone = phone;
        this.notifications = new ArrayList<>();
        this.id = count++;
    }

    public long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getName() {
        return this.name;
    }

    public long getPhone() {
        return this.phone;
    }

    public String getEmail() {
        return this.email;
    }

    public boolean getLogin() {
        return this.login;
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

    public long loginWithId(long id, String password) {
        if (this.id == id && this.password.equals(password)) {
            this.login = true;
            return this.id;
        }
        return -1;
    }

    public long loginWithEmail(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.login = true;
            return this.id;
        }
        return -1;
    }

    public long loginWithName(String name, String password) {
        if (this.name.equals(name) && this.password.equals(password)) {
            this.login = true;
            return this.id;
        }
        return -1;
    }

    public long loginWithPhone(long phone, String password) {
        if (this.phone == phone && this.password.equals(password)) {
            this.login = true;
            return this.id;
        }
        return -1;
    }

    public void sendNotification(String message) {
        for (Notification notificationType : notifications) {
            notificationType.sendNotification(message, this);
        }
    }

    public void addNotification(Notification notificationType) {
        if (notificationType != null && !notifications.toString().contains(notificationType.toString())) {
            notifications.add(notificationType);
        }
    }

    public void removeNotification(Notification notificationType) {
        if (notificationType != null) {
            Iterator<Notification> iterator = notifications.iterator();
            while (iterator.hasNext()) {
                Notification notification = iterator.next();
                if (notification != null && notification.equals(notificationType)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }

    public List<String> viewNotification() {
        List<String> list = new ArrayList<>();
        for (Notification notificationType : notifications) {
            if (notificationType != null) {
                list.add(notificationType.toString());
            }
        }
        return list;
    }

    @Override
    public String toString() {
        return "PersonId: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone;
    }
}
