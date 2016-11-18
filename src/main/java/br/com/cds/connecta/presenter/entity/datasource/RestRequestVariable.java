package br.com.cds.connecta.presenter.entity.datasource;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "TB_REST_REQUEST_VARIABLE")
public class RestRequestVariable extends AbstractBaseEntity{
   
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_VARIABLE")
    private Long id;
    
    @Column(name = "TXT_NAME")
    private String name;
    
//    @Column(name = "TXT_VALUE")
//    private String value;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
//    public String getValue() {
//        return value;
//    }
//
//    public void setValue(String value) {
//        this.value = value;
//    }
}
