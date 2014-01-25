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
	// Мы используем библиотеку LOG4J для вывода сообщений. Эту же библиотеку
	// использует и Quartz, так что подключать её всё равно необходимо.
	private static Logger logger = Logger.getLogger(HelloJob1.class);

	/**
	 * Метод, реализующий содержание исполняемой работы.
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		logger.info("Hello, world...");
	}
	
	public static void main(String[] args) throws SchedulerException {
		// Простейшая инициализация ведения журнала с выводом сообщений в консоль.
		BasicConfigurator.configure();
		
		// Запускаем исполнителей работ. Самих работ пока нет.
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		Scheduler scheduler = schedulerFactory.getScheduler();

		scheduler.start();

		// Определяем новую работу и привязываем её к нашему классу HelloJob
		JobDetail job = newJob(HelloJob1.class)
				.withIdentity("myJob", "group1")
				.build();

		// Создаем расписание, согласно которому работа будет запускаться
		// каждые 10 секунд, пока работают исполнители.
		Trigger trigger = newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(simpleSchedule()
						.withIntervalInSeconds(10)
						.repeatForever())
						.build();

		// Передаем работу и её расписание исполнителям.
		scheduler.scheduleJob(job, trigger);
		
		// Засыпаем на минуту...
		try {
			Thread.sleep(60000);
		} catch (InterruptedException ex) {}
		
		// ... и завершаем работу исполнителей (если какая-то из работ
		// еще не закончилась, то мы подождем её завершения). 
		scheduler.shutdown();
	}
}
