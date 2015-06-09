package br.com.cds.connecta.presenter;

import static org.hamcrest.Matchers.equalTo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author Nataniel Paiva
 */
public class AnalysisViewerTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("analysis-viewer");
    static final String RESOURCE_ID = RESOURCE.concat("/{id}");

    @Test
    @Ignore
    public void saveAnalysisViewer() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("analysis-viewer/new-analysis-viewer"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                  .andExpect(jsonPath("$.label", equalTo("")))
                  .andExpect(jsonPath("$.numMaxRow", equalTo(2)))
                  .andExpect(jsonPath("$.viewer.id", equalTo(1)))
                  .andExpect(jsonPath("$.viewer.name", equalTo("Teste viewer")))
                  .andExpect(jsonPath("$.viewer.description", equalTo("Testando viewer")))
                  .andExpect(jsonPath("$.analysisVwColumn[0].id", equalTo(1)))
                  .andExpect(jsonPath("$.analysisVwColumn[0].typeColumn", equalTo("NUMBER")))
                  .andExpect(jsonPath("$.analysisVwColumn[0].type", equalTo("METRIC")))
                  .andExpect(jsonPath("$.analysisVwColumn[0].txtMaskFormat", equalTo("REAL")))
                  .andExpect(jsonPath("$.analysisVwColumn[0].analysisColumn.formula", equalTo("TBTAL.FULANO")))
                  .andExpect(jsonPath("$.analysisVwColumn[0].analysisColumn.label", equalTo("FFA")))
                  .andExpect(jsonPath("$.analysisVwColumn[0].analysisColumn.name", equalTo("FULANO")))
                ;
    }
   

}
