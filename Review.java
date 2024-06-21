
import java.util.Date;

public class Review {

    private final User user;
    private final double rating;
    private final String comment;
    private final Date date;

    public Review(User user, double rating, String comment, Date date) {
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

}
