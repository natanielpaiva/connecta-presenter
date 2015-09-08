/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter;

import br.com.cds.connecta.framework.core.domain.MessageTypeEnum;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.todosOsItens;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Nataniel Paiva
 */
public class AttributeTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("attribute");

    @Test
    public void paginationError() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", notNullValue()))
//                .andExpect(jsonPath("$.code", equalTo(null)))
                .andExpect(jsonPath("$.message", containsString("Invalid pagination")))
                .andExpect(jsonPath("$.type", equalTo(MessageTypeEnum.WARN.name())));
    }
    
    @Test
    public void listPaginatedAttributesWithoutParameter() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .param("page", "1")
                .param("count", "2")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[*].id", todosOsItens(notNullValue())))
                .andExpect(jsonPath("$[*].name", todosOsItens(notNullValue())));
    }

    @Test
    public void listPaginatedAttributes() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .param("page", "1")
                .param("count", "2")
                .param("name", "")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[*].id", todosOsItens(notNullValue())))
                .andExpect(jsonPath("$[*].name", todosOsItens(notNullValue())));
    }

    @Test
    public void listPaginatedFilteredAttributes() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .param("page", "1")
                .param("count", "2")
                .param("name", "desc")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[*].id", todosOsItens(notNullValue())))
                .andExpect(jsonPath("$[*].name", todosOsItens(notNullValue())));
    }
    
    @Test
    public void listPaginatedFilteredAttributesIgnoreCase() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .param("page", "1")
                .param("count", "2")
                .param("name", "DeSc")
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[*].id", todosOsItens(notNullValue())))
                .andExpect(jsonPath("$[*].name", todosOsItens(notNullValue())));
    }
}
