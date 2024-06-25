
public class PayPalPayment implements Payment {

    private long cardNumber;
    private double amount;

    public PayPalPayment(long cardNumber, double amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    @Override
    public void pay(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount for PayPal payment.");
            return;
        }
        if (amount > this.amount) {
            System.out.println("Amount exceeds limit for PayPal payment.");
            return;
        }
        System.out.println("Paid " + amount + "with Number:" + cardNumber + " via PayPal.");
    }
}
