package sample.Model;

/**
 * 
 */
public abstract class EnergySupply extends CommunalService {

    /**
     * Default constructor
     */
    public EnergySupply() {
    }

    public EnergySupply(int id, String name) {
        super(id, name);
        this.typeCommunalService = "Энергоснабжение";
    }

    /**
     * 
     */
    protected String typeEnergySupply;

    /**
     * 
     */
    public String supply() {
        // TODO implement here
        return "Обеспечивает энергией";
    }

}