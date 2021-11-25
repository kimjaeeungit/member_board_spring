package task;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//파일스케쥴러 파일이 여기 내부로 옴
		System.out.println("잡 실행 ::" + new Date());
		
	}
	
	
}
