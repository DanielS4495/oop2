
import java.util.Date;

public class CreditCardPayment implements Payment {
    private long cardNumber;
    private int cvv;
    private Date expirationDate;
    private String cardHolderName;
    private double amount;

    public CreditCardPayment(long cardNumber, int cvv, Date expirationDate, String cardHolderName, double amount) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.amount = amount;
    }

    @Override
    public void pay(double amount) {
        if(amount <= 0) {
            System.out.println("Invalid amount for Credit Card payment.");
            return;
        }
        if(amount>this.amount) {
            System.out.println("Amount exceeds limit for Credit Card payment.");
            return;
        }
        if(expirationDate.before(new Date())) {
            System.out.println("Card has expired.");
            return;
        }
        System.out.println("Paid " + amount + " via Credit Card.");
    }
}
