package br.com.cds.connecta.presenter.entity.viewer;

import br.com.cds.connecta.presenter.domain.SingleSourceGroupViewerVisualizationEnum;
import br.com.cds.connecta.presenter.entity.Group;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.search.annotations.Indexed;

/**
 * The persistent class for the TA_SNGL_SRC_VW_GROUP database table.
 *
 */
@Entity
@Table(name = "TA_SNGL_SRC_VW_GROUP")
@Indexed
public class SingleSourceGroupViewer extends Viewer {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_GROUP")
    private Group group;
    
    @Column(name = "TP_VISUALIZATION")
    @Enumerated(EnumType.STRING)
    private SingleSourceGroupViewerVisualizationEnum visualization;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public SingleSourceGroupViewerVisualizationEnum getVisualization() {
        return visualization;
    }

    public void setVisualization(SingleSourceGroupViewerVisualizationEnum visualization) {
        this.visualization = visualization;
    }
}
