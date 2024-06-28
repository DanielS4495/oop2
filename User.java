
import java.util.ArrayList;
import java.util.List;

public class User extends Person{

    // private final long userId;
    private List<Reservation> pastOrders;
    private List<Reservation> wishList;
    // private List<Notification> notifications;
    // public static long count = 0;
    // private String name;
    // private String email;
    // private String password;
    // private boolean login;
    // private long phone;

    public User(String name, long phone, String email, String password) {
        
        // this.name = name;
        // this.email = email;
        // this.password = password;
        // this.login = false;
        // this.phone = phone;
        super(name, phone, email, password);
        // super.id = count++;
        this.wishList = new ArrayList<>();
        this.pastOrders = new ArrayList<>();
        // this.notifications = new ArrayList<>();
    }

    // public long getUserId() {
    //     return this.userId;
    // }

    // public String getPassword() {
    //     return this.password;
    // }

    // public String getName() {
    //     return this.name;
    // }

    // public long getPhone() {
    //     return this.phone;
    // }

    // public String getEmail() {
    //     return this.email;
    // }

    // public boolean getLogin() {
    //     return this.login;
    // }

    // public void setLoginOut() {
    //     this.login = false;
    // }

    public List<Reservation> getPastOrders() {
        return this.pastOrders;
    }

    public List<Reservation> getWishList() {
        return wishList;
    }

    public void addToWishlist(Reservation reservation) {
        wishList.add(reservation);
    }

    public void addPastOrder(Reservation reservation) {
        if (reservation.getStatus().toString().equals("CONFIRMED")) {
            String message = String.format("Reservation for room %d from %s to %s has been CONFIRMED.",
                    reservation.getRoom().getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());

            sendNotification(message);
            pastOrders.add(reservation);
        } else {
            System.out.println("Order not completed yet");
        }

    }

    public void removePastOrder(Reservation reservation) {
        if (reservation.getStatus().toString().equals("CANCELLED")) {
            pastOrders.remove(reservation);
            String message = String.format("Reservation for room %d from %s to %s has been CANCELLED.",
                    reservation.getRoom().getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());
            sendNotification(message);
        } else {
            System.out.println("Order not CANCELLED yet");
        }

    }

    public void removeFromWishlist(Reservation reservation) {
        wishList.remove(reservation);
    }

    // public void setEmail(String oldEmail, String newEmail) {
    //     if (oldEmail.equals(this.email)) {
    //         this.email = newEmail;
    //     }
    // }

    // public long loginWithId(long id, String password) {
    //     if (this.userId == id && this.password.equals(password)) {
    //         this.login = true;
    //         return this.userId;
    //     }
    //     return -1;
    // }

    // public long loginWithEmail(String email, String password) {
    //     if (this.email.equals(email) && this.password.equals(password)) {
    //         this.login = true;
    //         return this.userId;
    //     }
    //     return -1;
    // }

    // public long loginWithName(String name, String password) {
    //     if (this.name.equals(name) && this.password.equals(password)) {
    //         this.login = true;
    //         return this.userId;
    //     }
    //     return -1;
    // }

    // public long loginWithPhone(long phone, String password) {
    //     if (this.phone == phone && this.password.equals(password)) {
    //         this.login = true;
    //         return this.userId;
    //     }
    //     return -1;
    // }

    // public void sendNotification(String message) {
    //     for (Notification notificationType : notifications) {
    //         notificationType.sendNotification(message, this, null);
    //     }
    // }

    // public void addNotification(Notification notificationType) {
    //     if (notificationType != null) {
    //         notifications.add(notificationType);
    //     }
    // }

    // public void removeNotification(Notification notificationType) {
    //     if (notificationType != null) {
    //         notifications.remove(notificationType);
    //     }
    // }

    // public List<String> viewNotification() {
    //     List<String> list = new ArrayList<>();
    //     for (Notification notificationType : notifications) {
    //         if (notificationType != null) {
    //             list.add(notificationType.toString());
    //         }
    //     }
    //     return list;
    // }

    // @Override
    // public String toString() {
    //     return (" userId=" + getId() + ", name=" + getName() + ", email=" + getEmail() + ", phone=" + getPhone() + "\n");
    // }
}
