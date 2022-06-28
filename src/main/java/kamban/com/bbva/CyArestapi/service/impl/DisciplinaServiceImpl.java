package kamban.com.bbva.CyArestapi.service.impl;

import kamban.com.bbva.CyArestapi.controller.DisciplinaRestController;
import kamban.com.bbva.CyArestapi.model.MDLDisciplina;
import kamban.com.bbva.CyArestapi.repository.dao.DAOEvidence;
import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;
import kamban.com.bbva.CyArestapi.service.DisciplinaService;
import kamban.com.bbva.CyArestapi.utils.UTLConstants;
import kamban.com.bbva.CyArestapi.utils.UTLGeneralService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DisciplinaServiceImpl implements DisciplinaService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisciplinaRestController.class);

    @Autowired
    private DAOEvidence _daoEvidence;
    private UTLGeneralService<MDLDisciplina> _utlGeneralService;

    public DisciplinaServiceImpl() {
        this._utlGeneralService = new UTLGeneralService<>();
    }

    @Override
    public String createDisciplina(MDLDisciplina disciplinaData) {
        String dataReturn = new String();

        ENTEvidence<MDLDisciplina> dataToSave = this._utlGeneralService.createModelEvidence(disciplinaData);
        dataToSave.setEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DISCIPLINA.getValue());

        ENTEvidence<MDLDisciplina> dataSaved = _daoEvidence.save(dataToSave);

        if (dataSaved != null) {
            dataReturn = dataSaved.getId();
        }

        return dataReturn;
    }

    @Override
    public List<MDLDisciplina> retrieveAllDisciplina() {
        List<MDLDisciplina> dataReturn = new ArrayList<>();
        List<ENTEvidence<MDLDisciplina>> listEvidence = _daoEvidence.findByEvidenceTypeId(UTLConstants.CODE_DOCUMENT_DISCIPLINA.getValue());

        if (listEvidence != null && listEvidence.size() > 0) {
            for (ENTEvidence<MDLDisciplina> entEvidence : listEvidence) {
                if (entEvidence.getSpecificFieldsDes() != null) {
                    entEvidence.getSpecificFieldsDes().setId(entEvidence.getId());
                    dataReturn.add(entEvidence.getSpecificFieldsDes());
                }
            }
        }

        return dataReturn;
    }

    @Override
    public MDLDisciplina retrieveByName(String name) {
        ENTEvidence<MDLDisciplina> evidence = _daoEvidence.findByName(UTLConstants.CODE_DOCUMENT_DISCIPLINA.getValue(), name);

        if (evidence != null && evidence.getSpecificFieldsDes() != null) {
            evidence.getSpecificFieldsDes().setId(evidence.getId());
            return evidence.getSpecificFieldsDes();
        } else {
            return null;
        }
    }

    @Override
    public boolean existDisciplina(String name) {
        ENTEvidence<MDLDisciplina> evidence = _daoEvidence.findByName(UTLConstants.CODE_DOCUMENT_DISCIPLINA.getValue(), name);

        if (evidence != null && evidence.getSpecificFieldsDes() != null) {
            return true;
        } else {
            return false;
        }
    }


}
