package sample.Model;

/**
 * 
 */
public abstract class HousingService extends HousingCommunalService {

    /**
     * Default constructor
     */
    HousingService() {
    }

    HousingService(int id, String name) {
        super(id, name);
        this.type = "Жилищная услуга";
    }
    /**
     * 
     */
    protected String typeHousingService;

}