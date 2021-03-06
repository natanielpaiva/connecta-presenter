package br.com.cds.connecta.presenter.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.cds.connecta.framework.core.entity.AbstractBaseEntity;

@Entity
@Table(name = "TA_SNGL_SRC_GROUP")
public class SingleSourceGroup extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_SNGL_SRC_GROUP")
    private Long id;

    @Column(name = "NU_ORDER")
    private BigDecimal numOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SINGLE_SOURCE")
    private SingleSource singleSource;

    /**
     *
     * @return
     */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getNumOrder() {
        return numOrder;
    }

    public void setNumOrder(BigDecimal numOrder) {
        this.numOrder = numOrder;
    }

    public SingleSource getSingleSource() {
        return singleSource;
    }

    public void setSingleSource(SingleSource singleSource) {
        this.singleSource = singleSource;
    }

}
