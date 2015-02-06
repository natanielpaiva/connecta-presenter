package br.com.cds.connecta.presenter.entity.datasource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;
import javax.persistence.MapsId;

/**
 * The persistent class for the TB_ENDECA_DS database table.
 *
 */
@Entity
@Table(name = "TB_ENDECA_DS")
@NamedQuery(name = "EndecaDatasource.findAll", query = "SELECT t FROM EndecaDatasource t")
public class EndecaDatasource extends AbstractBaseEntity implements ITypedDatasource {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TB_ENDECA_DS_PKENDECADS_GENERATOR", sequenceName = "TB_ENDECA_DS_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TB_ENDECA_DS_PKENDECADS_GENERATOR")
    @Column(name = "PK_ENDECA_DS")
    private Long id;

    @Column(name = "TXT_ADDRESS")
    private String address;

    //bi-directional many-to-one association to TbDatasource
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_DATASOURCE")
    @MapsId
    private Datasource datasource;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

}
