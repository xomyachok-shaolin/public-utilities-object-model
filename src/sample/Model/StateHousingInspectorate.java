package sample.Model;

import java.util.ArrayList;

/**
 * 
 */
public class StateHousingInspectorate {

    /**
     * Default constructor
     */
    public StateHousingInspectorate() {
        licenses = new ArrayList<License>();
    }

    /**
     * 
     */
    private ArrayList<License> licenses;


    /**
     * @param newLicense
     */
    public void addLicense(License newLicense) {
        // TODO implement here
        licenses.add(newLicense);
    }

    /**
     * @return
     */
    public ArrayList<License> getLicenses() {
        // TODO implement here
        return licenses;
    }

    /**
     * @param l
     */
    public void removeLicense(License l) {
        // TODO implement here
        licenses.remove(l);
    }

}