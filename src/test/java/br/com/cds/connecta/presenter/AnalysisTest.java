/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
//    public void sucessoSalvarAnalise() throws Exception {
//        mockMvc().perform(post(RESOURCE)
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(getJson("analysis/new-analysis"))
//        ).andDo(print())
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(status().isCreated())
//            .andExpect(jsonPath("$", notNullValue()))
//            .andExpect(jsonPath("$.name", equalTo("New Analysis")))
//            .andExpect(jsonPath("$.description", equalTo("Analysis Description")))
//            .andExpect(jsonPath("$.type", equalTo("TEST")))
//            .andExpect(jsonPath("$.datasource", notNullValue()))
//            .andExpect(jsonPath("$.datasource.id", equalTo(1)));
//    }
    
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
}
