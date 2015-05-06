package br.com.cds.connecta.presenter;

import br.com.cds.connecta.framework.core.test.MockMvcProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Teste base de todos os projetos
 *
 * @author pires
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ContextConfiguration("classpath:spring/test-application-context.xml")
public class BaseTest {

    @Autowired
    private MockMvcProvider mmp;
    private Logger logger;
    protected static final String REST_PATH = "/";
    protected static final String TEST_RESOURCE_FOLDER = "src/test/resources/";
    protected static final String FILE_CHARSET = "UTF-8";
    
    protected static final MediaType MEDIATYPE_JSON_UTF8 = MediaType.parseMediaType(MediaType.APPLICATION_JSON_VALUE+";charset="+FILE_CHARSET);

    /**
     * Getter da configuração de {@link MockMvc} da {@link BaseTest}
     *
     * @return
     */
    public MockMvc mockMvc() {
        return mmp.getInstance();
    }

    /**
     * Getter do Logger
     *
     * @return
     */
    public Logger logger() {
        if (logger == null) {
            logger = Logger.getLogger(getClass());
        }
        return logger;
    }

    /**
     * Busca o conteúdo de um JSON da pasta src/test/resources/json como texto.
     *
     * Exemplo:
     * <br />
     * <b>Diretório:</b>
     * <pre>
     * src/
     *  test/
     *   resources/
     *    json/
     *     meu-modulo/
     *     - objeto-qualquer.json
     * </pre>
     *
     * <b>Código:</b>
     * <pre>
     * // json = "{ ... }"
     * String json = getJson("meu-modulo/objeto-qualquer");
     * </pre>
     *
     * @param jsonName
     * @return O conteúdo do JSON
     */
    public String getJson(String jsonName) {
        return getTestResourceContent(String.format("json/%s.json", jsonName));
    }

    /**
     * Retorna o conteúdo de um recurso da pasta src/test/resources como texto.
     *
     * Exemplo:
     *
     * <br />
     *
     * <b>Diretório:</b>
     * <pre>
     * src/
     *  test/
     *   resources/
     *    pasta/
     *    - arquivo.txt
     * </pre>
     *
     * <b>Código:</b>
     * <pre>
     * String conteudo = getTestResourceContent("pasta/arquivo.txt");
     * </pre>
     *
     * @param fileName
     * @return O conteúdo do arquivo
     */
    public String getTestResourceContent(String fileName) {
        try {
            InputStream is = getTestResource(fileName);
            return IOUtils.toString(is, FILE_CHARSET);
        } catch (IOException ex) {
            logger().error("Erro ao buscar arquivo " + fileName, ex);
        }
        return null;
    }

    public FileInputStream getTestResource(String fileName) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(TEST_RESOURCE_FOLDER.concat(fileName));
            return fileInputStream;
        } catch (FileNotFoundException ex) {
            logger().error("Arquivo não encontrado: " + fileName, ex);
        }
        return fileInputStream;
    }

    public MockHttpServletRequestBuilder multipart(MockHttpServletRequestBuilder request, String... files) throws IOException {
        for (String filename : files) {
            FileInputStream fis = getTestResource(String.format("file/%s", filename));
            MockMultipartFile file = new MockMultipartFile(filename, fis);
            request.content(file.getBytes());
        }

        Map<String, String> contentTypeParams = new HashMap<>();
        contentTypeParams.put("boundary", "265001916915724");
        MediaType mediaType = new MediaType("multipart", "form-data", contentTypeParams);
        
        request.contentType(mediaType);

//        .contentType(MediaType.MULTIPART_FORM_DATA)
        return request;
    }

    /**
     * Mantido apenas para execução do JUnit
     */
    @Test
    public void baseTest() {
    }

}
