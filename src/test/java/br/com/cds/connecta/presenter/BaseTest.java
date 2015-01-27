package br.com.cds.connecta.presenter;

import br.com.cds.connecta.framework.core.test.MockMvcProvider;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Teste base de todos os projetos
 * @author pires
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@EnableWebMvc
@ContextConfiguration("classpath:META-INF/br.com.cds.connecta.framework.test.xml")
public class BaseTest {
    @Autowired
    private MockMvcProvider mmp;
    protected Logger logger;
    protected static final String REST_PATH = "/";
    protected static final String TEST_RESOURCE_FOLDER = "src/test/resources/";
    protected static final String FILE_CHARSET = Charset.defaultCharset().displayName();
    
    /**
     * Getter da configuração de {@link MockMvc} da {@link BaseTest}
     * @return 
     */
    protected MockMvc mockMvc() {
        return mmp.getInstance();
    }
    
    /**
     * Getter do Logger
     * @return 
     */
    protected Logger logger() {
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
    protected String getJson(String jsonName) {
        return  getTestResourceContent(String.format("json/%s.json", jsonName));
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
    protected String getTestResourceContent(String fileName) {
        try {
            InputStream is = new FileInputStream(TEST_RESOURCE_FOLDER.concat(fileName));
            return IOUtils.toString(is, FILE_CHARSET);
        } catch (IOException ex) {
            logger().error("Erro ao buscar arquivo "+fileName, ex);
        }
        return null;
    }

    /**
     * Mantido apenas para execução do JUnit 
     */
    @Test
    public void baseTest() {}

}
