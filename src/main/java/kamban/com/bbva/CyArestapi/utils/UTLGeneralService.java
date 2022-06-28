package kamban.com.bbva.CyArestapi.utils;

import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;

import java.util.Date;

import static kamban.com.bbva.CyArestapi.utils.UTLConstants.CODE_DOCUMENT_DISCIPLINA;

public class UTLGeneralService<T> {

    public ENTEvidence<T> createModelEvidence(T dataEvidence){
        ENTEvidence<T> dataReturn = new ENTEvidence<T>();

        dataReturn.setEvidenceDate(new Date());
        dataReturn.setEvidenceGroupId("000");
        dataReturn.setSpecificFieldsDes(dataEvidence);

        return dataReturn;
    }
}
