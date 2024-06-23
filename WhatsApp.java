
public class WhatsApp implements Notification {

    @Override
    public void sendNotification(String message, User user,Manager manager) {
        if(manager!=null){
            System.out.println("Sending whatsapp "+ manager.getPhone()+" notification: "+ message);
        }
        else if(user!=null){
            System.out.println("Sending whatsapp "+ user.getPhone()+" notification: "+ message);
        }
    }
}
