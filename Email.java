
public class Email implements Notification {

    @Override
    public void sendNotification(String message, User user,Manager manager) {
        if(manager!=null){
            System.out.println("Sending email "+ manager.getName()+" notification: "+ message);
        }
       else if(user!=null){
            System.out.println("Sending email "+ user.getName()+" notification: "+ message);
        }
       
    }
}
