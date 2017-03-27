/**
 * Created by chrillebile on 2017-02-14.
 */
public class Customer {

    private String name;
    private long idNr;
    private static int customers;
    private int customerNr;

    /**
     * Skapar en kund (kontoinnehavare) med namnet ’name’ och id-nummer ’idNr’.
     * Kunden tilldelas också ett unikt kundnummer (customerNr).
     */
    public Customer(String name, long idNr){
        this.name = name;
        this.idNr = idNr;
        customers++;
        customerNr = customers;
    }

    /** Tar reda på kundens namn. */
    public String getName(){
        return name;
    }

    /** Tar reda på kundens personnummer. */
    public long getIdNr(){
        return idNr;
    }

    /** Tar reda på kundens kundnummer. */
    public int getCustomerNr(){
        return customerNr;
    }

    /** Returnerar en strängbeskrivning av kunden. */
    public String toString(){
        return name + ", id " + idNr + ", Kund nummer " + getCustomerNr();
    }

}
