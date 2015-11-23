package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.presenter.domain.CSVAnalysisQuoteEnum;
import br.com.cds.connecta.presenter.domain.CSVAnalysisSeparatorEnum;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author diego
 */

@Entity
@Table(name = "TB_CSV_ANALYSIS")
public class CsvAnalysis extends Analysis{
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TP_SEPARATOR")
    private CSVAnalysisSeparatorEnum separator;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "TP_QUOTE")
    private CSVAnalysisQuoteEnum quote;
    
    @Lob
    @Column(name = "BN_CSV")
    private String binaryFile;

    
    public CSVAnalysisSeparatorEnum getSeparator() {
        return separator;
    }

    public void setSeparator(CSVAnalysisSeparatorEnum separator) {
        this.separator = separator;
    }

    public CSVAnalysisQuoteEnum getQuote() {
        return quote;
    }

    public void setQuote(CSVAnalysisQuoteEnum quote) {
        this.quote = quote;
    }

     public String getBinaryFile() {
        return binaryFile;
    }

    public void setBinaryFile(String binaryFile) {
        this.binaryFile = binaryFile;
    }
    
   
}
