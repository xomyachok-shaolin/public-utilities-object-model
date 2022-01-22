package sample.Model;

/**
 * 
 */
public abstract class Security extends HousingService {

    /**
     * Default constructor
     */
    public Security() {
    }

    public Security(int id, String name) {
        super(id, name);
        this.typeHousingService = "Безопасность";
    }

    /**
     * 
     */
    protected String typeSecurity;

}