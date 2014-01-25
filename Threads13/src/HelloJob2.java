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
	// ћы используем библиотеку LOG4J дл€ вывода сообщений. Ёту же библиотеку
	// использует и Quartz, так что подключать еЄ всЄ равно необходимо.
	private static Logger logger = Logger.getLogger(HelloJob2.class);

	/**
	 * ћетод, реализующий содержание исполн€емой работы.
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// »з переданного контекста извлекаем им€ исполн€емой работы и еЄ аргументы.
		JobKey key = context.getJobDetail().getKey();
		JobDataMap dataMap = context.getMergedJobDataMap();
		String name = dataMap.getString("name");
		
		logger.info(key + " says: Hello, " + name);
	}
	
	public static void main(String[] args) throws SchedulerException {
		// ѕростейша€ инициализаци€ ведени€ журнала с выводом сообщений в консоль.
		BasicConfigurator.configure();
		
		// «апускаем исполнителей работ. —амих работ пока нет.
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		scheduler.start();
		
		// «адаем врем€ начала исполнени€ работ
		Calendar start1 = Calendar.getInstance();
		start1.add(Calendar.SECOND, 5);
		
		Calendar start2 = Calendar.getInstance();
		start2.add(Calendar.SECOND, 8);

		// ќпредел€ем две новые работы и прив€зываем их к классу HelloJob
		JobDetail job1 = newJob(HelloJob2.class)
				.withIdentity("myJob1", "group1")
				.usingJobData("name", "John")
				.build();
		JobDetail job2 = newJob(HelloJob2.class)
				.withIdentity("myJob2", "group1")
				.usingJobData("name", "Jack")
				.build();

		// —оздаем расписани€, согласно которым работы будут запускатьс€
		// каждые 10 и 7 секунд соответственно, пока работают исполнители.
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

		// ѕередаем работы и их расписани€ исполнител€м.
		scheduler.scheduleJob(job1, trigger1);
		scheduler.scheduleJob(job2, trigger2);
		
		// «асыпаем на две минуты...
		try {
			Thread.sleep(120000);
		} catch (InterruptedException ex) {}
		
		// ... и завершаем работу исполнителей (если кака€-то из работ
		// еще не закончилась, то мы подождем еЄ завершени€). 
		scheduler.shutdown();
	}
}
