package br.com.cds.connecta.presenter.domain;

/**
 *
 * @author diego
 */
public enum CSVAnalysisQuoteEnum {
    QUOTE('\''),
    DOUBLE_QUOTE('"');
    
    private final char string;

    private CSVAnalysisQuoteEnum(char delimiter) {
        this.string = delimiter;
    }

    public char getChar() {
        return string;
    }
}