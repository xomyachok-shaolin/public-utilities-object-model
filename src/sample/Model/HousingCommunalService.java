package sample.Model;

/**
 * 
 */
public abstract class HousingCommunalService implements IProvidingService {

    /**
     * Default constructor
     */
    HousingCommunalService() {
    }

    HousingCommunalService(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private String name;
    private int id;
    protected String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     */
    @Override
    public void provide() {
        // TODO implement here
    }

}