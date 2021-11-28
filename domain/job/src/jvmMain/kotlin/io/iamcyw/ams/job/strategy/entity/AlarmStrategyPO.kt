package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

/**
 * alarm事件策略
 */
@Entity(name = "ALARM_STRATEGY")
class AlarmStrategyPO() : PanacheEntity() {
    companion object : PanacheCompanion<AlarmStrategyPO>;

    lateinit var name: String

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var source: AlarmSourcePO

    @ManyToMany(targetEntity = StrategyPolicyPO::class, fetch = FetchType.LAZY)
    @JoinTable(
        name = "ALARM_STRATEGY_POLICY_LINK",
        inverseJoinColumns = [JoinColumn(name = "POLICY_ID", referencedColumnName = "ID")],
        joinColumns = [JoinColumn(name = "STRATEGY_ID", referencedColumnName = "ID")]
    )
    lateinit var alarmPolicies: List<StrategyPolicyPO>

    @OneToMany(fetch = FetchType.LAZY)
    lateinit var level: MutableSet<StrategyLevelPO>

    /**
     * 单位:分钟
     *
     * 如果具体级别的间隔时间没有设置才使用该值
     */
    var levelTimeInterval: Long = 0

    /**
     * 单位:秒
     *
     * 默认警报可重复
     *
     * 指定距离上次警报间隔多长才能再次发送
     */
    var repeatTimeInterval: Long = 0

    @OneToMany(fetch = FetchType.LAZY)
    lateinit var push: MutableSet<StrategyPushPO>

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var solve: StrategySolvePO

    /**
     * 当下strategy中的指定级别
     */
    fun level(level: Int): StrategyLevelPO? {
        return StrategyLevelPO.findWithStrategyAndLevel(level, id!!)
    }

    fun defaultLevel(): StrategyLevelPO {
        return StrategyLevelPO.findWithStrategyAndLevel(0, id!!)
            ?: throw IllegalStateException("The Strategy(${id}) no find default Level")
    }

}