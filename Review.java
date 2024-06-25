
import java.util.Date;

public class Review {

    private final User user;
    private final double rating;
    private final String comment;
    private final Date date;

    public Review(User user, double rating, String comment, Date date) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public double getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Review: " + "User: " + user + "Rating: " + rating + ", Comment: " + comment + ", Date: " + date;
    }

}
