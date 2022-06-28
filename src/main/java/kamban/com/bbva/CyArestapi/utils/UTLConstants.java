package kamban.com.bbva.CyArestapi.utils;

public enum UTLConstants {
    CODE_DOCUMENT_DISCIPLINA("BD-MX-AA-CYAOEVDS-001"),
    CODE_DOCUMENT_USER("BD-MX-AA-CYAOUSR-001");

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
