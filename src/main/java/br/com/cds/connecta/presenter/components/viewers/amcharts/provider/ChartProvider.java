package br.com.cds.connecta.presenter.components.viewers.amcharts.provider;

import br.com.cds.connecta.framework.amcharts.ChartTemplateType;
import br.com.cds.connecta.framework.amcharts.ChartTemplate;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */

@Component
public class ChartProvider {

    @Autowired
    private ApplicationContext context;
    
    private final Logger logger = Logger.getLogger(getClass());

    private static final String CHART_TEMPLATES = "/chart-templates";
    private static final String INVALID_TEMPLATE_ID = "Invalid template ID";
    private static final String JSON_EXT = ".json";
    private static final String CHARTJS_FOLDER = "chartjs";

    /**
     * Lista todos os tipos de templates de Gráfico disponíveis populados com os
     * respectivos templates, utilizando como fonte as pastas localizadas em
     * /chart-templates no classpath.
     * @param realPath 
     *
     * @return A lista de ChartTemplateType
     */
    public Collection<ChartTemplateType> listTemplateTypes() {
    	
    	
        File templateTypeFolder = new File(getFilePath(CHART_TEMPLATES));

        logger.info("Chart template folder IS: "+templateTypeFolder);
        logger.info("Chart template folder exists? "+templateTypeFolder.exists());
        logger.info("Chart template folder children "+Arrays.toString(templateTypeFolder.listFiles()));
        logger.info("Chart template folder children length "+templateTypeFolder.listFiles().length);

        File[] folders = templateTypeFolder.listFiles();


        Set<ChartTemplateType> types = new HashSet<>(folders.length);
        for (File folder : folders) {
        	if(!CHARTJS_FOLDER.equals(folder.getName())){
	            Collection<ChartTemplate> templates = listTemplatesFor(folder.getName());
	            types.add(new ChartTemplateType(folder.getName(), templates));
        	}
        }

        return types;
    }

    /**
     * Retorna o objeto do template do gráfico.
     *
     * @param type O tipo do template
     * @param file O identificador do template
     * @return o ChartTemplate
     */
    public ChartTemplate getTemplate(String type, String file) {
        File json = getTemplateFile(type, file);

        return new ChartTemplate(json.getName().replace(JSON_EXT, ""));
    }

    /**
     * Retorna o arquivo do template
     *
     * @param type O tipo (pasta) do template
     * @param id O id (nome do arquivo) do template
     * @return O objeto File do template
     */
    private File getTemplateFile(String type, String id) {
        File file = new File(getFilePath(CHART_TEMPLATES.concat(File.separator)
                .concat(type)
                .concat(File.separator)
                .concat(id)
                .concat(JSON_EXT)));

        if (!file.exists()) {
            throw new RuntimeException(INVALID_TEMPLATE_ID);
        }

        return file;
    }

    /**
     * Lista todos os templates de um tipo de template especificado.
     *
     * @param type A String do tipo (pasta) do template
     * @return A lista de ChartTemplate
     */
    public Collection<ChartTemplate> listTemplatesFor(String type) {
        File[] jsonFiles = new File(getFilePath(CHART_TEMPLATES)
                .concat(File.separator)
                .concat(type))
                .listFiles();

        Set<ChartTemplate> templates = new HashSet<>(jsonFiles.length);
        for (File json : jsonFiles) {
            templates.add(new ChartTemplate(json.getName().replace(JSON_EXT, "")));
        }

        return templates;
    }

    private String getFilePath(String filename) {
        String path = "";
        
        try {
            path = context.getResource(filename).getFile().getPath();
            logger.info("Spring getResource returned PATH: "+path);
        } catch (IOException ex) {
            logger.error("Spring getResource gave IOException: ", ex);
    }
        return path;
//        return getClass().getClassLoader().getResource(filename).getPath();
    }

    /**
     * Lista todos os templates de um tipo de template especificado.
     *
     * @param type O objeto do ChartTemplateType
     * @return
     */
    public Collection<ChartTemplate> listTemplatesFor(ChartTemplateType type) {
        return listTemplatesFor(type.getId());
    }

    /**
     * Retorna o conteúdo do arquivo de template, em um JSON.
     *
     * @param type A string do tipo do template
     * @param templateName A string do id do template
     * @return
     */
    public String getTemplateContent(String type, String templateName) {
        File file = getTemplateFile(type, templateName);

        String content = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            content = IOUtils.toString(fis);
        } catch (IOException ex) {
            throw new RuntimeException(INVALID_TEMPLATE_ID, ex);
        }

        return content;
    }
    
}