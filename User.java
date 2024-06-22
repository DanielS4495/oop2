
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Person {

    // private final long userId;
    // private String name;
    // private String email;
    // private String password;
    private WishList wishList;
    private List<Reservation> pastOrders;
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
        this.wishList = new WishList();
        this.pastOrders = new ArrayList<>();
        // this.reviews = new ArrayList<>();
        this.wishList = new WishList();
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

    // public List<Review> getReviews() {
    //     return reviews;
    // } // Get reviews
    public List<Reservation> getPastOrders() {
        return this.pastOrders;
    } // Get past orders

    public WishList getWishList() {
        return wishList;
    } // Get wishlist
    // public void addReview(String reviewerName, double rating, String comment,Date date) {
    //     Review review = new Review(reviewerName, rating, comment, date);
    //     reviews.add(review);
    // } // Add review

    public void addToWishlist(Reservation reservation) {
        wishList.addReservation(reservation);
    } // Add reservation to wishlist

    public void addPastOrder(Reservation reservation) {
        pastOrders.add(reservation);
    } // Add past orders

    public void removePastOrder(Reservation reservation) {
        if (pastOrders.contains(reservation) && reservation.getStatus().equals("Completed")) {
            System.out.println("Order removed successfully");
        } else {
            System.out.println("Order not found or not completed yet");
        }
        pastOrders.remove(reservation);
    } // Remove past orders

    public void removeFromWishlist(Reservation reservation) {
        wishList.removeReservation(reservation);
    } // Remove reservation from wishlist

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

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
    public void notification(String message) {
        for (Notification service : notifications) {
            service.sendNotification(message, this);
        }
    }

    public void addNotification(Notification service) {
        notifications.add(service);
    }

    public void removeNotification(Notification service) {
        notifications.remove(service);
    }

    public void sendCancellationNotification(int roomId, Date checkIn, Date checkOut) {
        for (Notification service : notifications) {
            String message = String.format("Reservation for room %d canceled from %s to %s.",
                    roomId, checkIn, checkOut);
            service.sendNotification(message, this);
        }
    }
}
