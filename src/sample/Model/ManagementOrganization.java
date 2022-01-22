package sample.Model;


import java.util.ArrayList;

/**
 * 
 */
public class ManagementOrganization {

    public int counterID = 1;

    /**
     * Default constructor
     */
    public ManagementOrganization() {
    }

    /**
     * 
     */
    private License license;

    /**
     *
     */
    private ArrayList<PackageHousingCommunalServices> packages;

    /**
     *
     */
    private String name;

    public String getNumberINN() {
        return numberINN;
    }

    public void setNumberINN(String inn) {
        this.numberINN = inn;
    }

    private String numberINN;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public ManagementOrganization(int id, String inn, String name) {
        this.id = id;
        this.numberINN = inn; this.name = name; license = null; packages = new ArrayList<>();
    }

    /**
     * @return
     */
    public License getLicense() {
        // TODO implement here
        return license;
    }

    /**
     * @param l
     */
    public void setLicense(License l) {
        // TODO implement here
        license = l;
    }

    /**
     * @param packageHCS
     */
    public void addPackageHCS(PackageHousingCommunalServices packageHCS) {
        // TODO implement here
        packages.add(packageHCS);
    }

    /**
     * @param packageHCS
     */
    public void removePackageHCS(PackageHousingCommunalServices packageHCS) {
        // TODO implement here
        packages.remove(packageHCS);
    }

    /**
     * @return
     */
    public ArrayList<PackageHousingCommunalServices> getPackages() {
        // TODO implement here
        return packages;
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