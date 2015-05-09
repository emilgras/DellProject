package Entities;

/**
 *
 * @author Frederik
 */
public class Budget {
    private int kno;
    private String partnerName;
    private float price;
    
    public Budget(int kno, String pn, float price){
        this.kno = kno;
        this.partnerName = pn;
        this.price = price;
    }

    public int getKno() {
        return kno;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public float getPrice() {
        return price;
    }
}
