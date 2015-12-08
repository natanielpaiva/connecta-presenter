package br.com.cds.connecta.presenter.components.amcharts.provider;

import br.com.cds.connecta.framework.amcharts.ChartTemplate;
import br.com.cds.connecta.framework.amcharts.ChartTemplateType;
import br.com.cds.connecta.presenter.components.viewers.amcharts.provider.ChartProvider;
import java.io.File;
import java.util.Collection;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Vinicius Pires <vinicius.pires@cds.com.br>
 */
public class ChartProviderTest {
    
    private static final String CHART_TEMPLATES = "chart-templates";
    private final ChartProvider provider = new ChartProvider();
    
    @Test
    public void providerListsTemplateTypes() {
        Collection<ChartTemplateType> list = provider.listTemplateTypes();
        
        assertThat(list, hasSize(7));
        
        for (ChartTemplateType type : list) {
            Collection<ChartTemplate> templates = type.getTemplates();
            
            assertThat(type.getId(), notNullValue());
            assertThat(templates, notNullValue());
            assertThat(templates, hasSize(greaterThan(1)));
            
            for (ChartTemplate template : templates) {
                assertThat(template.getId(), notNullValue());
                
                
                String fileString = getClass().getClassLoader().getResource(CHART_TEMPLATES).getPath();
                String completePath = fileString.concat(File.separator)
                        .concat(type.getId())
                        .concat(File.separator)
                        .concat(template.getId())
                        .concat(".json");
                
                File file = new File(completePath);
                
                assertThat(file.exists(), is(true));
            }
        }
    }
    
    @Test
    public void providerReturnsTemplate() {
        ChartTemplate template = provider.getTemplate("area", "area-area");
        assertThat(template, notNullValue());
        assertThat(template.getId(), is("area-area"));
    }
    
    @Test
    public void providerListsTemplatesFromType() {
        Collection<ChartTemplate> templates = provider.listTemplatesFor("area");
        assertThat(templates, notNullValue());
        assertThat(templates, hasSize(10));
        
        
        templates = provider.listTemplatesFor(new ChartTemplateType("area"));
        assertThat(templates, notNullValue());
        assertThat(templates, hasSize(10));
        
        for (ChartTemplate template : templates) {
            assertThat(template.getId(), startsWith("area-"));
        }
    }
    
    @Test
    public void providerReturnsTemplateContent() {
        String json = provider.getTemplateContent("area", "area-area");
        
        assertThat(json, notNullValue());
    }
}
