package br.com.cds.connecta.presenter;

import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.enumKeyFor;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.peloMenosUmItem;
import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.todosOsItens;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.isEmptyString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;

import br.com.cds.connecta.framework.core.domain.MessageTypeEnum;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.domain.DatasourceTypeEnum;

/**
 *
 * @author Diego Rego <diego.rego@cds.com.br>
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */

public class DatasourceTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("datasource");

    static final String RESOURCE_DATABASE = RESOURCE.concat("/database");
    static final String RESOURCE_ENDECA = RESOURCE.concat("/endeca");
    static final String RESOURCE_HDFS = RESOURCE.concat("/hdfs");
    static final String RESOURCE_BI = RESOURCE.concat("/obiee");
    static final String RESOURCE_SOLR = RESOURCE.concat("/solr");
    static final String RESOURCE_WEBSERVICE = RESOURCE.concat("/webservice");

    static final String RESOURCE_ID = RESOURCE.concat("/{id}");

    @Test
    public void paginationError() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .contentType(MEDIATYPE_JSON_UTF8)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.message", equalTo("Invalid pagination")))
                .andExpect(jsonPath("$.type", enumKeyFor(MessageTypeEnum.WARN)));
    }
    
    @Test
    public void listPagedDatasources() throws Exception {
        mockMvc().perform(get(RESOURCE)
                .param("page", "1")
                .param("count", "2")
                .contentType(MEDIATYPE_JSON_UTF8)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.content[*]", hasSize(2)))
                .andExpect(jsonPath("$.content[*].id", todosOsItens(isA(Integer.class))))
                .andExpect(jsonPath("$.content[*].name", todosOsItens(not(isEmptyString()))))
                .andExpect(jsonPath("$.content[*].description", todosOsItens(not(isEmptyString()))))
                .andExpect(jsonPath("$.content[*].user", todosOsItens(not(isEmptyString()))))
                .andExpect(jsonPath("$.content[*].password", todosOsItens(not(isEmptyString()))))
                .andExpect(jsonPath("$.content[*].type", todosOsItens(not(isEmptyString()))));
    }

    @Test
    public void getDatasourceID() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 1)
                .contentType(MEDIATYPE_JSON_UTF8)
                .header("Domain", "cds")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("Teste")))
                .andExpect(jsonPath("$.description", equalTo("Descrição teste")))
                .andExpect(jsonPath("$.user", equalTo("usuario")))
                .andExpect(jsonPath("$.password", equalTo("123456")))
                .andExpect(jsonPath("$.type", enumKeyFor(DatasourceTypeEnum.DATABASE)));
    }
    
    @Test
    public void datasourceNotFound() throws Exception {
        doesntExist(666);
    }

    @Test
    public void saveDatabaseDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_DATABASE)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content(getJson("datasource/new-database-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.driver", enumKeyFor(DatabaseDatasourceDriverEnum.POSTGRESQL)))
                .andExpect(jsonPath("$.server", equalTo("teste")))
                .andExpect(jsonPath("$.port", equalTo(666)))
                .andExpect(jsonPath("$.sid", equalTo("teste")))
                .andExpect(jsonPath("$.schema", equalTo("teste")))
                .andExpect(jsonPath("$.name", equalTo("Novódatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", enumKeyFor(DatasourceTypeEnum.DATABASE)))
                .andExpect(jsonPath("$.user", equalTo("s")))
                .andExpect(jsonPath("$.password", equalTo("s")))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));
    }

    @Test
    public void saveEndecaDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_ENDECA)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content(getJson("datasource/new-endeca-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", enumKeyFor(DatasourceTypeEnum.ENDECA)));
    }

    @Test
    public void saveBIDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_BI)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content(getJson("datasource/new-bi-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                .andExpect(jsonPath("$.path", equalTo("caminho")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", enumKeyFor(DatasourceTypeEnum.OBIEE)))
                .andExpect(jsonPath("$.user", equalTo("s")))
                .andExpect(jsonPath("$.password", equalTo("s")))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));
    }

    @Test
    public void saveSolrDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_SOLR)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content(getJson("datasource/new-solr-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                .andExpect(jsonPath("$.path", equalTo("caminho")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", enumKeyFor(DatasourceTypeEnum.SOLR)))
                .andExpect(jsonPath("$.user", equalTo("s")))
                .andExpect(jsonPath("$.password", equalTo("s")))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));

    }

    @Test
    public void saveWebserviceDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_WEBSERVICE)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content(getJson("datasource/new-webservice-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                //.andExpect(jsonPath("$.method", equalTo("metodo")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", enumKeyFor(DatasourceTypeEnum.WEBSERVICE)))
                .andExpect(jsonPath("$.parameters[*].params", allOf(
                                        peloMenosUmItem(equalTo("ww")),
                                        peloMenosUmItem(equalTo("ddd"))
                                )))
                .andExpect(jsonPath("$.parameters[*].value", allOf(
                                        peloMenosUmItem(equalTo("ww")),
                                        peloMenosUmItem(equalTo("aaa"))
                                )));

    }

    @Test
    public void saveHDFSDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_HDFS)
                .contentType(MEDIATYPE_JSON_UTF8)
                .content(getJson("datasource/new-hdfs-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MEDIATYPE_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.server", equalTo("server")))
                .andExpect(jsonPath("$.path", equalTo("caminho")))
                .andExpect(jsonPath("$.hdfsPort", equalTo(666)))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", enumKeyFor(DatasourceTypeEnum.HDFS)))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));
    }
    
    @Test
    /**
     * 
     * TODO 
     * 	MODIFY SPECTED STATUS IN TESTE
     */
    
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
