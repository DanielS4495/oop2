
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends Person implements Observer{

    // private final long userId;
    // private String name;
    // private String email;
    // private String password;
    private WishList wishList;
    private List<Reservation> pastOrders;
    // private List<Review> reviews; //need to do only in hotel
    private Map<Room, Boolean> subscribedRooms;
    // private boolean login;

    public User(long userId, String name, String email, String password) {
        // this.userId = userId;
        // this.name = name;
        // this.email = email;
        // this.password = password;
        // Super(userId, name, email, password);
        super(userId, name, email, password);
        this.wishList = new WishList();
        this.pastOrders = new ArrayList<>();
        // this.reviews = new ArrayList<>();
        this.wishList = new WishList();
        // this.login = false;
        this.subscribedRooms = new HashMap<>();
    }

    // public long getUserId() {
    //     return userId;
    // }

    // public String getPassword() {
    //     return password;
    // }

    // public String getName() {
    //     return name;
    // }

    // public String getEmail() {
    //     return email;
    // }

    // public boolean getLogin() {
    //     return login;
    // }

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

    public void setLogin(boolean login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean loginWithEmail(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            return true;
        }
        return false;
    } // For login functionality

    public boolean loginWithName(String name, String password) {
        if (this.name.equals(name) && this.password.equals(password)) {
            return true;
        }
        return false;
    } // For login functionality

//     public void subscribeToRoom(Room room) {
//         subscribedRooms.put(room, room.isAvailable());
//     }

//     public void unsubscribeFromRoom(Room room) {
//         subscribedRooms.remove(room);
//     }

    @Override
    public void update(String message) {
        System.out.println("User " + name + " received update: " + message);
    }

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
}
