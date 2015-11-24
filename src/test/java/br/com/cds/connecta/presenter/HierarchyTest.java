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
 * @author pires
 */
public class HierarchyTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("hierarchy");
    static final String RESOURCE_ID = RESOURCE.concat("/{id}");

    @Test
    public void saveHierarchy() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("hierarchy/new-hierarchy"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("NomeDaHierachy")));

    }

}
