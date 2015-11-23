package br.com.cds.connecta.presenter.domain;

/**
 *
 * @author diego
 */
public enum CSVAnalysisSeparatorEnum {
    COMMA(','),
    SEMICOLON(';');
    
    private final char string;

    private CSVAnalysisSeparatorEnum(char separator) {
        this.string = separator;
    }

    public char getChar() {
        return string;
    }
}
