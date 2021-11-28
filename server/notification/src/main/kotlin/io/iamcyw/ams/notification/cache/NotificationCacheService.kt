package io.iamcyw.ams.notification.cache

import io.iamcyw.ams.job.strategy.entity.AlarmSourcePO
import io.iamcyw.ams.job.strategy.entity.AlarmStrategyPO
import io.iamcyw.ams.notification.cache.entity.AlarmPO
import io.iamcyw.ams.notification.cache.entity.AlarmStatus
import io.iamcyw.ams.notification.cache.entity.toAlarmMetaPOs
import io.iamcyw.tower.commandhandling.CommandHandle
import java.time.LocalDateTime
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class NotificationCacheService {

    @CommandHandle
    fun handle(addAlarm: AddAlarm) {
        val alarmStrategyPO =
            AlarmStrategyPO.findById(addAlarm.strategy)
                ?: throw IllegalStateException("the strategy id(${addAlarm.strategy}) not find object")

        if (alarmStrategyPO.repeatTimeInterval != 0L) {
            //todo 检查是否需要在单位时间内忽略重复警报
        }

        val alarmPO = AlarmPO()
        alarmPO.strategy = alarmStrategyPO
        alarmPO.status = AlarmStatus.Wait
        alarmPO.source = AlarmSourcePO(addAlarm.source)
        //save payload headers
        alarmPO.alarmMeta = addAlarm.payload.headers.toAlarmMetaPOs()
        alarmPO.payload = addAlarm.payload.payload
        //默认级别为最小级
        alarmPO.currentLevel = alarmStrategyPO.defaultLevel().id!!
        alarmPO.notificationTime = LocalDateTime.now()
        alarmPO.persist()
    }
}