package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.entity.SingleSource;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

/**
 * The persistent class for the TB_SNGL_SRC_VIEWER database table.
 *
 */
@Entity
@Table(name = "TB_SNGL_SRC_VIEWER")
@NamedQuery(
    name = "SingleSourceViewer.get",
    query = "SELECT v FROM SingleSourceViewer v LEFT JOIN FETCH v.singleSource s "
          + "LEFT JOIN FETCH s.singleSourceAttributes a WHERE v.id = :id"
)
@Indexed
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
