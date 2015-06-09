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
public class ViewerTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("viewer");
    static final String RESOURCE_TEST = RESOURCE.concat("/teste");
    static final String RESOURCE_ID = RESOURCE.concat("/{id}");

    @Test
    public void saveViewer() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("viewer/new-viewer"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("TestViewer")))
                .andExpect(jsonPath("$.description", equalTo("TestDescription")))
                .andExpect(jsonPath("$.configuration.type", equalTo("serial")))
                .andExpect(jsonPath("$.configuration.path", equalTo("https://www.amcharts.com/lib/3/")))
                .andExpect(jsonPath("$.configuration.categoryField", equalTo("category")))
                .andExpect(jsonPath("$.configuration.startDuration", equalTo(1d)))
                .andExpect(jsonPath("$.configuration.categoryAxis.gridPosition", equalTo("start")))
                .andExpect(jsonPath("$.configuration.graphs[0].balloonText", equalTo("[[title]] of [[category]]:[[value]]")))
                .andExpect(jsonPath("$.configuration.graphs[0].fillAlphas", equalTo(1d)))
                .andExpect(jsonPath("$.configuration.graphs[0].id", equalTo("AmGraph-1")))
                .andExpect(jsonPath("$.configuration.graphs[0].title", equalTo("graph 1")))
                .andExpect(jsonPath("$.configuration.graphs[0].type", equalTo("column")))
                .andExpect(jsonPath("$.configuration.graphs[0].valueField", equalTo("column-1")))
                .andExpect(jsonPath("$.configuration.graphs[1].balloonText", equalTo("[[title]] of [[category]]:[[value]]")))
                .andExpect(jsonPath("$.configuration.graphs[1].fillAlphas", equalTo(1d)))
                .andExpect(jsonPath("$.configuration.graphs[1].id", equalTo("AmGraph-2")))
                .andExpect(jsonPath("$.configuration.graphs[1].title", equalTo("graph 2")))
                .andExpect(jsonPath("$.configuration.graphs[1].type", equalTo("column")))
                .andExpect(jsonPath("$.configuration.graphs[1].valueField", equalTo("column-2")))
                .andExpect(jsonPath("$.configuration.legend.useGraphSettings", equalTo(true)))
                .andExpect(jsonPath("$.configuration.titles[0].id", equalTo("Title-1")))
                .andExpect(jsonPath("$.configuration.titles[0].size", equalTo(15d)))
                .andExpect(jsonPath("$.configuration.titles[0].text", equalTo("Chart Title")));
    }

    @Test
    public void updateViewer() throws Exception {
        mockMvc().perform(put(RESOURCE_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("viewer/edit-viewer"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("TestViewerEdit")))
                .andExpect(jsonPath("$.description", equalTo("TestDescriptionEdit")))
                .andExpect(jsonPath("$.configuration.type", equalTo("serial")))
                .andExpect(jsonPath("$.configuration.path", equalTo("https://www.amcharts.com/lib/3/")))
                .andExpect(jsonPath("$.configuration.categoryField", equalTo("category")))
                .andExpect(jsonPath("$.configuration.startDuration", equalTo(1d)))
                .andExpect(jsonPath("$.configuration.categoryAxis.gridPosition", equalTo("start")))
                .andExpect(jsonPath("$.configuration.graphs[0].balloonText", equalTo("[[title]] of [[category]]:[[value]]")))
                .andExpect(jsonPath("$.configuration.graphs[0].fillAlphas", equalTo(1d)))
                .andExpect(jsonPath("$.configuration.graphs[0].id", equalTo("AmGraph-1")))
                .andExpect(jsonPath("$.configuration.graphs[0].title", equalTo("graph 1")))
                .andExpect(jsonPath("$.configuration.graphs[0].type", equalTo("column")))
                .andExpect(jsonPath("$.configuration.graphs[0].valueField", equalTo("column-1")))
                .andExpect(jsonPath("$.configuration.graphs[1].balloonText", equalTo("[[title]] of [[category]]:[[value]]")))
                .andExpect(jsonPath("$.configuration.graphs[1].fillAlphas", equalTo(1d)))
                .andExpect(jsonPath("$.configuration.graphs[1].id", equalTo("AmGraph-2")))
                .andExpect(jsonPath("$.configuration.graphs[1].title", equalTo("graph 2")))
                .andExpect(jsonPath("$.configuration.graphs[1].type", equalTo("column")))
                .andExpect(jsonPath("$.configuration.graphs[1].valueField", equalTo("column-2")))
                .andExpect(jsonPath("$.configuration.legend.useGraphSettings", equalTo(true)))
                .andExpect(jsonPath("$.configuration.titles[0].id", equalTo("Title-1")))
                .andExpect(jsonPath("$.configuration.titles[0].size", equalTo(15d)))
                .andExpect(jsonPath("$.configuration.titles[0].text", equalTo("Chart Title")));
    }

    @Test
    public void deleteViewer() throws Exception {
        mockMvc().perform(delete(RESOURCE_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void teste() throws Exception {
        mockMvc().perform(get(RESOURCE_TEST)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

}
