package sample.Model;

/**
 * 
 */
abstract class CommunalService extends HousingCommunalService {

    /**
     * Default constructor
     */
    CommunalService() {
    }

    CommunalService(int id, String name) {
        super(id, name);
        this.type = "Коммунальная услуга";
    }

    /**
     * 
     */
    protected String typeCommunalService;

}