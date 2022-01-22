package sample.Model;

/**
 * 
 */
public abstract class Repair extends HousingService {

    /**
     * Default constructor
     */
    public Repair() {
    }

    public Repair(int id, String name) {
        super(id, name);
        this.typeHousingService = "Ремонт";
    }

    /**
     * 
     */
    protected String typeRepair;

}