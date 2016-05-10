package br.com.cds.connecta.presenter;

import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.*;
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
public class ExecuteDatabaseAnalysisTest extends BaseTest {

    private static final String RESOURCE = REST_PATH.concat("analysis");
    private static final String RESOURCE_SQL = RESOURCE.concat("/result");
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

    /**
     *
     * @throws Exception
     */
    @Test
    public void runSQLMySQLDatabaseAnalysis() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-mysql-database-sql", new String[]{
            "cod",
            "cod2",
            "cod_tmp",
            "transporte",
            "taxi",
            "projeto",
            "reprovacao",
            "retificado",
            "observacoes",
            "locacao",
            "tipo",
            "assunto",
            "hotel",
            "atividade",
            "data"
        }, null);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void runSQLMySQLDatabaseAnalysisWithColumns() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-mysql-database-sql-columns", new String[]{
            "Código",
            "Data do Memorando",
            "Projeto alocado"
        }, new String[]{
            "cod",
            "cod2",
            "cod_tmp",
            "transporte",
            "taxi",
            "projeto",
            "reprovacao",
            "retificado",
            "observacoes",
            "locacao",
            "tipo",
            "assunto",
            "hotel",
            "atividade",
            "data"
        });
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void runTableMySQLDatabaseAnalysis() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-mysql-database-table", new String[]{
            "id_projeto",
            "nome",
            "descricao",
            "id_usuario_gerente",
            "id_usuario_diretor",
            "assunto",
            "ativo",
            "assinatura",
            "nome_abrv",
            "n_contrato",
            "aprovacao",
            "cod_nd",
            "id_empresa_cabecalho",
            "cod_projeto_primavera",
            "nome_projeto_primavera"
        }, null);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void runTableMySQLDatabaseAnalysisWithColumns() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-mysql-database-table-columns", new String[]{
            "ID do Projeto",
            "Descrição",
            "Ativo?"
        }, new String[]{
            "id_projeto",
            "nome",
            "descricao",
            "id_usuario_gerente",
            "id_usuario_diretor",
            "assunto",
            "ativo",
            "assinatura",
            "nome_abrv",
            "n_contrato",
            "aprovacao",
            "cod_nd",
            "id_empresa_cabecalho",
            "cod_projeto_primavera",
            "nome_projeto_primavera"
        });
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void runSQLOracleDatabaseAnalysis() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-oracle-database-sql", new String[]{
            "QT",
            "TP"
        }, null);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void runSQLOracleDatabaseAnalysisWithColumns() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-oracle-database-sql-columns", new String[]{
            "Quantidade",
            "Tipo de visualizador"
        }, new String[]{
            "QT",
            "TP"
        });
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void runTableOracleDatabaseAnalysis() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-oracle-database-table", new String[]{
            "PK_VIEWER",
            "NM_VIEWER",
            "DS_VIEWER",
            "TP_VIEWER",
            "BN_CONFIGURATION",
            "BN_PREVIEW"
        }, null);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void runTableOracleDatabaseAnalysisWithColumns() throws Exception {
        testReturnedColumnsFromAnalysis("run-analysis/analysis-oracle-database-table-columns", new String[]{
            "ID do Visualizador",
            "Nome do visualizador",
            "Tipo de visualizador"
        }, new String[]{
            "PK_VIEWER",
            "NM_VIEWER",
            "DS_VIEWER",
            "TP_VIEWER",
            "BN_CONFIGURATION",
            "BN_PREVIEW"
        });
    }

    /**
     * Posta o JSON da Análise no Endpoint de execução e testa as colunas
     * retornadas
     *
     * @param analysisJsonPath O identificador do JSON a ser postado no servidor
     * @param existingColumns Colunas que são esperadas no resultado da Análise
     * @param nonExistingColumns Colunas que são esperadas no resultado da
     * Análise
     * @throws Exception
     */
    private void testReturnedColumnsFromAnalysis(String analysisJsonPath, String[] existingColumns, String[] nonExistingColumns) throws Exception {
        ResultActions resultActions = mockMvc().perform(post(RESOURCE_SQL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson(analysisJsonPath))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))));

        if (existingColumns != null) {
            for (String column : existingColumns) {
                resultActions.andExpect(jsonPath("$[*]", todosOsItens(hasKey(column))));
            }
        }
        if (nonExistingColumns != null) {
            for (String column : nonExistingColumns) {
                resultActions.andExpect(jsonPath("$[*]", todosOsItens(not(hasKey(column)))));
            }
        }
    }

}
