import java.util.Calendar;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

public class HelloJob2 implements Job {
	// �� ���������� ���������� LOG4J ��� ������ ���������. ��� �� ����������
	// ���������� � Quartz, ��� ��� ���������� � �� ����� ����������.
	private static Logger logger = Logger.getLogger(HelloJob2.class);

	/**
	 * �����, ����������� ���������� ����������� ������.
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// �� ����������� ��������� ��������� ��� ����������� ������ � � ���������.
		JobKey key = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getMergedJobDataMap();
		String name = dataMap.getString("name");
		
		logger.info(key + " says: Hello, " + name);
	}
	
	public static void main(String[] args) throws SchedulerException {
		// ���������� ������������� ������� ������� � ������� ��������� � �������.
		BasicConfigurator.configure();
		
		// ��������� ������������ �����. ����� ����� ���� ���.
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		scheduler.start();
		
		// ������ ����� ������ ���������� �����
		Calendar start1 = Calendar.getInstance();
		start1.add(Calendar.SECOND, 5);
		
		Calendar start2 = Calendar.getInstance();
		start2.add(Calendar.SECOND, 8);

		// ���������� ��� ����� ������ � ����������� �� � ������ HelloJob
		JobDetail job1 = newJob(HelloJob2.class)
				.withIdentity("myJob1", "group1")
				.usingJobData("name", "John")
				.build();
		JobDetail job2 = newJob(HelloJob2.class)
				.withIdentity("myJob2", "group1")
				.usingJobData("name", "Jack")
				.build();

		// ������� ����������, �������� ������� ������ ����� �����������
		// ������ 10 � 7 ������ ��������������, ���� �������� �����������.
		Trigger trigger1 = newTrigger()
				.withIdentity("myTrigger1", "group1")
				.startAt(start1.getTime())
				.withSchedule(simpleSchedule()
						.withIntervalInSeconds(10)
						.repeatForever())
						.build();
		Trigger trigger2 = newTrigger()
				.withIdentity("myTrigger2", "group1")
				.startAt(start2.getTime())
				.withSchedule(simpleSchedule()
						.withIntervalInSeconds(7)
						.repeatForever())
						.build();

		// �������� ������ � �� ���������� ������������.
		scheduler.scheduleJob(job1, trigger1);
		scheduler.scheduleJob(job2, trigger2);
		
		// �������� �� ��� ������...
		try {
			Thread.sleep(120000);
		} catch (InterruptedException ex) {}
		
		// ... � ��������� ������ ������������ (���� �����-�� �� �����
		// ��� �� �����������, �� �� �������� � ����������). 
		scheduler.shutdown();
	}
}
