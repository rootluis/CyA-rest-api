package kamban.com.bbva.CyArestapi.utils;

import kamban.com.bbva.CyArestapi.repository.entity.ENTEvidence;

import java.util.Date;

public class UTLGeneralService<T> {

    public ENTEvidence<T> createModelEvidence(T dataEvidence) {
        ENTEvidence<T> dataReturn = new ENTEvidence<T>();

        dataReturn.setEvidenceDate(new Date());
        dataReturn.setEvidenceGroupId("000");
        dataReturn.setSpecificFieldsDes(dataEvidence);

        return dataReturn;
    }
}
