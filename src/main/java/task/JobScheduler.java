package task;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

//서버켜질때 딱 한번 수행 
public class JobScheduler extends HttpServlet {
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		try {
			//서블릿에 반영 
			//TestJob실행해서 호출
			//스케쥴러 팩토리
			SchedulerFactory factory = new StdSchedulerFactory();
			//스케쥴러 생성
			Scheduler scheduler;
			scheduler = factory.getScheduler();
			scheduler.start();
			//1. 작업
			JobDetail jobDetail = 
					JobBuilder
					.newJob(OrganizeFileJob.class)
					.build();
			//2. 규칙(cron) "0 0 2 * * ?"
			//특정시간에 파일 일괄삭제 (ex:새벽2시~4시)
			//0 * * * * ? <<1분에 한번삭제
			CronTrigger cronTrigger = 
					TriggerBuilder
					.newTrigger()
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 2 * * ?"))
					.build();
			//스케쥴러에 작업, 규칙 부여
			scheduler.scheduleJob(jobDetail,cronTrigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
		
	}
	

