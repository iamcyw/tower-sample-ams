package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.*

@Entity(name = "ALARM_STRATEGY_POLICY")
class StrategyPolicyPO : PanacheEntity() {

    companion object : PanacheCompanion<StrategyPolicyPO> {
        fun queryBySource(source: Long): List<StrategyPolicyPO> {
            return list("source_id", source)
        }
    }

    lateinit var name: String

    @OneToMany(fetch = FetchType.LAZY)
    lateinit var metadata: MutableSet<StrategyPolicyMetaPO>

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var source: AlarmSourcePO

    @ManyToMany(targetEntity = AlarmStrategyPO::class, fetch = FetchType.LAZY, mappedBy = "alarmPolicies")
    lateinit var strategy: List<AlarmStrategyPO>

}