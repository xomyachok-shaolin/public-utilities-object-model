package sample.Model;

import java.util.ArrayList;

/**
 * 
 */
public class PackageHousingCommunalServices {

    public int counterID = 1;

    /**
     * Default constructor
     */
    public PackageHousingCommunalServices() {
    }

    /**
     * 
     */
    private ArrayList<HousingCommunalService> packageHCS;

    /**
     * 
     */
    private int id;

    /**
     * 
     */
    private String name;

    public PackageHousingCommunalServices(int id, String name) {
        this.id = id; this.name = name; packageHCS = new ArrayList<>();
    }


    /**
     * @param newHCService
     */
    public boolean addHCS(HousingCommunalService newHCService) {
        // TODO implement here
        for (HousingCommunalService s : packageHCS) {
            if (s.getName().equals(newHCService.getName()))
                return false;
        }
        packageHCS.add(newHCService);
        return true;
    }

    /**
     * @return
     */
    public ArrayList<HousingCommunalService> getPackageHCS() {
        // TODO implement here
        return packageHCS;
    }

    /**
     * @param s
     */
    public void removeHCS(HousingCommunalService s) {
        // TODO implement here
        packageHCS.remove(s);
    }

    /**
     * @return
     */
    public int getID() {
        // TODO implement here
        return id;
    }

    /**
     * @param i
     */
    public void setID(int i) {
        // TODO implement here
        id = i;
    }

    /**
     * @param n
     */
    public void setName(String n) {
        // TODO implement here
        name = n;
    }

    /**
     * @return
     */
    public String getName() {
        // TODO implement here
        return name;
    }

}