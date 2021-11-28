package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity(name = "ALARM_STRATEGY_LEVEL")
class StrategyLevelPO : PanacheEntity() {
    companion object : PanacheCompanion<StrategyLevelPO> {
        fun findWithStrategyAndLevel(level: Int, strategy: Long): StrategyLevelPO? {
            return find("LEVEL = ?1 and STRATEGY_ID = ?2", level, strategy).list().singleOrNull()
        }
    }

    /**
     * 当前级别
     *
     * 最低0
     */
    var level: Int = 0

    /**
     * 单位:分钟
     */
    var timeInterval: Long = 0

    lateinit var name: String

    @JoinColumn(name = "STRATEGY_ID", nullable = false)
    @ManyToOne
    lateinit var strategy: AlarmStrategyPO


    @OneToMany(fetch = FetchType.LAZY)
    lateinit var target: MutableSet<StrategyLevelTargetPO>


}