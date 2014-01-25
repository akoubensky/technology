import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class HelloJob1 implements Job {
	// �� ���������� ���������� LOG4J ��� ������ ���������. ��� �� ����������
	// ���������� � Quartz, ��� ��� ���������� � �� ����� ����������.
	private static Logger logger = Logger.getLogger(HelloJob1.class);

	/**
	 * �����, ����������� ���������� ����������� ������.
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Hello, world...");
	}
	
	public static void main(String[] args) throws SchedulerException {
		// ���������� ������������� ������� ������� � ������� ��������� � �������.
		BasicConfigurator.configure();
		
		// ��������� ������������ �����. ����� ����� ���� ���.
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		scheduler.start();

		// ���������� ����� ������ � ����������� � � ������ ������ HelloJob
		JobDetail job = newJob(HelloJob1.class)
				.withIdentity("myJob", "group1")
				.build();

		// ������� ����������, �������� �������� ������ ����� �����������
		// ������ 10 ������, ���� �������� �����������.
		Trigger trigger = newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(simpleSchedule()
						.withIntervalInSeconds(10)
						.repeatForever())
						.build();

		// �������� ������ � � ���������� ������������.
		scheduler.scheduleJob(job, trigger);
		
		// �������� �� ������...
		try {
			Thread.sleep(60000);
		} catch (InterruptedException ex) {}
		
		// ... � ��������� ������ ������������ (���� �����-�� �� �����
		// ��� �� �����������, �� �� �������� � ����������). 
		scheduler.shutdown();
	}
}
