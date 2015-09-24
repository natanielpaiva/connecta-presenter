package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.entity.SingleSource;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the TB_SNGL_SRC_VIEWER database table.
 *
 */
@Entity
@Table(name = "TB_SNGL_SRC_VIEWER")
public class SingleSourceViewer extends Viewer {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_SINGLE_SOURCE")
    private SingleSource singleSource;

    public SingleSource getSingleSource() {
        return singleSource;
    }

    public void setSingleSource(SingleSource singleSource) {
        this.singleSource = singleSource;
    }
}
