package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.TechnologyModel;
import kamban.com.bbva.CyArestapi.repository.TechnologyRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.TechnologyService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TechnologyServiceImpl.class);

    @Autowired
    private TechnologyRepository technologyRepository;

    private UTLGeneralService<TechnologyModel> utlGeneralService;

    public TechnologyServiceImpl() {
        this.utlGeneralService = new UTLGeneralService<>();
    }

    @Override
    public List<TechnologyModel> retrieveAllTechnology(String evidenceTypeId) {
        List<TechnologyModel> listResult = new ArrayList<>();
        List<ENTEvidence<TechnologyModel>>  listForProcess = technologyRepository.findByEvidenceTypeId(evidenceTypeId);

        for (ENTEvidence<TechnologyModel> objEnt: listForProcess) {
            TechnologyModel obj = objEnt.getSpecificFieldsDes();
            obj.setId(objEnt.getId());
            listResult.add(obj);
        }

        return listResult;
    }

    @Override
    public TechnologyModel retrieveTechnologyById(String id) {
        ENTEvidence<TechnologyModel> dataForProc = technologyRepository.findById(id).orElseThrow(RuntimeException :: new);
        TechnologyModel objResult = new TechnologyModel();
        objResult.setId(dataForProc.getId());
        objResult.setCode(dataForProc.getSpecificFieldsDes().getCode());
        objResult.setDescription(dataForProc.getSpecificFieldsDes().getDescription());
        objResult.setActive(dataForProc.getSpecificFieldsDes().isActive());
        return objResult;

    }

    @Override
    public TechnologyModel createTechnology(TechnologyModel objTechnology) {
        ENTEvidence<TechnologyModel> technologyObj = utlGeneralService.createModelEvidence(objTechnology);
        technologyObj.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DEPENDENCY_TECHNOLOGY.getValue());
        ENTEvidence<TechnologyModel> dataSaved = technologyRepository.save(technologyObj);
        dataSaved.getSpecificFieldsDes().setId(dataSaved.getId());

        return dataSaved.getSpecificFieldsDes();

    }

    @Override
    public TechnologyModel updateTechnology(String id, TechnologyModel objTechnology) {
        ENTEvidence<TechnologyModel> objForUpdate =
                technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la tecnologia"));

        objForUpdate.setSpecificFieldsDes(objTechnology);

        ENTEvidence<TechnologyModel> objResult = technologyRepository.save(objForUpdate);
        TechnologyModel objTechnologyResult = new TechnologyModel();
        objTechnologyResult.setId(objResult.getId());
        objTechnologyResult.setCode(objResult.getSpecificFieldsDes().getCode());
        objTechnologyResult.setDescription(objResult.getSpecificFieldsDes().getDescription());
        objTechnologyResult.setActive(objResult.getSpecificFieldsDes().isActive());

        return objTechnologyResult;

    }

    @Override
    public TechnologyModel disableTechnology(String id) {
        ENTEvidence<TechnologyModel> objFine =
                technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la tecnologia"));
        TechnologyModel objForUpdate = objFine.getSpecificFieldsDes();
        if (objForUpdate.isActive()){
            LOGGER.info("Tecnologia a Desactivar: " + objForUpdate.getDescription());
            objForUpdate.setActive(false);
        }else{
            LOGGER.info("Tecnologia a Activar: " + objForUpdate.getDescription());
        }

        objFine.setSpecificFieldsDes(objForUpdate);

        ENTEvidence<TechnologyModel> objResult = technologyRepository.save(objFine);
        TechnologyModel objTechnologyResult = new TechnologyModel();
        objTechnologyResult.setId(objResult.getId());
        objTechnologyResult.setCode(objResult.getSpecificFieldsDes().getCode());
        objTechnologyResult.setDescription(objResult.getSpecificFieldsDes().getDescription());
        objTechnologyResult.setActive(objResult.getSpecificFieldsDes().isActive());

        return objTechnologyResult;

    }

    @Override
    public TechnologyModel deleteTechnology(String id) {
        ENTEvidence<TechnologyModel> objFine =
                technologyRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la tecnologia"));

        technologyRepository.delete(objFine);
        ENTEvidence<TechnologyModel> objResult = technologyRepository.save(objFine);
        TechnologyModel objTechnologyResult = new TechnologyModel();
        objTechnologyResult.setId(objResult.getId());
        objTechnologyResult.setCode(objResult.getSpecificFieldsDes().getCode());
        objTechnologyResult.setDescription(objResult.getSpecificFieldsDes().getDescription());
        objTechnologyResult.setActive(objResult.getSpecificFieldsDes().isActive());
        return objTechnologyResult;
    }
}
