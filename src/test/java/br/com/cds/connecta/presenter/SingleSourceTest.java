/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter;

import org.junit.Test;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author paiva
 */
public class SingleSourceTest extends BaseTest {
    
    static final String RESOURCE = REST_PATH.concat("media");
    static final String RESOURCE_URL = RESOURCE.concat("/url");
    static final String RESOURCE_FILE = RESOURCE.concat("/file");
    static final String RESOURCE_ID = RESOURCE.concat("/{id}");
    
    @Test
    public void saveFile() throws Exception {
        mockMvc().perform(post(RESOURCE_FILE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(getJson("singlesource/new-file-single-source"))
        ).andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("New Midia")))
            .andExpect(jsonPath("$.description", equalTo("Midia Description")))
            .andExpect(jsonPath("$.id", equalTo(1)));
    }
    
    @Test
    public void saveUrl() throws Exception {
        mockMvc().perform(post(RESOURCE_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(getJson("singlesource/new-url-single-source"))
        ).andDo(print())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.name", equalTo("New Midia")))
            .andExpect(jsonPath("$.description", equalTo("Midia Description")))
            .andExpect(jsonPath("$.id", equalTo(2)));
    }
    
}
