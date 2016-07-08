package br.com.cds.connecta.presenter;

import org.junit.Test;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.*;
import br.com.cds.connecta.presenter.domain.AnalysisTypeEnum;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author pires
 */
public class AnalysisTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("analysis");
    static final String RESOURCE_ID = RESOURCE.concat("/{id}");

    @Test
    public void saveAnalysis() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("analysis/new-analysis-database"))
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("New Analysis")))
                .andExpect(jsonPath("$.description", equalTo("Analysis Description")))
                .andExpect(jsonPath("$.type", enumKeyFor(AnalysisTypeEnum.DATABASE)))
                .andExpect(jsonPath("$.datasource", notNullValue()))
                .andExpect(jsonPath("$.datasource.id", equalTo(1)))
                
                .andExpect(jsonPath("$.analysisColumns[0].name", equalTo("ID_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[0].label", equalTo("ID_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[0].formula", equalTo("ASD.ID_USUARIO")))
                
                .andExpect(jsonPath("$.analysisColumns[1].name", equalTo("ID_EMPRESAS")))
                .andExpect(jsonPath("$.analysisColumns[1].label", equalTo("ID_EMPRESAS")))
                .andExpect(jsonPath("$.analysisColumns[1].formula", equalTo("ASD.ID_EMPRESAS")))
                
                .andExpect(jsonPath("$.analysisColumns[2].name", equalTo("NOME_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[2].label", equalTo("NOME_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[2].formula", equalTo("ASD.NOME_USUARIO")))
                
                .andExpect(jsonPath("$.analysisRelations[0].leftAnalysisColumn.id", equalTo(1)))
                .andExpect(jsonPath("$.analysisRelations[0].rightAnalysis.id", equalTo(1)))
                .andExpect(jsonPath("$.analysisRelations[0].rightAnalysisColumn.id", equalTo(2)));
        
        mockMvc().perform(get(RESOURCE_ID, 101)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
            .andExpect(jsonPath("$.id", equalTo(101)))
            
            .andExpect(jsonPath("$.type", enumKeyFor(AnalysisTypeEnum.DATABASE)))
            .andExpect(jsonPath("$.analysisRelations[*].leftAnalysisColumn", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$.analysisRelations[*].rightAnalysis", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$.analysisRelations[*].rightAnalysisColumn", todosOsItens(notNullValue())))
                
            .andExpect(jsonPath("$.analysisRelations[0].leftAnalysisColumn.id", equalTo(1)))
            .andExpect(jsonPath("$.analysisRelations[0].rightAnalysis.id", equalTo(1)))
            .andExpect(jsonPath("$.analysisRelations[0].rightAnalysisColumn.id", equalTo(2)))
        ;
    }
    
    @Test
    public void saveAnalysisNewColumns() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("analysis/new-analysis-database-new-columns"))
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("New Analysis")))
                .andExpect(jsonPath("$.description", equalTo("Analysis Description")))
                .andExpect(jsonPath("$.type", enumKeyFor(AnalysisTypeEnum.DATABASE)))
                .andExpect(jsonPath("$.datasource", notNullValue()))
                .andExpect(jsonPath("$.datasource.id", equalTo(1)))
                
                .andExpect(jsonPath("$.analysisColumns[0].name", equalTo("ID_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[0].label", equalTo("ID_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[0].formula", equalTo("ASD.ID_USUARIO")))
                
                .andExpect(jsonPath("$.analysisColumns[1].name", equalTo("ID_EMPRESAS")))
                .andExpect(jsonPath("$.analysisColumns[1].label", equalTo("ID_EMPRESAS")))
                .andExpect(jsonPath("$.analysisColumns[1].formula", equalTo("ASD.ID_EMPRESAS")))
                
                .andExpect(jsonPath("$.analysisColumns[2].name", equalTo("NOME_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[2].label", equalTo("NOME_USUARIO")))
                .andExpect(jsonPath("$.analysisColumns[2].formula", equalTo("ASD.NOME_USUARIO")))
                
                .andExpect(jsonPath("$.analysisRelations[0].leftAnalysisColumn.id", greaterThan(0)))
                .andExpect(jsonPath("$.analysisRelations[0].leftAnalysisColumn.name", equalTo("ID_USUARIO")))
                .andExpect(jsonPath("$.analysisRelations[0].rightAnalysis.id", equalTo(1)))
                .andExpect(jsonPath("$.analysisRelations[0].rightAnalysisColumn.id", equalTo(2)));
        
        mockMvc().perform(get(RESOURCE_ID, 102)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
            .andExpect(jsonPath("$.id", equalTo(102)))
            
            .andExpect(jsonPath("$.type", enumKeyFor(AnalysisTypeEnum.DATABASE)))
            .andExpect(jsonPath("$.analysisRelations[*].leftAnalysisColumn", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$.analysisRelations[*].rightAnalysis", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$.analysisRelations[*].rightAnalysisColumn", todosOsItens(notNullValue())))
                
            .andExpect(jsonPath("$.analysisRelations[0].leftAnalysisColumn.id", isA(Number.class)))
            .andExpect(jsonPath("$.analysisRelations[0].rightAnalysis.id", equalTo(1)))
            .andExpect(jsonPath("$.analysisRelations[0].rightAnalysisColumn.id", equalTo(2)))
        ;
    }
    
    /**
     * 
     * @throws Exception 
     */
    @Test
    public void listAnalysis() throws Exception {
        mockMvc().perform(get(RESOURCE)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))))
            .andExpect(jsonPath("$[*].description", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$[*].name", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$[*].type", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$[*].datasource", todosOsItens(nullValue())));
    }
    
    /**
     * @throws Exception 
     */
    @Test
    public void getAnalysisByID() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 1)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("Teste")))
            .andExpect(jsonPath("$.description", equalTo("Teste descricao")))
            .andExpect(jsonPath("$.type", enumKeyFor(AnalysisTypeEnum.DATABASE)))
            .andExpect(jsonPath("$.datasource", notNullValue()));
    }

    @Test
    public void updateAnalysis() throws Exception {
        mockMvc().perform(put(RESOURCE_ID, 2)
            .contentType(MediaType.APPLICATION_JSON)
            .content(getJson("analysis/edit-analysis"))
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.id", equalTo(2)))
            .andExpect(jsonPath("$.name", equalTo("Edited Analysis")))
            .andExpect(jsonPath("$.description", equalTo("Analysis Description")))
            .andExpect(jsonPath("$.type", enumKeyFor(AnalysisTypeEnum.CSV)))
            .andExpect(jsonPath("$.datasource", nullValue()));
    }
    
    /**
     * @throws Exception 
     */
    @Test
    public void deleteAnalysis() throws Exception {
        mockMvc().perform(delete(RESOURCE_ID, 100)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(status().isNoContent())
            .andExpect(content().string(""));
        
        doesntExist(100);
    }
    
    @Test
    public void bulkDeleteRecords() throws Exception {
        mockMvc().perform(delete(RESOURCE)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content("[98,99]")
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isNoContent());
        
        doesntExist(98);
        doesntExist(99);
    }
    
    private void doesntExist(int id) throws Exception {
        mockMvc().perform(
            get(RESOURCE_ID, id)
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(status().isNotFound());
    }
}
