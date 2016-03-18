package br.com.cds.connecta.presenter;

import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.peloMenosUmItem;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.stringIgnoringWhitespaceAndCase;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.todosOsItens;
import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import org.junit.Ignore;
import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 * @author pires
 */
@Ignore
public class QueryBuilderTest extends BaseTest {

    final String RESOURCE = REST_PATH.concat("group/query");
    final String RESOURCE_ID = REST_PATH.concat("group/query/{id}");
    final String RESOURCE_RESULT = REST_PATH.concat("group/query/result");
    final String RESOURCE_PREVIEW = REST_PATH.concat("group/query/preview");

    @PersistenceContext
    EntityManager em;

    @Test
    public void mappingPersists() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-save"))
        ).andDo(print())
                .andExpect(status().isOk());

        mockMvc().perform(get(RESOURCE_ID, 1L)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(greaterThan(0))))
                .andExpect(jsonPath("$.statement", notNullValue()))
                .andExpect(jsonPath("$.statement.id", greaterThan(0)))
                .andExpect(jsonPath("$.statement.type", is("GROUP")))
                .andExpect(jsonPath("$.statement.operator", is(QueryOperatorEnum.AND.name())))
                .andExpect(jsonPath("$.statement.statements[*]", hasSize(2)))
                .andExpect(jsonPath("$.statement.statements[*].id", todosOsItens(greaterThan(0))))
                .andExpect(jsonPath("$.statement.statements[*].type", allOf(
                                        peloMenosUmItem(is("CONDITION")),
                                        peloMenosUmItem(is("GROUP"))
                                )))
                .andExpect(jsonPath("$.statement.statements[?(@.type==CONDITION)]", hasSize(1)))
                .andExpect(jsonPath("$.statement.statements[?(@.type==GROUP)]", hasSize(1)));
    }

    @Test
    @Ignore
    public void errorGroupLessThanTwoConditions() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-save-invalid"))
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[*]", hasSize(1)))
                .andExpect(jsonPath("$[*].message", is("BATATA")));
    }

    @Test
    public void sqlWithEqual() throws Exception {
        String expected = getTestResourceContent("sql/querybuilder/select-equal.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }
    
    @Test
    public void sqlWithGroup() throws Exception {
       // String expected = getTestResourceContent("sql/querybuilder/select-group.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-group"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                ;//.andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }

    @Test
    public void sqlWithNotEqual() throws Exception {
        String expected = getTestResourceContent("sql/querybuilder/select-not-equal.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }

    @Test
    public void sqlWithIn() throws Exception {
        String expected = getTestResourceContent("sql/querybuilder/select-in.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-in"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }

    @Test
    public void sqlWithBetween() throws Exception {
        String expected = getTestResourceContent("sql/querybuilder/select-between.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-between"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }

    @Test
    public void sqlWithNotBetween() throws Exception {
        String expected = getTestResourceContent("sql/querybuilder/select-not-between.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-between"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }

    @Test
    public void fetchsResultsWithBetween() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-between"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(99))
                        ))
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(100))
                        ));
    }

    @Test
    public void fetchsResultsWithNotBetween() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-between"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(99))));
    }

    @Test
    public void fetchsResultsWithIn() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-in"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(100))
                        ));
    }

    @Test
    public void fetchsResultsWithNotIn() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-in"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(100))
                        ));
    }

    @Test
    public void sqlWithLike() throws Exception {
        String expected = getTestResourceContent("sql/querybuilder/select-like.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-like"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }

    @Test
    public void sqlWithNotLike() throws Exception {
        String expected = getTestResourceContent("sql/querybuilder/select-not-like.sql");

        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-like"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(stringIgnoringWhitespaceAndCase(expected)));
    }

    @Test
    public void fetchsResultsWithLike() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-like"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(99))));
    }

    @Test
    public void fetchsResultsWithNotLike() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-like"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(99))));
    }

    @Test
    public void fetchsResultsWithEqual() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", anyOf(
                                        peloMenosUmItem(equalTo(99)),
                                        peloMenosUmItem(equalTo(100))
                                )));
    }

    @Test
    public void fetchsResultsWithNotEqual() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id", anyOf(
                                        peloMenosUmItem(equalTo(99)),
                                        peloMenosUmItem(equalTo(100))
                                )));
    }

    @Test
    public void fetchsResultsWithEqualAndEqual() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal-and-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(99))));
    }

    @Test
    public void fetchsResultsWithEqualOrEqual() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal-or-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(99))))
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(100))));
    }
    
    @Test
    public void fetchsResultsWithEqualOrEqualSameAttribute() throws Exception {
        mockMvc().perform(post(RESOURCE_RESULT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal-or-equal-same"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(99))))
                .andExpect(jsonPath("$[*].id", peloMenosUmItem(equalTo(100))));
    }

}
