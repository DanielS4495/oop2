
public class BitPayment implements Payment {

    private long cardNumber;
    private double amount;

    public BitPayment(long cardNumber, double amount) {
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    @Override
    public void pay(double amount) { 
        if (amount <= 0) {
            System.out.println("Invalid amount for Bit payment.");
            return;
        }
        if (amount > this.amount) {
            System.out.println("Amount exceeds limit for Bit payment.");
            return;
        }
        System.out.println("Paid " + amount  + " via Bit.");
    }
}
