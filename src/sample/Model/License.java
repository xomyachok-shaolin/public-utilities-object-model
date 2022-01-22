package sample.Model;

import java.util.Date;

/**
 * 
 */
public class License {

    /**
     * Default constructor
     * @param i
     */
    public License(String i) {
        numberINN = i;
        dateOrder = new Date();
    }

    /**
     * 
     */
    private String numberINN;

    /**
     * 
     */
    private Date dateOrder;


    /**
     * @param i
     */
    public void setNumberINN(String i) {
        // TODO implement here
        numberINN = i;
    }

    /**
     * @return
     */
    public String getNumberINN() {
        // TODO implement here
        return numberINN;
    }

    /**
     * @param d
     */
    public void setDateOrder(Date d) {
        // TODO implement here
        dateOrder = d;
    }

    /**
     * @return
     */
    public Date getDateOrder() {
        // TODO implement here
        return dateOrder;
    }

    @Override
    public String toString() {
        return "ИНН: " + numberINN + "\nДата приказа: " + dateOrder;
    }
}