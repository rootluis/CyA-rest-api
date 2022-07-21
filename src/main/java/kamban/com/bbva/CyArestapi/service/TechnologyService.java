package kamban.com.bbva.CyArestapi.service;

import kamban.com.bbva.CyArestapi.model.TechnologyModel;

import java.util.List;

public interface TechnologyService {

    public List<TechnologyModel> retrieveAllTechnology(String evidenceTypeId);

    public TechnologyModel retrieveTechnologyById(String id);

    public TechnologyModel createTechnology(TechnologyModel objTechnology);

    public TechnologyModel updateTechnology(String id, TechnologyModel sprintData);

    public TechnologyModel disableTechnology(String id);

    public TechnologyModel deleteTechnology(String id);


}
