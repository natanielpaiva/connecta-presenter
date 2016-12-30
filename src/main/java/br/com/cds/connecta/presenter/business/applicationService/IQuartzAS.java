package br.com.cds.connecta.presenter.business.applicationService;

import org.quartz.SchedulerException;

import br.com.cds.connecta.presenter.entity.QuartzJob;

public interface IQuartzAS {
	
	QuartzJob get(Long id);
    
    Iterable<QuartzJob> list();
    
    QuartzJob findByName(String name);
    
    QuartzJob save(QuartzJob job);
    
    void rescheduleJob(QuartzJob job) throws SchedulerException;

}
