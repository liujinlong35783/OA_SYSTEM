package cn.gson.oasys.schedule.quarte;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
@PropertySource("classpath:cronDate.properties")
@ConditionalOnProperty(prefix = "scheduling" , name = "enabled" , havingValue = "true")
public class StaticPropertiesScheduleTask {

    @Value("${cron}")
    public String cron;

    @Value("${cronNumber}")
    public String cronNumber;

    @Scheduled(cron = "0/10 * * * * ?")
    public void schedule1(){
        System.out.println(cronNumber+ LocalDateTime.now());
        System.out.println(cron+ LocalDateTime.now());
    }
}
