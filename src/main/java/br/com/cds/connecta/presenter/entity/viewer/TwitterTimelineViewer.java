package br.com.cds.connecta.presenter.entity.viewer;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name = "TB_TWITTER_TL_VIEWER")
@Indexed
public class TwitterTimelineViewer extends Viewer {

	private String twitterUser;

	public String getTwitterUser() {
		return twitterUser;
	}

	public void setTwitterUser(String twitterUser) {
		this.twitterUser = twitterUser;
	}
	
}
