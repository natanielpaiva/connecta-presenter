package br.com.cds.connecta.presenter.quartz;

import java.text.ParseException;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import br.com.cds.connecta.presenter.entity.QuartzJob;

@Component
public class QuartzConfiguration {
	
	private String name;
	private String group;
	private String expression;
	
	public void setConfigutarion(QuartzJob job){
		this.name = job.getName();
		this.group = job.getGroup();
		this.expression = "0 0/"+ job.getInterval() +" * 1/1 * ? *"; //Ex: 0 0/10 * 1/1 * ? * -- Every 10 minute
	}
	
	public JobDetailFactoryBean jobDetailFactoryBean() {
		JobDetailFactoryBean jobDetail = new JobDetailFactoryBean();
		jobDetail.setJobClass(RefreshAnalysisJob.class);
		jobDetail.setDurability(true);
		jobDetail.setName(this.name);
		jobDetail.setGroup(this.group);
		
		jobDetail.afterPropertiesSet();
		return jobDetail;
	}

	public CronTriggerFactoryBean cronTriggerFactoryBean() throws ParseException {
		CronTriggerFactoryBean stFactory = new CronTriggerFactoryBean();
		stFactory.setJobDetail(jobDetailFactoryBean().getObject());
		stFactory.setCronExpression(this.expression);
        stFactory.setName("analysisTrigger");
        
		stFactory.afterPropertiesSet();
		return stFactory;
	}
}
