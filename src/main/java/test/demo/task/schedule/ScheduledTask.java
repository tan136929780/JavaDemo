//package test.demo.task.schedule;
//
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//@EnableScheduling
//@EnableAsync
//public class ScheduledTask {
//    @Async
//    @Scheduled(cron = "*/5 * * * * *")
//    public void first() throws InterruptedException {
//        System.out.println((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
//    }
//}
