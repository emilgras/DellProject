/*
 Frederik har arbejdet i den her klasse
 */
package Entities;

/**
 *
 * @author Frederik
 */
public class Budget {
    int kno;
    String partnerName;
    float price;
    
    public Budget(int kno, String pn, float price){
        this.kno = kno;
        this.partnerName = pn;
        this.price = price;
    }

    public int getKno() {
        return kno;
    }

    public void setKno(int kno) {
        this.kno = kno;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String PartnerNavn) {
        this.partnerName = PartnerNavn;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Budget{" + "kno=" + kno + ", partnerName=" + partnerName + ", price=" + price + '}';
    }
    
    
}
