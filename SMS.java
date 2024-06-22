
public class SMS implements Notification {

    @Override
    public void sendNotification(String message, User user) {
        System.out.println("Sending email notification to " + user.getName() + ": " + "message");
    }
}
