
package br.com.cds.connecta.presenter.entity.analysis;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "TB_WS_ANALYSIS_PARAMETER")
public class WebserviceAnalysisParameter extends AbstractBaseEntity {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_WS_ANALYSIS_PARAMETER")
    private Long id;
    
    @Column(name = "TXT_PARAMS")
    private String params;
    
    @Column(name = "TXT_VALUE")
    private String value;
    
    @Column(name = "TXT_ATTRIBUTES")
    private String attributes;
    
   
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }
    
}
