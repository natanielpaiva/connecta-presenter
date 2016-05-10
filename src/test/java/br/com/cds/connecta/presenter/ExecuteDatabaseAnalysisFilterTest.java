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

    private static final String ENDPOINT = REST_PATH.concat("analysis/result");
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

//    @Test
//    public void listPossibleValuesForFilterWithoutPreviousFiltersTableMySQL() {
//        String table = "tb_projeto";
//        String columnName = "id_usuario_gerente";
//
//        ContextFactory contextFactory = makeMySQLTableContextFactory(table);
//
//        QueryBuilder queryContext = new QueryBuilder();
//
//        Request request = new Request(contextFactory, queryContext);
//
//        List<Object> all = client.possibleValuesFor(request, contextFactory.getColumn(columnName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        for (Object possibleValue : all) {
//            logger.info("VALUE: " + possibleValue);
//            assertThat((String) possibleValue, anyOf(
//                    is("91"),
//                    is("99"),
//                    is("224")
//            ));
//        }
//    }
//
//    @Test
//    public void listPossibleValuesForFilterWithPreviousFiltersTableMySQL() {
//        String table = "tb_projeto";
//        String columnName = "cod_projeto_primavera";
//        String value = "CDS.Comercial";
//        String columnPossibleValuesName = "id_usuario_gerente";
//
//        ContextFactory contextFactory = makeMySQLTableContextFactory(table);
//
//        Column column = contextFactory.getColumn(columnName);
//
//        QueryBuilder queryContext = new QueryBuilder()
//                .addFilter(column, QueryFilterOperator.EQUAL, new QueryFilterValue(value));
//
//        Request request = new Request(contextFactory, queryContext);
//
//        List<Object> all = client.possibleValuesFor(request, contextFactory.getColumn(columnPossibleValuesName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        for (Object possibleValue : all) {
//            assertThat((String) possibleValue, anyOf(is("91"), is("99")));
//        }
//    }
//
//    @Test
//    public void listPossibleValuesForFilterWithoutPreviousFiltersSQLMySQL() {
//        String columnName = "tipo";
//
//        ContextFactory contextFactory = makeMySQLSQLContextFactory();
//
//        QueryBuilder queryContext = new QueryBuilder();
//
//        Request request = new Request(contextFactory, queryContext);
//
//        List<Object> all = client.possibleValuesFor(request, contextFactory.getColumn(columnName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        for (Object possibleValue : all) {
//            logger.info("VALUE: " + possibleValue);
//            assertThat((String) possibleValue, anyOf(
//                    is("Reembolso"),
//                    is("Despesas"),
//                    is("Despesa"),
//                    is("Compra de Material")
//            ));
//        }
//    }
//
//    @Test
//    public void listPossibleValuesForFilterWithPreviousFiltersSQLMySQL() {
//        String columnName = "tipo";
//        String value = "Reembolso";
//        String columnPossibleValuesName = "projeto";
//
//        ContextFactory contextFactory = makeMySQLSQLContextFactory();
//
//        Column column = contextFactory.getColumn(columnName);
//
//        QueryBuilder queryContext = new QueryBuilder()
//                .addFilter(column, QueryFilterOperator.EQUAL, new QueryFilterValue(value));
//
//        Request request = new Request(contextFactory, queryContext);
//
//        List<Object> all = client.possibleValuesFor(request, contextFactory.getColumn(columnPossibleValuesName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        assertThat(all, containsInAnyOrder((Object) "NTC", (Object) "Prospecção Comercial"));
//    }
//
//    @Test
//    public void listPossibleValuesForFilterWithoutPreviousFiltersTableOracle() {
//        String table = "TB_VIEWER";
//        String columnName = "TP_VIEWER";
//
//        ContextFactory contextFactory = makeOracleTableContextFactory(table);
//
//        QueryBuilder builder = new QueryBuilder();
//
//        Request request = new Request(contextFactory, builder);
//
//        List<Object> all = client.possibleValuesFor(
//                request,
//                contextFactory.getColumn(columnName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        for (Object possibleValue : all) {
//            logger.info("VALUE: " + possibleValue);
//            assertThat((String) possibleValue, anyOf(
//                    is("SINGLESOURCE"),
//                    is("ANALYSIS")
//            ));
//        }
//    }
//
//    @Test
//    public void listPossibleValuesForFilterWithPreviousFiltersTableOracle() {
//        String table = "TB_DATASOURCE";
//        String columnName = "TP_DATASOURCE";
//        String value = "DATABASE";
//        String columnPossibleValuesName = "TXT_USUARIO";
//
//        ContextFactory contextFactory = makeOracleTableContextFactory(table);
//
//        Column column = contextFactory.getColumn(columnName);
//
//        QueryBuilder queryContext = new QueryBuilder()
//                .addFilter(column, QueryFilterOperator.EQUAL, new QueryFilterValue(value));
//
//        Request request = new Request(contextFactory, queryContext);
//
//        List<Object> all = client.possibleValuesFor(request, contextFactory.getColumn(columnPossibleValuesName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        for (Object possibleValue : all) {
//            assertThat((String) possibleValue, anyOf(
//                    is("PRESENTER2"),
//                    is("PRESENTER_ANALYTICS"),
//                    is("poc_caixa"),
//                    is("root")
//            ));
//        }
//    }
//
//    @Test
//    public void listPossibleValuesForFilterWithoutPreviousFiltersSQLOracle() {
//        String columnName = "TP";
//
//        ContextFactory contextFactory = makeOracleSQLContextFactory();
//
//        QueryBuilder queryContext = new QueryBuilder();
//
//        Request request = new Request(contextFactory, queryContext);
//
//        List<Object> all = client.possibleValuesFor(request, contextFactory.getColumn(columnName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        for (Object possibleValue : all) {
//            logger.info("VALUE: " + possibleValue);
//            assertThat((String) possibleValue, anyOf(
//                    is("DATABASE"),
//                    is("WEBSERVICE"),
//                    is("HDFS")
//            ));
//        }
//    }
//
//    @Test
//    public void listPossibleValuesForFilterWithPreviousFiltersSQLOracle() {
//        String columnName = "TP";
//        String value = "DATABASE";
//        String columnPossibleValuesName = "QT";
//
//        ContextFactory contextFactory = makeOracleSQLContextFactory();
//
//        Column column = contextFactory.getColumn(columnName);
//
//        QueryBuilder queryContext = new QueryBuilder()
//                .addFilter(column, QueryFilterOperator.EQUAL, new QueryFilterValue(value));
//
//        Request request = new Request(contextFactory, queryContext);
//
//        List<Object> all = client.possibleValuesFor(request, contextFactory.getColumn(columnPossibleValuesName));
//
//        assertThat(all, hasSize(greaterThan(0)));
//
//        for (Object possibleValue : all) {
//            logger.info("VALUE: " + possibleValue);
//            assertThat((String) possibleValue, anyOf(
//                    is("4")
//            ));
//        }
//    }
    @Test
    public void filterOneColumnDatabaseTableMySQL() throws Exception {
        testOneColumnFilterEqual(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-equal"),
                "cod_projeto_primavera",
                "CDS.Comercial"
        );
        testOneColumnFilterNotEqual(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-not-equal"),
                "cod_projeto_primavera",
                "CDS.Comercial"
        );
        testOneColumnFilterGreaterThan(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-greater-than"),
                "id_projeto",
                100
        );
        testOneColumnFilterLessThan(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-less-than"),
                "id_projeto",
                100
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-greater-than-equal"),
                "id_projeto",
                99
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-less-than-equal"),
                "id_projeto",
                99
        );
        testOneColumnFilterBetween(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-between"),
                "id_projeto",
                99, 240
        );
        testOneColumnFilterIn(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-in"),
                "id_projeto",
                new Integer[]{99, 240}
        );
        testOneColumnFilterContains(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-contains"),
                "cod_projeto_primavera",
                "S.Com"
        );
        testOneColumnFilterStartsWith(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-starts-with"),
                "cod_projeto_primavera",
                "CDS"
        );
        testOneColumnFilterEndsWith(
                makeBackendRequest("run-analysis/analysis-mysql-database-table-filter-ends-with"),
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
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-equal"),
                "tipo",
                "Reembolso"
        );
        testOneColumnFilterNotEqual(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-not-equal"),
                "tipo",
                "Reembolso"
        );
        testOneColumnFilterGreaterThan(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-greater-than"),
                "cod",
                6650
        );
        testOneColumnFilterLessThan(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-less-than"),
                "cod",
                6650
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-greater-than-equal"),
                "cod",
                6657
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-less-than-equal"),
                "cod",
                6657
        );
        testOneColumnFilterBetween(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-between"),
                "cod",
                6633, 6670
        );
        testOneColumnFilterIn(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-in"),
                "tipo",
                new String[]{"Reembolso", "Despesas"}
        );
        testOneColumnFilterContains(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-contains"),
                "tipo",
                "bol"
        );
        testOneColumnFilterStartsWith(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-starts-with"),
                "tipo",
                "Reem"
        );
        testOneColumnFilterEndsWith(
                makeBackendRequest("run-analysis/analysis-mysql-database-sql-filter-ends-with"),
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
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-equal"),
                "TP_VIEWER",
                "ANALYSIS"
        );
        testOneColumnFilterNotEqual(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-not-equal"),
                "TP_VIEWER",
                "ANALYSIS"
        );
        testOneColumnFilterGreaterThan(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-greater-than"),
                "PK_VIEWER",
                700
        );
        testOneColumnFilterLessThan(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-less-than"),
                "PK_VIEWER",
                700
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-greater-than-equal"),
                "PK_VIEWER",
                390
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-less-than-equal"),
                "PK_VIEWER",
                390
        );
        testOneColumnFilterBetween(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-between"),
                "PK_VIEWER",
                390, 554
        );
        testOneColumnFilterIn(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-in"),
                "PK_VIEWER",
                new Integer[]{390, 554}
        );
        testOneColumnFilterContains(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-contains"),
                "TP_VIEWER",
                "ALY"
        );
        testOneColumnFilterStartsWith(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-starts-with"),
                "TP_VIEWER",
                "ANA"
        );
        testOneColumnFilterEndsWith(
                makeBackendRequest("run-analysis/analysis-oracle-database-table-filter-ends-with"),
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
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-equal"),
                "TP",
                "DATABASE"
        );
        testOneColumnFilterNotEqual(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-not-equal"),
                "TP",
                "SOLR"
        );
        testOneColumnFilterGreaterThan(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-greater-than"),
                "QT",
                3
        );
        testOneColumnFilterLessThan(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-less-than"),
                "QT",
                10
        );
        testOneColumnFilterGreaterThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-greater-than-equal"),
                "QT",
                2
        );
        testOneColumnFilterLessThanOrEqualTo(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-less-than-equal"),
                "QT",
                5
        );
        testOneColumnFilterBetween(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-between"),
                "QT",
                2, 10
        );
        testOneColumnFilterIn(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-in"),
                "TP",
                new String[]{"DATABASE", "WEBSERVICE"}
        );
        testOneColumnFilterContains(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-contains"),
                "TP",
                "ABA"
        );
        testOneColumnFilterStartsWith(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-starts-with"),
                "TP",
                "DATA"
        );
        testOneColumnFilterEndsWith(
                makeBackendRequest("run-analysis/analysis-oracle-database-sql-filter-ends-with"),
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
    private ResultActions makeBackendRequest(String analysisJsonPath) throws Exception {
        ResultActions resultActions = mockMvc().perform(post(ENDPOINT)
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
