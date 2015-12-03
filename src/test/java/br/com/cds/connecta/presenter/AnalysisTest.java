package br.com.cds.connecta.presenter;

import org.junit.Test;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Iterator;
import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import org.json.JSONException;
import org.json.JSONObject;
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
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("New Analysis")))
                .andExpect(jsonPath("$.description", equalTo("Analysis Description")))
                .andExpect(jsonPath("$.type", equalTo("DATABASE")))
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
                .andExpect(jsonPath("$.analysisColumns[2].formula", equalTo("ASD.NOME_USUARIO")));
    }
    
    
    
//    @Test
//    public void sucessoBuscarAnalises() throws Exception {
//        mockMvc().perform(get(RESOURCE)
//            .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$", notNullValue()))
//            .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))))
//            .andExpect(jsonPath("$[*].description", todosOsItens(notNullValue())))
//            .andExpect(jsonPath("$[*].name", todosOsItens(notNullValue())))
//            .andExpect(jsonPath("$[*].type", todosOsItens(notNullValue())))
//            .andExpect(jsonPath("$[*].datasource", todosOsItens(nullValue())));
//    }
//    
//    @Test
//    public void sucessoBuscarAnalisePorID() throws Exception {
//        mockMvc().perform(get(RESOURCE_ID, 1)
//            .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$", notNullValue()))
//            .andExpect(jsonPath("$.name", equalTo("Teste")))
//            .andExpect(jsonPath("$.description", equalTo("Teste descricao")))
//            .andExpect(jsonPath("$.type", equalTo("TESTE")))
//            .andExpect(jsonPath("$.datasource", nullValue()));
//    }
//    
    

//    @Test
//    public void sucessoAlterarAnalise() throws Exception {
//        mockMvc().perform(put(RESOURCE_ID, 2)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(getJson("analysis/edit-analysis"))
//        ).andDo(print())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isOk())
//            .andExpect(jsonPath("$", notNullValue()))
//            .andExpect(jsonPath("$.id", equalTo(2)))
//            .andExpect(jsonPath("$.name", equalTo("Edited Analysis")))
//            .andExpect(jsonPath("$.description", equalTo("Analysis Description")))
//            .andExpect(jsonPath("$.type", equalTo("TESTING")))
//            .andExpect(jsonPath("$.datasource", nullValue()));
//    }
//    @Test
//    public void sucessoExcluirAnalise() throws Exception {
//        mockMvc().perform(delete(RESOURCE_ID, 99)
//            .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(print())
//            .andExpect(status().isNoContent())
//            .andExpect(content().string(""));
//    }
//    
    @Test
    public void bulkDeleteRecords() throws Exception {
        mockMvc().perform(delete(RESOURCE)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content("[98,99,100]")
        ).andDo(print())
                .andExpect(status().isNoContent());
        
        doesntExist(98);
        doesntExist(99);
        doesntExist(100);
    }
    
    private void doesntExist(int id) throws Exception {
        mockMvc().perform(
            get(RESOURCE_ID, id)
        ).andDo(print())
            .andExpect(status().isNotFound());
    }
}
