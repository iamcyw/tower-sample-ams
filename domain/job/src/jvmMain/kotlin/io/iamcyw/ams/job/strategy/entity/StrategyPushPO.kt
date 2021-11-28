package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity(name = "ALARM_STRATEGY_PUSH")
class StrategyPushPO : PanacheEntity() {

    companion object : PanacheCompanion<StrategyPushPO> {

    }

    /**
     * 消息主题，可以包含表达式
     */
    lateinit var subject: String

    lateinit var type: String

    /**
     * 正文模板，可以包含表达式
     */
    lateinit var template: String

    @OneToMany(fetch = FetchType.LAZY)
    lateinit var metaPO: MutableSet<StrategyPushMetaPO>

}