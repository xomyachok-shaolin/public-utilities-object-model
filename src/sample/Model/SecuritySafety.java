package sample.Model;

/**
 * 
 */
public abstract class SecuritySafety extends Security {

    /**
     * Default constructor
     */
    public SecuritySafety() {
    }

    public SecuritySafety(int id, String name) {
        super(id, name);
        this.typeSecurity = "Охранная безопасность";
    }
    /**
     * 
     */
    protected String typeSecuritySafety;

    /**
     * 
     */
    public String guard() {
        // TODO implement here
        return "Защищает от злоумышленников";
    }

}