package br.com.cds.connecta.presenter;

import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.*;
import java.util.ArrayList;
import java.util.Collection;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author pires
 */
@Ignore
public class ExecuteDatabaseAnalysisFilterTest extends BaseTest {

    private static final String ANALYSIS_RESULT = REST_PATH.concat("analysis/result");
    private static final String FILTER_VALUES = REST_PATH.concat("analysis/filter-value");
    
//    private final String MOCK_H2_ORACLE = "jdbc:h2:mem:connecta;INIT=create schema if not exists PRESENTER2;MODE=Oracle;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";
//    private final String MOCK_H2_MYSQL = "jdbc:h2:mem:connecta;INIT=create schema if not exists PRESENTER2;MODE=MySQL;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE";

    /**
     * Variável recebida pela property do maven para testar a integração ou não
     */
    private static Boolean integration;

    @BeforeClass
    public static void beforeClass() {
        integration = Boolean.valueOf(System.getProperty("connecta.analysis.integration"));
    }

    /**
     * TODO Faz um mock da integração caso seja informado para não integrar
     */
    @Before
    public void mock() {
        if (!integration) {
            logger().info("MOCKING SERVER CALLS");
        }
    }

    @Test
    public void listPossibleValuesForFilterTableMySQL() throws Exception {
        makeFilterValuesRequest("run-analysis/analysis-mysql-database-table", "cod_projeto_primavera")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                nullValue(),
                equalTo("ALEP.032"),
                equalTo("APEX.080"),
                equalTo("APEX.087"),
                equalTo("BanPara.082"),
                equalTo("BEC.078"),
                equalTo("CDS.BI"),
                equalTo("CDS.BI PRIMAVERA"),
                equalTo("CDS.BPM"),
                equalTo("CDS.Comercial"),
                equalTo("CDS.Desenvolvimento"),
                equalTo("CDS.GEO"),
                equalTo("CDS.INFRA"),
                equalTo("CDS.MEMORANDO"),
                equalTo("CDS.MIRA"),
                equalTo("CDS.PMO"),
                equalTo("CDS.REQUISITOS"),
                equalTo("CDS.SOA"),
                equalTo("CEF.040"),
                equalTo("CEF.041"),
                equalTo("CNIPE.044"),
                equalTo("CNJ.047"),
                equalTo("CNJ.061"),
                equalTo("CNJ.070"),
                equalTo("DATASUS.053"),
                equalTo("DATASUS.054"),
                equalTo("DATASUS.055"),
                equalTo("DATASUS.056"),
                equalTo("DATASUS.059"),
                equalTo("DATASUS.063"),
                equalTo("DATASUS.064"),
                equalTo("DATASUS.OS01.043"),
                equalTo("DATASUS.OS02.043"),
                equalTo("DATASUS.OS03.043"),
                equalTo("DATASUS.OS04.043"),
                equalTo("DATASUS.OS05.043"),
                equalTo("DATASUS.OS06.043"),
                equalTo("DATASUS.OS07.043"),
                equalTo("DATASUS.OS08.043"),
                equalTo("DATASUS.OS09.043"),
                equalTo("DATASUS.OS10.043"),
                equalTo("DATASUS.OS11.043"),
                equalTo("DATASUS.OS12.043"),
                equalTo("DATASUS.OS13.043"),
                equalTo("DATASUS.OS14.043"),
                equalTo("DATASUS.OS15.043"),
                equalTo("DPF.085"),
                equalTo("IBAMA.023"),
                equalTo("IBAMA.066"),
                equalTo("IBAMA.069"),
                equalTo("IBAMA.077"),
                equalTo("INEP.049"),
                equalTo("MPT.075"),
                equalTo("POC.MEC.2013"),
                equalTo("PRODESP.087"),
                equalTo("RENNER.031"),
                equalTo("RENNER.050"),
                equalTo("RENNER.052"),
                equalTo("RENNER.067"),
                equalTo("SASCAR.057"),
                equalTo("SASCAR.057.G"),
                equalTo("SEFA.062"),
                equalTo("SEFAZ-SC.084"),
                equalTo("SEFAZ-SP.048"),
                equalTo("SFMSP.072"),
                equalTo("SICREDI.060"),
                equalTo("SICREDI.076"),
                equalTo("SKY.068"),
                equalTo("SKY.083"),
                equalTo("TERRACAP.024"),
                equalTo("TERRACAP.074"),
                equalTo("TJDFT.079"),
                equalTo("TJRO.073"),
                equalTo("TJSP.032"),
                equalTo("TJSP.033"),
                equalTo("TJSP.034"),
                equalTo("Treinamento"),
                equalTo("VALEC.065"),
                equalTo("CDS.Comercial")
            ))));
        
        makeFilterValuesRequest("run-analysis/analysis-mysql-database-table-filter-equal", "id_usuario_gerente")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                equalTo(91),
                equalTo(99)
            ))));
    }

    @Test
    public void listPossibleValuesForFilterSQLMySQL() throws Exception {
        makeFilterValuesRequest("run-analysis/analysis-mysql-database-sql", "tipo")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                equalTo("Reembolso"),
                equalTo("Despesas"),
                equalTo("Despesa"),
                equalTo("Compra de Material")
            ))));
        
        makeFilterValuesRequest("run-analysis/analysis-mysql-database-sql-filter-equal", "projeto")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                equalTo("NTC"),
                equalTo("Prospecção Comercial")
            ))));
    }

    @Test
    public void listPossibleValuesForFilterTableOracle() throws Exception {
        makeFilterValuesRequest("run-analysis/analysis-oracle-database-table", "TP_VIEWER")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                equalTo("SINGLESOURCE"),
                equalTo("ANALYSIS")
            ))));
        
        makeFilterValuesRequest("run-analysis/analysis-oracle-database-table-filter-equal", "NM_DOMAIN")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                equalTo("SalaSituacao"),
                equalTo("Santander"),
                equalTo("Domínio Connecta"),
                equalTo("Projetos")
            ))));
    }

    @Test
    public void listPossibleValuesForFilterSQLOracle() throws Exception {
        makeFilterValuesRequest("run-analysis/analysis-oracle-database-sql", "TP")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                equalTo("DATABASE"),
                equalTo("WEBSERVICE"),
                equalTo("HDFS")
            ))));
        
        makeFilterValuesRequest("run-analysis/analysis-oracle-database-sql-filter-equal", "QT")
            .andExpect(jsonPath("$[*]", todosOsItens(anyOf(
                equalTo(5)
            ))));
    }

    @Test
    public void filterOneColumnDatabaseTableMySQL() throws Exception {
        testOneColumnFilterEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-equal"),
                "cod_projeto_primavera",
                "CDS.Comercial"
        );
        testOneColumnFilterNotEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-not-equal"),
                "cod_projeto_primavera",
                "CDS.Comercial"
        );
        testOneColumnFilterGreaterThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-greater-than"),
                "id_projeto",
                100
        );
        testOneColumnFilterLessThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-less-than"),
                "id_projeto",
                100
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-greater-than-equal"),
                "id_projeto",
                99
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-less-than-equal"),
                "id_projeto",
                99
        );
        testOneColumnFilterBetween(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-between"),
                "id_projeto",
                99, 240
        );
        testOneColumnFilterIn(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-in"),
                "id_projeto",
                new Integer[]{99, 240}
        );
        testOneColumnFilterContains(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-contains"),
                "cod_projeto_primavera",
                "S.Com"
        );
        testOneColumnFilterStartsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-starts-with"),
                "cod_projeto_primavera",
                "CDS"
        );
        testOneColumnFilterEndsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-table-filter-ends-with"),
                "cod_projeto_primavera",
                "ercial"
        );
    }

//    @Test
//    public void filterTwoColumnsDatabaseTableMySQL() {
//        String table = "tb_projeto";
//        String column1 = "cod_projeto_primavera";
//        String column2 = "id_usuario_gerente";
//        String value1 = "CDS.Comercial";
//        String value2 = "99";
//
//        ContextFactory contextFactory = makeMySQLTableContextFactory(table);
//
//        List<Map<String, Object>> all;
//
//        all = getResultTwoColumnsFilter(contextFactory,
//                column1, QueryFilterOperator.EQUAL, new QueryFilterValue(value1),
//                column2, QueryFilterOperator.EQUAL, new QueryFilterValue(value2));
//
//        for (Map<String, Object> map : all) {
//            assertThat(map, hasKey(column1));
//            assertThat((String) map.get(column1), is(value1));
//            assertThat(map, hasKey(column2));
//            assertThat((String) map.get(column2), is(value2));
//        }
//    }
//
    @Test
    public void filterOneColumnDatabaseSQLMySQL() throws Exception {
        testOneColumnFilterEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-equal"),
                "tipo",
                "Reembolso"
        );
        testOneColumnFilterNotEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-not-equal"),
                "tipo",
                "Reembolso"
        );
        testOneColumnFilterGreaterThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-greater-than"),
                "cod",
                6650
        );
        testOneColumnFilterLessThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-less-than"),
                "cod",
                6650
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-greater-than-equal"),
                "cod",
                6657
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-less-than-equal"),
                "cod",
                6657
        );
        testOneColumnFilterBetween(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-between"),
                "cod",
                6633, 6670
        );
        testOneColumnFilterIn(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-in"),
                "tipo",
                new String[]{"Reembolso", "Despesas"}
        );
        testOneColumnFilterContains(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-contains"),
                "tipo",
                "bol"
        );
        testOneColumnFilterStartsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-starts-with"),
                "tipo",
                "Reem"
        );
        testOneColumnFilterEndsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-mysql-database-sql-filter-ends-with"),
                "tipo",
                "bolso"
        );
    }
//
//    @Test
//    public void filterTwoColumnsDatabaseSQLMySQL() {
//        String column1 = "tipo";
//        String value1 = "Reembolso";
//        String column2 = "projeto";
//        String value2 = "NTC";
//
//        ContextFactory contextFactory = makeMySQLSQLContextFactory();
//
//        List<Map<String, Object>> all;
//
//        all = getResultTwoColumnsFilter(contextFactory,
//                column1, QueryFilterOperator.EQUAL, new QueryFilterValue(value1),
//                column2, QueryFilterOperator.EQUAL, new QueryFilterValue(value2));
//
//        for (Map<String, Object> map : all) {
//            assertThat(map, hasKey(column1));
//            assertThat((String) map.get(column1), is(value1));
//            assertThat(map, hasKey(column2));
//            assertThat((String) map.get(column2), is(value2));
//        }
//    }
//
    @Test
    public void filterOneColumnDatabaseTableOracle() throws Exception {
        testOneColumnFilterEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-equal"),
                "TP_VIEWER",
                "ANALYSIS"
        );
        testOneColumnFilterNotEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-not-equal"),
                "TP_VIEWER",
                "ANALYSIS"
        );
        testOneColumnFilterGreaterThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-greater-than"),
                "PK_VIEWER",
                700
        );
        testOneColumnFilterLessThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-less-than"),
                "PK_VIEWER",
                700
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-greater-than-equal"),
                "PK_VIEWER",
                390
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-less-than-equal"),
                "PK_VIEWER",
                390
        );
        testOneColumnFilterBetween(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-between"),
                "PK_VIEWER",
                390, 554
        );
        testOneColumnFilterIn(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-in"),
                "PK_VIEWER",
                new Integer[]{390, 554}
        );
        testOneColumnFilterContains(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-contains"),
                "TP_VIEWER",
                "ALY"
        );
        testOneColumnFilterStartsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-starts-with"),
                "TP_VIEWER",
                "ANA"
        );
        testOneColumnFilterEndsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-table-filter-ends-with"),
                "TP_VIEWER",
                "SIS"
        );
    }
//
//    @Test
//    public void filterTwoColumnsDatabaseTableOracle() {
//        String table = "TB_VIEWER";
//        String column1 = "TP_VIEWER";
//        String column2 = "NM_VIEWER";
//        String value1 = "ANALYSIS";
//        String value2 = "Total de homens";
//
//        ContextFactory contextFactory = makeOracleTableContextFactory(table);
//
//        List<Map<String, Object>> all;
//
//        all = getResultTwoColumnsFilter(contextFactory,
//                column1, QueryFilterOperator.EQUAL, new QueryFilterValue(value1),
//                column2, QueryFilterOperator.EQUAL, new QueryFilterValue(value2));
//
//        for (Map<String, Object> map : all) {
//            assertThat(map, hasKey(column1));
//            assertThat((String) map.get(column1), is(value1));
//            assertThat(map, hasKey(column2));
//            assertThat((String) map.get(column2), is(value2));
//        }
//    }
//
    @Test
    public void filterOneColumnDatabaseSQLOracle() throws Exception {
        testOneColumnFilterEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-equal"),
                "TP",
                "DATABASE"
        );
        testOneColumnFilterNotEqual(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-not-equal"),
                "TP",
                "SOLR"
        );
        testOneColumnFilterGreaterThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-greater-than"),
                "QT",
                3
        );
        testOneColumnFilterLessThan(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-less-than"),
                "QT",
                10
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-greater-than-equal"),
                "QT",
                2
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-less-than-equal"),
                "QT",
                5
        );
        testOneColumnFilterBetween(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-between"),
                "QT",
                2, 10
        );
        testOneColumnFilterIn(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-in"),
                "TP",
                new String[]{"DATABASE", "WEBSERVICE"}
        );
        testOneColumnFilterContains(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-contains"),
                "TP",
                "ABA"
        );
        testOneColumnFilterStartsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-starts-with"),
                "TP",
                "DATA"
        );
        testOneColumnFilterEndsWith(
                makeAnalysisExecutionRequest("run-analysis/analysis-oracle-database-sql-filter-ends-with"),
                "TP",
                "BASE"
        );
    }
//
//    @Test
//    public void filterTwoColumnsDatabaseSQLOracle() {
//        String column1 = "TP";
//        String value1 = "DATABASE";
//        String column2 = "QT";
//        String value2 = "4";
//
//        ContextFactory contextFactory = makeOracleSQLContextFactory();
//
//        List<Map<String, Object>> all;
//
//        all = getResultTwoColumnsFilter(contextFactory,
//                column1, QueryFilterOperator.EQUAL, new QueryFilterValue(value1),
//                column2, QueryFilterOperator.EQUAL, new QueryFilterValue(value2));
//
//        for (Map<String, Object> map : all) {
//            assertThat(map, hasKey(column1));
//            assertThat((String) map.get(column1), is(value1));
//            assertThat(map, hasKey(column2));
//            assertThat((String) map.get(column2), is(value2));
//        }
//    }
//    /**
//     * Posta o JSON da Análise no Endpoint de execução e testa as colunas
//     * retornadas
//     *
//     * @param analysisJsonPath O identificador do JSON a ser postado no servidor
//     * @param existingColumns Colunas que são esperadas no resultado da Análise
//     * @param nonExistingColumns Colunas que são esperadas no resultado da
//     * Análise
//     * @throws Exception
//     */
//    private void testReturnedColumnsFromAnalysis(String analysisJsonPath, String[] existingColumns, String[] nonExistingColumns) throws Exception {
//        ResultActions resultActions = makeBackendRequest(analysisJsonPath);
//
//        if (existingColumns != null) {
//            for (String column : existingColumns) {
//                resultActions.andExpect(jsonPath("$[*]", todosOsItens(hasKey(column))));
//            }
//        }
//        if (nonExistingColumns != null) {
//            for (String column : nonExistingColumns) {
//                resultActions.andExpect(jsonPath("$[*]", todosOsItens(not(hasKey(column)))));
//            }
//        }
//    }
    private ResultActions makeAnalysisExecutionRequest(String analysisJsonPath) throws Exception {
        ResultActions resultActions = mockMvc().perform(post(ANALYSIS_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson(analysisJsonPath))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))));
        return resultActions;
    }
    
    private ResultActions makeFilterValuesRequest(String analysisJsonPath, String column) throws Exception {
        ResultActions resultActions = mockMvc().perform(
                post(FILTER_VALUES).param("column", column)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(getJson(analysisJsonPath))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))));
        return resultActions;
    }

    private void testOneColumnFilterEqual(ResultActions resultActions, String column, String value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(is(value)))
        );
    }

    private void testOneColumnFilterNotEqual(ResultActions resultActions, String column, String value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(not(is(value))))
        );
    }

    private void testOneColumnFilterGreaterThan(ResultActions resultActions, String column, Integer value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(greaterThan(value)))
        );
    }

    private void testOneColumnFilterLessThan(ResultActions resultActions, String column, Integer value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(lessThan(value)))
        );
    }

    private void testOneColumnFilterGreaterThanOrEqualTo(ResultActions resultActions, String column, Integer value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(greaterThanOrEqualTo(value)))
        );
    }

    private void testOneColumnFilterLessThanOrEqualTo(ResultActions resultActions, String column, Integer value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(lessThanOrEqualTo(value)))
        );
    }

    private void testOneColumnFilterBetween(ResultActions resultActions, String column, Integer start, Integer end) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(allOf(
                                        greaterThanOrEqualTo(start),
                                        lessThanOrEqualTo(end)
                                )))
        );
    }

    private void testOneColumnFilterIn(ResultActions resultActions, String column, Object[] values) throws Exception {
        Collection<Matcher<? super Object>> in = new ArrayList<>(values.length);
        for (Object value : values) {
            in.add(is(value));
        }

        resultActions.andExpect(
            jsonPath("$[*]." + column, todosOsItens(anyOf(
                (Iterable<Matcher<? super Object>>) in
            )))
        );
    }
    
    private void testOneColumnFilterContains(ResultActions resultActions, String column, String value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(containsString(value)))
        );
    }
    
    private void testOneColumnFilterStartsWith(ResultActions resultActions, String column, String value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(startsWith(value)))
        );
    }
    
    private void testOneColumnFilterEndsWith(ResultActions resultActions, String column, String value) throws Exception {
        resultActions.andExpect(
                jsonPath("$[*]." + column, todosOsItens(endsWith(value)))
        );
    }

}
