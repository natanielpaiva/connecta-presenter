package br.com.cds.connecta.presenter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Nataniel Paiva
 */
public class GroupTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("group");
    static final String RESOURCE_ID = RESOURCE.concat("/{id}");
    
    @Test
    public void listGroup() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$[*]", hasSize(greaterThan(0))));
    }

    @Test
    public void saveGroup() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("group/new-group"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("New Group")))
                .andExpect(jsonPath("$.description", equalTo("Group Description")))
               ;// .andExpect(jsonPath("$.txtQuery", equalTo("SELECT...")));
    }
    
    @Test
    public void getGroupById() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("New Group")))
                .andExpect(jsonPath("$.description", equalTo("Group Description")))
                ;//.andExpect(jsonPath("$.txtQuery", equalTo("SELECT...")));
    }
    
    @Test
    public void updateGroup() throws Exception {
        mockMvc().perform(put(RESOURCE_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("group/edit-group"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("Edit Group")))
                .andExpect(jsonPath("$.description", equalTo("Group Description Edit")))
                ;//.andExpect(jsonPath("$.txtQuery", equalTo("SELECT...")));
    }
    
    @Test
    public void deleteGroup() throws Exception {
        mockMvc().perform(delete(RESOURCE_ID, 999)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }
    
    @Test
    public void bulkDeleteRecords() throws Exception {
        mockMvc().perform(delete(RESOURCE)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content("[98,99,100]")
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isNoContent());
        
        doesntExist(98);
        doesntExist(99);
        doesntExist(100);
    }
    
    private void doesntExist(int id) throws Exception {
        mockMvc().perform(
            get(RESOURCE_ID, id)
            .header("Domain", "cds")
        ).andDo(print())
            .andExpect(status().isNotFound());
    }

}
