package br.com.cds.connecta.presenter;

import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.enumKeyFor;
import br.com.cds.connecta.presenter.domain.AnalysisViewerColumnDataType;
import br.com.cds.connecta.presenter.domain.AnalysisViewerColumnType;
import br.com.cds.connecta.presenter.domain.SingleSourceGroupViewerVisualizationEnum;
import br.com.cds.connecta.presenter.domain.ViewerTypeEnum;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.hamcrest.Matchers.*;
import org.junit.Ignore;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author pires
 */
public class ViewerTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("viewer");
    static final String RESOURCE_ID = RESOURCE.concat("/{id}");
    static final String RESOURCE_TEMPLATE = RESOURCE.concat("/chart-template");
    static final String RESOURCE_TEMPLATE_TYPE = RESOURCE_TEMPLATE.concat("/{type}");
    static final String RESOURCE_TEMPLATE_CONTENT = RESOURCE_TEMPLATE_TYPE.concat("/{template}");

    @Test
    public void saveAnalysisViewer() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("viewer/new-analysis-viewer"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)))
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
                .andExpect(jsonPath("$.configuration.graphs[1].lineColor", equalTo("['#FF6600', '#FCD202']")))
                .andExpect(jsonPath("$.configuration.legend.useGraphSettings", equalTo(true)))
                .andExpect(jsonPath("$.configuration.titles[0].id", equalTo("Title-1")))
                .andExpect(jsonPath("$.configuration.titles[0].size", equalTo(15d)))
                .andExpect(jsonPath("$.configuration.titles[0].text", equalTo("Chart Title")))
                
                .andExpect(jsonPath("$.label", equalTo("Test")))
                .andExpect(jsonPath("$.maxRows", equalTo(2)))
                .andExpect(jsonPath("$.analysisViewerColumns[0].columnDataType", enumKeyFor(AnalysisViewerColumnDataType.NUMBER)))
                .andExpect(jsonPath("$.analysisViewerColumns[0].columnType", enumKeyFor(AnalysisViewerColumnType.METRIC)))
                .andExpect(jsonPath("$.analysisViewerColumns[0].maskFormat", equalTo("########-##")))
                .andExpect(jsonPath("$.analysisViewerColumns[0].analysisColumn.id", equalTo(1)))
                
                .andExpect(jsonPath("$.analysisViewerColumns[1].columnDataType", enumKeyFor(AnalysisViewerColumnDataType.TEXT)))
                .andExpect(jsonPath("$.analysisViewerColumns[1].columnType", enumKeyFor(AnalysisViewerColumnType.DESCRIPTION)))
                .andExpect(jsonPath("$.analysisViewerColumns[1].maskFormat", equalTo(".*")))
                .andExpect(jsonPath("$.analysisViewerColumns[1].analysisColumn.id", equalTo(2)))
                
                .andExpect(jsonPath("$.analysisViewerColumns[2].analysisColumn.id", equalTo(1)))
                .andExpect(jsonPath("$.analysisViewerColumns[2].columnType", enumKeyFor(AnalysisViewerColumnType.VALUEFIELD)))
                ;
    }
    
    @Test
    public void saveSingleSourceViewer() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("viewer/new-singlesource-viewer"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name", equalTo("TestViewer")))
                .andExpect(jsonPath("$.description", equalTo("TestDescription")))
                .andExpect(jsonPath("$.configuration.type", equalTo("singlesource")))
                
                .andExpect(jsonPath("$.singleSource.id", is(1)))
                ;
    }
    
    @Test
    public void saveMediaGroupViewer() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("viewer/new-singlesource-group-viewer"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name", equalTo("TestViewer")))
                .andExpect(jsonPath("$.description", equalTo("TestDescription")))
                .andExpect(jsonPath("$.configuration.type", equalTo("singlesourcegroup")))
                
                .andExpect(jsonPath("$.group.id", is(1)))
                .andExpect(jsonPath("$.visualization", enumKeyFor(SingleSourceGroupViewerVisualizationEnum.GALLERY)))
                ;
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
                .andExpect(jsonPath("$.configuration.titles[0].text", equalTo("Chart Title")))
                
                .andExpect(jsonPath("$.analysisViewerColumns[0].analysisColumn.id", equalTo(2)))
                .andExpect(jsonPath("$.analysisViewerColumns[0].columnType", enumKeyFor(AnalysisViewerColumnType.VALUEFIELD)))
                ;
    }
    
    @Test
    public void getAnalysisViewer() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 50)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name", equalTo("Analysis viewer")))
                .andExpect(jsonPath("$.description", nullValue()))
                .andExpect(jsonPath("$.type", enumKeyFor(ViewerTypeEnum.ANALYSIS)))
                .andExpect(jsonPath("$.configuration", nullValue()))
                
                .andExpect(jsonPath("$.updateInterval", is(1000)))
                .andExpect(jsonPath("$.maxRows", is(5)))
                .andExpect(jsonPath("$.label", is("Analysis label")))
                .andExpect(jsonPath("$.analysisViewerColumns", hasSize(greaterThan(0))))
                ;
    }
    
    @Test
    public void getSingleSourceViewer() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 51)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name", equalTo("Single source viewer")))
                .andExpect(jsonPath("$.type", enumKeyFor(ViewerTypeEnum.SINGLESOURCE)))
                
                .andExpect(jsonPath("$.singleSource", notNullValue()))
                .andExpect(jsonPath("$.singleSource.id", is(100)))
                ;
    }
    
    @Test
    @Ignore
    public void getSingleSourceGroupViewer() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 52)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name", equalTo("Single source group viewer")))
                .andExpect(jsonPath("$.type", enumKeyFor(ViewerTypeEnum.SINGLESOURCE_GROUP)))
                .andExpect(jsonPath("$.visualization", enumKeyFor(SingleSourceGroupViewerVisualizationEnum.GALLERY)))
                
                .andExpect(jsonPath("$.group", notNullValue()))
                .andExpect(jsonPath("$.group.id", is(52)))
                ;
    }
    
    @Test
    @Ignore
    public void getCombinedViewer() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 53)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", greaterThan(0)))
                .andExpect(jsonPath("$.name", equalTo("TestViewer")))
                .andExpect(jsonPath("$.description", equalTo("TestDescription")))
                .andExpect(jsonPath("$.type", enumKeyFor(ViewerTypeEnum.COMBINED)))
                ;
    }

    @Test
    public void deleteViewer() throws Exception {
        mockMvc().perform(delete(RESOURCE_ID, 50)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }

    @Test
    public void listChartTypes() throws Exception {
        mockMvc().perform(get(RESOURCE_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    public void listChartByType() throws Exception {
        mockMvc().perform(get(RESOURCE_TEMPLATE)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(7)));
    }

    @Test
    public void getChartTemplate() throws Exception {
        mockMvc().perform(get(RESOURCE_TEMPLATE_CONTENT, "area", "area-area")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.type", equalTo("serial")));
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
