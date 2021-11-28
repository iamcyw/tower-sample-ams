package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne


@Entity(name = "ALARM_STRATEGY_LEVEL_TARGET")
class StrategyLevelTargetPO : PanacheEntity() {

    companion object : PanacheCompanion<StrategyLevelTargetPO> {
    }

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID", nullable = false)
    lateinit var strategyLevel: StrategyLevelPO

    lateinit var type: TargetType

    lateinit var vale: String

    enum class TargetType {
        User, UserGroup, Address
    }
}

