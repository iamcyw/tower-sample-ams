package io.iamcyw.ams.notification.cache.entity

import io.iamcyw.ams.job.strategy.entity.AlarmSourcePO
import io.iamcyw.ams.job.strategy.entity.AlarmStrategyPO
import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import io.quarkus.panache.common.Sort
import kotlinx.serialization.json.*
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "ALARM")
class AlarmPO : PanacheEntity() {
    companion object : PanacheCompanion<AlarmPO> {

        /**
         * 可以立即发送的警报
         *
         * 假设发送需要的最大上限时间是5s，可以立即发送的则包括未来5s类的
         */
        fun findWaitImmediateAlarm() = find(
            "status = ?1 and notificationTime <= ?2",
            Sort.descending("createTime"),
            AlarmStatus.Wait,
            LocalDateTime.now().plusSeconds(5)
        )

        /**
         * 过了1分钟仍然被锁定的任务
         *
         * 意味着失败
         */
        fun findLockedAlarm() = find(
            "status = ?1 and notificationTime <= ?2",
            Sort.descending("createTime"),
            AlarmStatus.Process,
            LocalDateTime.now().plusSeconds(-60)
        )
    }

    lateinit var status: AlarmStatus

    @CreationTimestamp
    lateinit var createTime: LocalDateTime

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var source: AlarmSourcePO

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var strategy: AlarmStrategyPO

    var currentLevel: Long = 0

    lateinit var checkLevelTime: LocalDateTime

    @Lob
    @Basic(fetch = FetchType.LAZY)
    lateinit var payload: String

    lateinit var notificationTime: LocalDateTime

    lateinit var alarmMeta: MutableSet<AlarmMetaPO>

    fun payloadJson(): JsonElement = Json.parseToJsonElement(payload)

}

fun MutableMap<String, String>.toAlarmMetaPOs() = this.toList().map { AlarmMetaPO(it.first, it.second) }.toMutableSet()

fun MutableSet<AlarmMetaPO>.toKV() = this.associateBy({ alarmMetaPO -> alarmMetaPO.metaKey },
    { alarmMetaPO -> alarmMetaPO.metaValue })

enum class AlarmStatus {
    /**
     * 需要发送
     */
    Wait,

    /**
     * 正在被处理
     */
    Process,

    /**
     * 发送完成
     */
    Sent
}
