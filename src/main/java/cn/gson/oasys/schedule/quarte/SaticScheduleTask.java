package cn.gson.oasys.schedule.quarte;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration      //用于标注配置类 ,兼备Component的效果
//@EnableScheduling   //开启定时任务

//默认条件注解是开启的，现在采用配置文件的变量来手动控制定时任务是否执行
@ConditionalOnProperty(prefix = "scheduling", name = "enabled", havingValue = "false")
public class SaticScheduleTask {
    //添加定时任务
    @Scheduled(cron="0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
