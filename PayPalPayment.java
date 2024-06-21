
public class PayPalPayment implements Payment {

    private long cardNumber;
    private double amount;

    public PayPalPayment(long cardNumber, double amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    @Override
    public void pay(double amount) { //create a method so that the user can ask if he wants to pay and conform the payment
        // Logic to process PayPal payment
        if (amount <= 0) {
            System.out.println("Invalid amount for Credit Card payment.");
            return;
        }
        if (amount > this.amount) {
            System.out.println("Amount exceeds limit for Credit Card payment.");
            return;
        }
        System.out.println("Paid " + amount + "with Number:" + cardNumber + " via PayPal.");
    }
}
