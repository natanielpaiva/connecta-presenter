package br.com.cds.connecta.presenter.entity.datasource;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import br.com.cds.connecta.presenter.domain.RequestBodyTypeEnum;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.http.HttpMethod;

/**
 *
 * @author diego
 */
@Entity
@Table(name = "TB_REQUEST_REST_DS")
public class RestDatasourceRequest extends AbstractBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_REQUEST_DS")
    private Long id;

    @Column(name = "TXT_NAME")
    private String name;

    @Column(name = "TXT_ADDRESS")
    private String url;

    @Column(name = "TXT_VERB")
    @Enumerated(EnumType.STRING)
    private HttpMethod verb;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name = "FK_REQUEST_HEADER", nullable = false)
    @JoinColumn(name = "FK_REST_REQUEST", nullable = false)
    private Set<RestRequestHeaderDatasource> headers;

    
    
    @Column(name = "TP_BODY")
    @Enumerated(EnumType.STRING)
    private RequestBodyTypeEnum typeBody;

    @Lob
    @Column(name = "TXT_BODY")
    private String body;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    //@JoinColumn(name = "FK_REQUEST_VARIABLE", nullable = false)
    @JoinColumn(name = "FK_REST_REQUEST_VARIABLE", nullable = false)
    private Set<RestRequestVariable> variables;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public HttpMethod getVerb() {
        return verb;
    }

    public void setVerb(HttpMethod verb) {
        this.verb = verb;
    }

    public Set<RestRequestHeaderDatasource> getHeaders() {
        return headers;
    }

    public void setHeaders(Set<RestRequestHeaderDatasource> headers) {
        this.headers = headers;
    }

    public RequestBodyTypeEnum getTypeBody() {
        return typeBody;
    }

    public void setTypeBody(RequestBodyTypeEnum typeBody) {
        this.typeBody = typeBody;
    }

    public Set<RestRequestVariable> getVariables() {
        return variables;
    }

    public void setVariables(Set<RestRequestVariable> variables) {
        this.variables = variables;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        if(this.id != null)
            hash = 53 * hash + Objects.hashCode(this.id);
        if(this.name != null)
            hash = 53 * hash + Objects.hashCode(this.name);
        if(this.url != null)
            hash = 53 * hash + Objects.hashCode(this.url);
        if(this.verb != null)
            hash = 53 * hash + Objects.hashCode(this.verb);
        if(this.headers != null)
            hash = 53 * hash + Objects.hashCode(this.headers);
        if(this.typeBody != null)
            hash = 53 * hash + Objects.hashCode(this.typeBody);
        hash = 53 * hash + Objects.hashCode(this.body);
        if(this.variables != null)
            hash = 53 * hash + Objects.hashCode(this.variables);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RestDatasourceRequest other = (RestDatasourceRequest) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (this.verb != other.verb) {
            return false;
        }
        if (!Objects.equals(this.headers, other.headers)) {
            return false;
        }
        if (this.typeBody != other.typeBody) {
            return false;
        }
        if (!Objects.equals(this.body, other.body)) {
            return false;
        }
        if (!Objects.equals(this.variables, other.variables)) {
            return false;
        }
        return true;
    }

    
}
