package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "ALARM_STRATEGY_PUSH_META")
class StrategyPushMetaPO : PanacheEntity() {

    lateinit var metaKey: String

    lateinit var metaValue: String

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PUSH_ID")
    lateinit var push: StrategyPushPO
}