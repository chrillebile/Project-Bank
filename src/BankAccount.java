/**
 * Created by chrillebile on 2017-02-14.
 */
public class BankAccount {

    private Customer holder;
    private static int accounts;
    private int accountNumber;
    private double amount;


    /**
     * Skapar ett nytt bankkonto åt en innehavare med namn ’holderName’ och
     * id ’holderId’. Kontot tilldelas ett unikt kontonummer och innehåller
     * inledningsvis 0 kr.
     */
    public BankAccount(String holderName, long holderId){
        this(new Customer(holderName, holderId));
    }

    /**
     * Skapar ett nytt bankkonto med innehavare ’holder’. Kontot tilldelas
     * ett unikt kontonummer och innehåller inledningsvis 0 kr.
     */
    public BankAccount(Customer holder){
        this.holder = holder;
        accounts++;
        accountNumber = accounts;
        amount = 0;
    }

    /** Tar reda på kontots innehavare. */
    public Customer getHolder(){
        return holder;
    }

    /** Tar reda på det kontonummer som identifierar detta konto. */
    public int getAccountNumber(){
        return accountNumber;
    }

    /** Tar reda på hur mycket pengar som finns på kontot. */
    public double getAmount(){
        return amount;
    }

    /** Sätter in beloppet ’amount’ på kontot. */
    public void deposit(double amount){
        this.amount += amount;
    }

    /**
     * Tar ut beloppet ’amount’ från kontot. Om kontot saknar täckning
     * blir saldot negativt.
     */
    public void withdraw(double amount){
        this.amount -= amount;
    }

    /** Returnerar en strängrepresentation av bankkontot. */
    public String toString(){
        return "Konto " + getAccountNumber() + " (" + holder  + "): " + amount + "kr";
    }
}
