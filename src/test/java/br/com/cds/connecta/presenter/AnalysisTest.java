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
    public void sucessoBuscarAnalises() throws Exception {
        mockMvc().perform(get(RESOURCE)
            .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$[*]", hasSize(greaterThan(1))))
            .andExpect(jsonPath("$[*].description", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$[*].name", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$[*].type", todosOsItens(notNullValue())))
            .andExpect(jsonPath("$[*].datasource", todosOsItens(notNullValue())));
    }
    
    @Test
    public void sucessoBuscarAnalisePorID() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 1)
            .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.host", allOf(notNullValue(), containsString("http://"), containsString("presenter"))))
            .andExpect(jsonPath("$.name", allOf(notNullValue(), equalTo("presenter"))))
            .andExpect(jsonPath("$.title", allOf(notNullValue(), equalToIgnoringCase("presenter"))));
    }
    
    @Test
    public void sucessoSalvarAnalise() throws Exception {
        mockMvc().perform(post(RESOURCE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(getJson("analysis/new-analysis"))
        ).andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.host", equalTo("http://speaknow.connecta.com")))
            .andExpect(jsonPath("$.name", equalTo("speaknow")))
            .andExpect(jsonPath("$.title", equalTo("SpeakNow")))
            .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));
    }
    
    @Test
    public void sucessoAlterarAnalise() throws Exception {
        mockMvc().perform(put(RESOURCE_ID, 1)
            .contentType(MediaType.APPLICATION_JSON)
            .content(getJson("analysis/edit-analysis"))
        ).andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), equalTo(1))))
            .andExpect(jsonPath("$.host", equalTo("http://connectad.cds.com.br/presenter")))
            .andExpect(jsonPath("$.name", equalTo("presenter")))
            .andExpect(jsonPath("$.title", equalTo("Presentacion de Pelota")));
    }
    
    @Test
    public void sucessoExcluirAnalise() throws Exception {
        mockMvc().perform(delete(RESOURCE_ID, 99)
            .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
            .andExpect(status().isNoContent())
            .andExpect(content().string(""));
    }
    
}
