package cn.gson.oasys.schedule.quarte;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Configuration
//@EnableScheduling

//默认条件注解是开启的，现在采用配置文件的变量来手动控制定时任务是否执行
@ConditionalOnProperty(prefix = "scheduling", name = "enabled", havingValue = "false")
public class DynamicScheduleTask implements SchedulingConfigurer {
    @Mapper
    public interface CronMapper {
        @Select("select cron from cron limit 1")
        public String getCron();

        @Select("SELECT CRON FROM CRON WHERE CRON_ID = '2'")
        public String getCron1();
    }

    @Autowired      //注入mapper
    @SuppressWarnings("all")
    CronMapper cronMapper;

    /**
     * 执行定时任务.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();
                    String cron1 = cronMapper.getCron1();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
//                         Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
