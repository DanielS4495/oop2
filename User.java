
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Person {

    // private final long userId;
    // private String name;
    // private String email;
    // private String password;
    // private WishList wishList;
    private List<Reservation> pastOrders;
    private List<Reservation> wishList;
    // private List<Review> reviews; //need to do only in hotel
    private Map<Room, Boolean> subscribedRooms;
    // private boolean login;
    private List<Notification> notifications;

    public User(long userId, String name, long phone, String email, String password) {
        // this.userId = userId;
        // this.name = name;
        // this.email = email;
        // this.password = password;
        // Super(userId, name, email, password);
        super(userId, name, phone, email, password);
        this.wishList = new ArrayList<>();
        this.pastOrders = new ArrayList<>();
        // this.reviews = new ArrayList<>();
        // this.wishList = new WishList();
        // this.login = false;
        this.subscribedRooms = new HashMap<>();
        this.notifications = new ArrayList<>();
    }

    @Override
    public long getPersonId() {
        return super.getPersonId();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public boolean getLogin() {
        return super.getLogin();
    }

    @Override
    public void setLoginOut() {
        super.setLoginOut();
    }

    @Override
    public long getPhone() {
        return super.getPhone();
    }

    // public List<Review> getReviews() {
    //     return reviews;
    // } // Get reviews
    public List<Reservation> getPastOrders() {
        return this.pastOrders;
    } // Get past orders

    public List<Reservation> getWishList() {
        return wishList;
    } // Get wishlist

    // public void addReview(String reviewerName, double rating, String comment,Date date) {
    //     Review review = new Review(reviewerName, rating, comment, date);
    //     reviews.add(review);
    // } // Add review
    public void addToWishlist(Reservation reservation) {
        wishList.add(reservation);
    } // Add reservation to wishlist

    public void addPastOrder(Reservation reservation) {
        if (reservation.getStatus().toString().equals("CONFIRMED")) {
            String message = String.format("Reservation for room %d from %s to %s has been CONFIRMED.",
                    reservation.getRoom().getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());

            sendNotification(message);
            pastOrders.add(reservation);
        } else {
            System.out.println("Order not completed yet");
        }

    } // Add past orders

    public void removePastOrder(Reservation reservation) {
        if (reservation.getStatus().toString().equals("CANCELLED")) {
            pastOrders.remove(reservation);
            String message = String.format("Reservation for room %d from %s to %s has been CANCELLED.",
                    reservation.getRoom().getRoomId(), reservation.getCheckInDate(), reservation.getCheckOutDate());
            sendNotification(message);
        } else {
            System.out.println("Order not CANCELLED yet");
        }

    } // Remove past orders

    public void removeFromWishlist(Reservation reservation) {
        wishList.remove(reservation);
    } // Remove reservation from wishlist

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public boolean loginWithId(long id, String password) {
        return super.loginWithId(id, password);
    } // For login functionality

    @Override
    public boolean loginWithEmail(String email, String password) {
        return super.loginWithEmail(email, password);
    } // For login functionality

    @Override
    public boolean loginWithName(String name, String password) {
        return super.loginWithName(name, password);
    } // For login functionality

    @Override
    public boolean loginWithPhone(long phone, String password) {
        return super.loginWithPhone(phone, password);
    } // For login functionality
    // @Override
    // public boolean login(String loginType, long personId, String email,String name, long phone, String password) {
    //     return super.login(loginType, personId, email, name, phone, password);
    // }

//     public void subscribeToRoom(Room room) {
//         subscribedRooms.put(room, room.isAvailable());
//     }
//     public void unsubscribeFromRoom(Room room) {
//         subscribedRooms.remove(room);
//     }
    // @Override
    // public void update(String message) {
    //     System.out.println("User " + super.getName() + " received update: " + message);
    // }
//     public void notifyRoomAvailabilityChange(Room room) {
//         if (subscribedRooms.containsKey(room)) {
//             boolean currentAvailability = room.isAvailable();
//             if (currentAvailability != subscribedRooms.get(room)) {
//                 subscribedRooms.put(room, currentAvailability);
//                 update("Room " + room.getDescription() + " is now " + (currentAvailability ? "available" : "unavailable"));
//             }
//         }
//     }
//     public void notifyRoomPriceChange(Room room) {
//         if (subscribedRooms.containsKey(room)) {
//             update("Room " + room.getDescription() + " price changed to " + room.getPrice());
//         }
//     }
//     public void notifyReservationChange(Reservation reservation) {
//         update("Reservation for " + reservation.getRoom().getDescription() + " is now " + reservation.getStatus());
//     }
    public void sendNotification(String message) {
        for (Notification notificationType : notifications) {
            notificationType.sendNotification(message, this, null);
        }
    }

    public void addNotification(Notification notificationType) {
        if (notificationType != null) {
            notifications.add(notificationType);
        }
    }

    public void removeNotification(Notification notificationType) {
        if (notificationType != null) {
            notifications.remove(notificationType);
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
        return ("User [userId=" + super.getPersonId() + ", name=" + super.getName() + ", email=" + super.getEmail() + ", phone=" + super.getPhone() + ", password=" + super.getPassword() + "]");
    }
}
