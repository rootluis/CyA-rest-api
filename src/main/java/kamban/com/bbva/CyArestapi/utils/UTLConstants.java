package kamban.com.bbva.CyArestapi.utils;

public enum UTLConstants {
    CODE_DOCUMENT_DISCIPLINA("BD-MX-AA-CYAOEVDS-001"),
    CODE_DOCUMENT_USER("BD-MX-AA-CYAOUSR-001"),
    CODE_DOCUMENT_AREA("BD-MX-AA-CYAOARA-001"),
    CODE_DOCUMENT_CONTINUITY("BD-MX-AA-CYAOCNTD-001"),
    CODE_DOCUMENT_SPRINT("BD-MX-AA-CYAOSPT-001"),
    CODE_DOCUMENT_STAGEDD("BD-MX-AA-CYAODDST-001"),
    CODE_DOCUMENT_PROCESS_STATUS("BD-MX-AA-CYAOPROCST-001"),
    CODE_DOCUMENT_DEPENDENCY_STATUS("BD-MX-AA-CYAODEPST-001"),
    CODE_DOCUMENT_DEPENDENCY_TECHNOLOGY("BD-MX-AA-CYAOTEC-001")
    ;

    private String value;

    UTLConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    //    public static final String CODE_DOCUMENT_DISCIPLINA="BD-MX-AA-CYAOEVDS-001";
//    public static final String CODE_DOCUMENT_USER="BD-MX-AA-CYAOUSR-001";


}
