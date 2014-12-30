package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2014-12-30T15:47:56.247-0200")
@StaticMetamodel(CsvData.class)
public class TbCsvData_ {
	public static volatile SingularAttribute<CsvData, Long> pkCsvData;
	public static volatile SingularAttribute<CsvData, BigDecimal> idRow;
	public static volatile SingularAttribute<CsvData, String> txtDataRow;
	public static volatile SingularAttribute<CsvData, Analysi> tbAnalysi;
}
