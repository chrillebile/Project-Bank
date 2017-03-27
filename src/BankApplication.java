import java.util.Scanner;

/**
 * Created by chrillebile on 2017-02-14.
 */
public class BankApplication {
    
    private Bank bank = new Bank();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        BankApplication application = new BankApplication();
        application.run();
    }

    /** Kör appen */
    private void run(){
        while(true){
            printMenu();
            System.out.println("Val: ");
            switch (scanner.next()){
                case "1":
                    getAccountByHolderId();
                    break;
                case "2":
                    getHolderByName();
                    break;
                case "3":
                    depositAmount();
                    break;
                case "4":
                    withdrawAmount();
                    break;
                case "5":
                    transferAmount();
                    break;
                case "6":
                    newBankAccount();
                    break;
                case "7":
                    removeBankAccount();
                    break;
                case "8":
                    printAccounts();
                    break;
                case "9":
                    System.out.println("Hejdå");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Vänligen välj mellan valen 1 till 9");
                    break;
            }
        }
    }

    /**
     * Skriver ut menyn
     */
    private void printMenu(){
        System.out.println("---------------------------------------------");
        System.out.println("1. Hitta konto utifrån innehavare");
        System.out.println("2. Sök kontoinnehavare utifrån (del av) namn");
        System.out.println("3. Sätt in");
        System.out.println("4. Ta ut");
        System.out.println("5. Överföring");
        System.out.println("6. Skapa konto");
        System.out.println("7. Ta bort konto");
        System.out.println("8. Skriv ut konton");
        System.out.println("9. Avsluta");
        System.out.println("---------------------------------------------");
    }

    /**
     * Menyval 1
     */
    private void getAccountByHolderId(){
        System.out.println("Kundens ID: ");
        long holderId = scanner.nextLong();
        for (BankAccount acc: bank.findAccountsForHolder(holderId)) {
            System.out.println(acc);
        }
    }

    /**
     * Menyval 2
     */
    private void getHolderByName(){
        System.out.println("Kundens namn: ");
        String holderName = scanner.useDelimiter(System.lineSeparator()).next().toLowerCase();
        for (Customer customer: bank.findByPartOfName(holderName)) {
            System.out.println(customer);
        }
    }

    /**
     * Menyval 3
     */
    private void depositAmount(){
        BankAccount depositAccount = getBankAccount();
        if(depositAccount == null){
            return;
        }
        double amount = getAmount();
        if (amount != -1){
            depositAccount.deposit(amount);
            System.out.println(depositAccount);
        }
    }

    /**
     * Menyval 4
     */
    private void withdrawAmount(){
        BankAccount withdrawAccount = getBankAccount();
        if(withdrawAccount == null){
            return;
        }
        System.out.println("Konto " + withdrawAccount.getAccountNumber() + " har " + withdrawAccount.getAmount() + "kr.");
        double amount = getAmount();
        amount = withdrawFromAccount(withdrawAccount, amount);
        if (amount != -1) {
            System.out.println(withdrawAccount);
        }
    }

    /**
     * Menyval 5
     */
    private void transferAmount(){
        System.out.println("Från:");
        BankAccount fromAccount = getBankAccount();
        if(fromAccount == null){
            return;
        }
        System.out.println("Till:");
        BankAccount toAccount = getBankAccount();
        if(toAccount == null){
            return;
        }
        double amount = getAmount();
        amount = withdrawFromAccount(fromAccount, amount);
        if(amount != -1) {
            toAccount.deposit(amount);
            System.out.println(fromAccount + "\n" + toAccount);
        }
    }

    /**
     * Menyval 6
     */
    private void newBankAccount(){
        System.out.println("Namn: ");
        String holderName = scanner.useDelimiter(System.lineSeparator()).next();
        System.out.println("ID: ");
        long holderId = scanner.nextLong();
        int accNumber = bank.addAccount(holderName, holderId);
        System.out.println("Konto skapat: " + accNumber);
    }

    /**
     * Menyval 7
     */
    private void removeBankAccount(){
        BankAccount rmBankAccount = getBankAccount();
        if(rmBankAccount != null) {
            bank.removeAccount(rmBankAccount.getAccountNumber());
        }
    }

    /**
     * Menyval 8
     */
    private void printAccounts(){
        for(BankAccount acc: bank.getAllAccounts()){
            if(acc != null) {
                System.out.println(acc);
            }
        }
    }

    /**
     * Hämta bankkonto från konto nummert
     */
    private BankAccount getBankAccount(){
        System.out.println("Konto nummer: ");
        int accountNumber = scanner.nextInt();
        while(bank.findByNumber(accountNumber) == null){
            System.out.println("Kontonummer: " + accountNumber+ " hittades inte i systemet vänligen försök igen eller avsluta med -1. \n Konto nr: ");
            accountNumber = scanner.nextInt();
            if(accountNumber == -1){
                return null;
            }
        }
        return bank.findByNumber(accountNumber);
    }

    /**
     * Hämtar inmatad summa och kontrollerar den
     */
    private double getAmount(){
        System.out.println("Summa: ");
        double amount = scanner.nextDouble();
        if(amount == -1){
            return -1;
        }
        while (amount<=0){
            System.out.println("Vänligen ange en summa större än 0 eller avsluta med -1: ");
            amount = scanner.nextDouble();
            if(amount == -1){
                return -1;
            }
        }
        return amount;
    }

    /**
     * Kontrollerar att det man vill ta ut går att ta ut.
     */
    private double withdrawFromAccount(BankAccount account, double amount){
        while(amount > account.getAmount()){
            System.out.println("Bankkonto: " + account.getAccountNumber() + " har " + account.getAmount() + "kr. Vänligen ange annan annan summa eller avsluta med -1");
            amount = getAmount();
            if(amount == -1){
                return -1;
            }
        }
        account.withdraw(amount);
        return amount;
    }
}