package br.com.cds.connecta.presenter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_QUARTZ_JOB")
public class QuartzJob {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_QUARTZ_JOB")
	private Long id;
    @Column(name = "NM_JOB")
	private String name;
    @Column(name = "NM_GROUP")
	private String group;
	@Column(name = "QTD_INTERVAL")
	private int interval;
	
	public QuartzJob(){}
	
	public QuartzJob(String name, String group, int interval){
		this.name = name;
		this.group = group;
		this.interval = interval;
	}

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

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	

}
