package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.model.ErrorModel;
import kamban.com.bbva.CyArestapi.model.ResponseDataModel;
import kamban.com.bbva.CyArestapi.model.TechnologyModel;
import kamban.com.bbva.CyArestapi.repository.EvidenceRepository;
import kamban.com.bbva.CyArestapi.repository.TechnologyRepository;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.TechnologyService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TechnologyServiceImpl implements TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    private UTLGeneralService<TechnologyModel> _utlGeneralService;

    public TechnologyServiceImpl() {
        this._utlGeneralService = new UTLGeneralService<>();
    }

    @Override
    public ResponseDataModel<List<TechnologyModel>> retrieveAll() {
        ResponseDataModel<List<TechnologyModel>> dataReturn = new ResponseDataModel<>();
        dataReturn.setResult(new ArrayList<>());

        List<ENTEvidence<TechnologyModel>> listSprint = technologyRepository.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DEPENDENCY_TECHNOLOGY.getValue());

        if (listSprint.isEmpty()) {
            dataReturn.setCode("1");
            dataReturn.setMsn("No se han localizado datos");
        } else {
            for (ENTEvidence<TechnologyModel> sprintEnt : listSprint) {
                sprintEnt.getSpecificFieldsDes().setId(sprintEnt.getId());
                dataReturn.getResult().add(sprintEnt.getSpecificFieldsDes());
            }

            dataReturn.setCode("0");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<TechnologyModel> retrieveById(String id) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        Optional<ENTEvidence<TechnologyModel>> sprintEnt = technologyRepository.findById(id);
        if (sprintEnt.isPresent()) {
            sprintEnt.get().getSpecificFieldsDes().setId(sprintEnt.get().getId());
            dataReturn.setResult(sprintEnt.get().getSpecificFieldsDes());

            dataReturn.setCode("0");
        } else {
            dataReturn.setCode("1");
            dataReturn.setMsn("No se encontro el elemento indicado");
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<TechnologyModel> create(TechnologyModel objTechnology) {
        ResponseDataModel<TechnologyModel> dataReturn = validDataToCreate(objTechnology);

        if (dataReturn.getCode().equals("0")) {
            objTechnology.setActive(true);
            ENTEvidence<TechnologyModel> sprintToSave = _utlGeneralService.createModelEvidence(objTechnology);
            sprintToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DEPENDENCY_TECHNOLOGY.getValue());

            ENTEvidence<TechnologyModel> sprintModelENTEvidence = technologyRepository.save(sprintToSave);
            sprintModelENTEvidence.getSpecificFieldsDes().setId(sprintModelENTEvidence.getId());

            dataReturn.setResult(sprintModelENTEvidence.getSpecificFieldsDes());
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<TechnologyModel> alter(TechnologyModel sprintData) {
        ResponseDataModel<TechnologyModel> dataReturn = validDataToAlter(sprintData);

        if (dataReturn.getCode().equals("0")) {
            Optional<ENTEvidence<TechnologyModel>> entToAlter = technologyRepository.findById(sprintData.getId());

            if (entToAlter.isPresent()) {
                sprintData.setId(null);
                entToAlter.get().setSpecificFieldsDes(sprintData);

                ENTEvidence<TechnologyModel> entToAlterSaved = technologyRepository.save(entToAlter.get());
                entToAlterSaved.getSpecificFieldsDes().setId(entToAlterSaved.getId());

                dataReturn.setResult(entToAlterSaved.getSpecificFieldsDes());
            } else {
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el Sprint indicado");
            }
        }

        return dataReturn;
    }

    @Override
    public ResponseDataModel<TechnologyModel> disable(String id) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        if (id == null || id.isEmpty()) {
            dataReturn.setCode("1");
            dataReturn.setMsn("Es necesario indicar el id del Sprint a desbilitar");
        } else {
            Optional<ENTEvidence<TechnologyModel>> sprintENTEvidence = technologyRepository.findById(id);

            if (sprintENTEvidence.isPresent()) {
                sprintENTEvidence.get().getSpecificFieldsDes().setActive(false);

                ENTEvidence<TechnologyModel> sprintSaved = technologyRepository.save(sprintENTEvidence.get());
                sprintSaved.getSpecificFieldsDes().setId(sprintSaved.getId());

                dataReturn.setResult(sprintSaved.getSpecificFieldsDes());
                dataReturn.setCode("0");
            } else {
                dataReturn.setCode("1");
                dataReturn.setMsn("No existe el Sprint indicada");
            }
        }

        return dataReturn;
    }


    private ResponseDataModel<TechnologyModel> validDataToCreate(TechnologyModel objTechnology) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        if (objTechnology != null) {
            List<ErrorModel> errors = new ArrayList<>();

            if (objTechnology.getCode().isEmpty()) {
                ErrorModel errorName = new ErrorModel();
                errorName.setMessage("Es necesario indicar el nombre del Sprint");

                errors.add(errorName);
            }

            if (errors.size() > 0) {
                dataReturn.setCode("2");
                dataReturn.setMsn("Favor de validar los siguientes comentarios:");
                dataReturn.setErrors(errors);
            } else {
                dataReturn.setCode("0");
            }

        } else {
            dataReturn.setCode("1");
            dataReturn.setMsn("No se encontraron datos a procesar");
        }

        return dataReturn;
    }

    private ResponseDataModel<TechnologyModel> validDataToAlter(TechnologyModel objTechnology) {
        ResponseDataModel<TechnologyModel> dataReturn = new ResponseDataModel<>();

        if (objTechnology != null) {
            if (objTechnology.getId().isEmpty()) {
                dataReturn.setCode("1");
                dataReturn.setMsn("El indicador del Sprint es incorrecto");
            } else {
                List<ErrorModel> errors = new ArrayList<>();

                if (objTechnology.getCode().isEmpty()) {
                    ErrorModel errorName = new ErrorModel();
                    errorName.setMessage("Es necesario indicar el nombre del Sprint");

                    errors.add(errorName);
                }

                if (errors.size() > 0) {
                    dataReturn.setCode("2");
                    dataReturn.setMsn("Favor de validar los siguientes comentarios:");
                    dataReturn.setErrors(errors);
                } else {
                    dataReturn.setCode("0");
                }
            }

        } else {
            dataReturn.setCode("1");
            dataReturn.setMsn("No se encontraron datos a procesar");
        }

        return dataReturn;
    }

}
