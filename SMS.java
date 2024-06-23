
public class SMS implements Notification {

    @Override
    public void sendNotification(String message, User user,Manager manager) {
        if(manager!=null){
            System.out.println("Sending sms "+ manager.getPhone()+" notification: "+ message);
        }
        else if(user!=null){
            System.out.println("Sending sms "+ user.getPhone()+" notification: "+ message);
        }
    }
}
