import java.util.ArrayList;

/**
 * Created by chrillebile on 2017-02-14.
 */
public class Bank {

    private ArrayList<BankAccount> bankAccounts = new ArrayList<BankAccount>();
    
    /** Skapar en ny bank utan konton. */
    public Bank(){
        bankAccounts = new ArrayList<BankAccount>();
    }

    /**
     * Öppna ett nytt konto i banken. Om det redan finns en kontoinnehavare
     * med de givna uppgifterna ska inte en ny Customer skapas, utan istället
     * den befintliga användas. Det nya kontonumret returneras.
     */
    public int addAccount(String holderName, long idNr){
        Customer existingCustomer = findHolder(idNr);
        if(existingCustomer != null){
            bankAccounts.add(new BankAccount(existingCustomer));
        }else {
            bankAccounts.add(new BankAccount(holderName, idNr));
        }
        return bankAccounts.get((bankAccounts.size()-1)).getAccountNumber();
    }

    /**
     * Returnerar den kontoinnehavaren som har det givna id-numret,
     * eller null om ingen sådan finns.
     */
    public Customer findHolder(long idNr){
        for (BankAccount acc: bankAccounts) {
            if(acc.getHolder().getIdNr() == idNr){
                return acc.getHolder();
            }
        }
        return null;
    }


    /**
     * Söker upp och returnerar bankkontot med kontonummer ’accountNumber’.
     * Returnerar null om inget sådant konto finns.
     */
    public BankAccount findByNumber(int accountNumber){
        for (BankAccount acc: bankAccounts) {
            if(acc.getAccountNumber() == accountNumber){
                return acc;
            }
        }
        return null;
    }

    /**
     * Söker upp alla bankkonton som innehas av kunden med id-nummer ’idNr’.
     * Kontona returneras i en lista. Kunderna antas ha unika id-nummer.
     */
    public ArrayList<BankAccount> findAccountsForHolder(long idNr){
        ArrayList<BankAccount> holderAccounts = new ArrayList<BankAccount>();
        for (BankAccount acc: bankAccounts) {
            if(acc.getHolder().getIdNr() == idNr){
                holderAccounts.add(acc);
            }
        }
        return holderAccounts;
    }


    /**
     * Söker upp kunder utifrån en sökning på namn eller del av namn. Alla
     * personer vars namn innehåller strängen ’namePart’ inkluderas i
     * resultatet, som returneras som en lista. Samma person kan förekomma
     * flera gånger i resultatet. Sökningen är "case insensitive", det vill
     * säga gör ingen skillnad på stora och små bokstäver.
     */
    public ArrayList<Customer> findByPartOfName(String namePart){
        ArrayList<Customer> foundCustomers = new ArrayList<Customer>();
        for (BankAccount acc: bankAccounts) {
            if(acc.getHolder().getName().toLowerCase().contains(namePart)){
                foundCustomers.add(acc.getHolder());
            }
        }
        return foundCustomers;
    }          

    /**
     * Tar bort konto med nummer ’number’ från banken. Returnerar true om
     * kontot fanns (och kunde tas bort), annars false.
     */
    public boolean removeAccount(int number){
        for (BankAccount acc: bankAccounts) {
            if(acc.getAccountNumber() == number){
                bankAccounts.remove(acc);
                return true;
            }
        }
        return false;
    }

    /**
     * Returnerar en lista innehållande samtliga bankkonton i banken.
     * Listan är sorterad på kontoinnehavarnas namn.
     */
    public ArrayList<BankAccount> getAllAccounts(){
        ArrayList<BankAccount> sortedBankAccounts = new ArrayList<BankAccount>();
        for (BankAccount acc: bankAccounts) {
            int position = 0;
            for (BankAccount sortAcc: sortedBankAccounts) {
                if(acc.getHolder().getName().toLowerCase().compareTo(sortAcc.getHolder().getName().toLowerCase())<0){
                    break;
                }else {
                    position++;
                }
            }
            sortedBankAccounts.add(position, acc);
        }
        return sortedBankAccounts;
    }

}
