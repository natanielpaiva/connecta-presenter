package br.com.cds.connecta.presenter;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

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
    
//    @Test
//    public void bulkDeleteRecords() throws Exception {
//        mockMvc().perform(delete(RESOURCE)
//                .contentType(MEDIATYPE_JSON_UTF8)
//                .content("[98,99,100]")
//        ).andDo(print())
//                .andExpect(status().isNoContent());
//        
//        doesntExist(98);
//        doesntExist(99);
//        doesntExist(100);
//    }
    
    private void doesntExist(int id) throws Exception {
        mockMvc().perform(
            get(RESOURCE_ID, id)
        ).andDo(print())
            .andExpect(status().isNotFound());
    }

}
