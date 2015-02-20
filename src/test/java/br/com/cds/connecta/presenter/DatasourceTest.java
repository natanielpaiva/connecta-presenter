/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter;

import static br.com.cds.connecta.framework.core.test.ConnectaMatchers.*;
import br.com.cds.connecta.presenter.domain.DatabaseDatasourceDriverEnum;
import br.com.cds.connecta.presenter.domain.DatasourceTypeEnum;
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
public class DatasourceTest extends BaseTest {

    static final String RESOURCE = REST_PATH.concat("datasource");

    static final String RESOURCE_DATABASE = RESOURCE.concat("/database");
    static final String RESOURCE_ENDECA = RESOURCE.concat("/endeca");
    static final String RESOURCE_HDFS = RESOURCE.concat("/hdfs");
    static final String RESOURCE_BI = RESOURCE.concat("/bi");
    static final String RESOURCE_SOLR = RESOURCE.concat("/solr");
    static final String RESOURCE_WEBSERVICE = RESOURCE.concat("/webservice");

    static final String RESOURCE_ID = RESOURCE.concat("/{id}");

    @Test
    @Ignore
    public void getDatasourceID() throws Exception {
        mockMvc().perform(get(RESOURCE_ID, 1)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", equalTo("")))
                .andExpect(jsonPath("$.datasource", nullValue()));
    }

    @Test
    public void saveDatabaseDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_DATABASE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("datasource/new-database-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.driver", equalTo(DatabaseDatasourceDriverEnum.POSTGRES.name())))
                .andExpect(jsonPath("$.server", equalTo("teste")))
                .andExpect(jsonPath("$.port", equalTo(666)))
                .andExpect(jsonPath("$.sid", equalTo("teste")))
                .andExpect(jsonPath("$.schema", equalTo("teste")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", equalTo(DatasourceTypeEnum.DATABASE.name())))
                .andExpect(jsonPath("$.user", equalTo("s")))
                .andExpect(jsonPath("$.password", equalTo("s")))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));

    }

    @Test
    public void saveEndecaDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_ENDECA)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("datasource/new-endeca-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", equalTo(DatasourceTypeEnum.ENDECA.name())))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));

    }

    @Test
    public void saveBIDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_BI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("datasource/new-bi-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                .andExpect(jsonPath("$.path", equalTo("caminho")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", equalTo(DatasourceTypeEnum.BI.name())))
                .andExpect(jsonPath("$.user", equalTo("s")))
                .andExpect(jsonPath("$.password", equalTo("s")))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));

    }

    @Test
    public void saveSolrDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_SOLR)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("datasource/new-solr-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                .andExpect(jsonPath("$.path", equalTo("caminho")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", equalTo(DatasourceTypeEnum.SOLR.name())))
                .andExpect(jsonPath("$.user", equalTo("s")))
                .andExpect(jsonPath("$.password", equalTo("s")))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));

    }

    @Test
    public void saveWebserviceDatasource() throws Exception {
        mockMvc().perform(post(RESOURCE_WEBSERVICE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("datasource/new-webservice-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.address", equalTo("endereco")))
                .andExpect(jsonPath("$.method", equalTo("metodo")))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", equalTo(DatasourceTypeEnum.WEBSERVICE.name())))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))))
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
                .contentType(MediaType.APPLICATION_JSON)
                .content(getJson("datasource/new-hdfs-datasource"))
        ).andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.server", equalTo("server")))
                .andExpect(jsonPath("$.path", equalTo("caminho")))
                .andExpect(jsonPath("$.port", equalTo(666)))
                .andExpect(jsonPath("$.name", equalTo("Novodatasource")))
                .andExpect(jsonPath("$.description", equalTo("s")))
                .andExpect(jsonPath("$.type", equalTo(DatasourceTypeEnum.HDFS.name())))
                .andExpect(jsonPath("$.id", allOf(notNullValue(), isA(Integer.class), greaterThan(0))));

    }

}
