package br.com.cds.connecta.presenter;

import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.peloMenosUmItem;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.stringIgnoringWhitespaceAndCase;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.todosOsItens;
import br.com.cds.connecta.presenter.domain.QueryOperatorEnum;
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
public class QueryBuilderTest extends BaseTest {

    final String RESOURCE_SAVE = REST_PATH.concat("group/query");
    final String RESOURCE = REST_PATH.concat("group/query/result");
    final String RESOURCE_PREVIEW = REST_PATH.concat("group/query/preview");

    @Test
    public void mappingPersists() throws Exception {
        mockMvc().perform(post(RESOURCE_SAVE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-save"))
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
        mockMvc().perform(post(RESOURCE_SAVE)
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
                .andExpect(content().string( stringIgnoringWhitespaceAndCase(expected) ));
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
                .andExpect(content().string( stringIgnoringWhitespaceAndCase(expected) ));
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
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-like"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].attribute.id", todosOsItens(is(1))))
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].value", todosOsItens(containsString("Tes"))));
    }

    @Test
    public void fetchsResultsWithNotLike() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-like"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                                "$[*].singleSourceAttributes[?(@.attribute.id==1)].value",
                                todosOsItens(not(containsString("Tes")))
                        ));
    }

    @Test
    public void fetchsResultsWithEqual() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].attribute.id", todosOsItens(is(1))))
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].value", todosOsItens(equalTo("Teste"))));
    }

    @Test
    public void fetchsResultsWithNotEqual() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-not-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(
                                "$[*].singleSourceAttributes[?(@.attribute.id==1)].value",
                                todosOsItens(not(equalTo("Teste")))
                        ));
    }

    @Test
    public void fetchsResultsWithEqualAndEqual() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal-and-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].attribute.id", todosOsItens(is(1))))
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].value", todosOsItens(equalTo("Teste"))));
    }

    @Test
    public void fetchsResultsWithEqualOrEqual() throws Exception {
        mockMvc().perform(post(RESOURCE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("querybuilder/query-equal-or-equal"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].attribute.id", todosOsItens(is(1))))
                .andExpect(jsonPath("$[*].singleSourceAttributes[*].value", todosOsItens(equalTo("Teste"))));
    }

    @Test
    public void sqlWithInnerGroups() throws Exception {
        mockMvc().perform(post(RESOURCE_PREVIEW)
                .contentType(MediaType.APPLICATION_JSON)
                .param("split", "true")
                .content(getJson("querybuilder/query-inner-groups"))
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/sql"))
                .andExpect(content().string(startsWith("WHERE")))
//                .andExpect(content().string("where ( ( generatedAlias1.value=:param0 ) and ( generatedAlias1.attribute.id=1L ) ) and ( ( ( generatedAlias1.value=:param1 ) and ( generatedAlias1.attribute.id=1L ) ) or ( ( ( generatedAlias1.value=:param2 ) and ( generatedAlias1.attribute.id=1L ) ) or ( ( generatedAlias1.value=:param3 ) and ( generatedAlias1.attribute.id=1L ) ) ) )"))
                ;
    }
}
