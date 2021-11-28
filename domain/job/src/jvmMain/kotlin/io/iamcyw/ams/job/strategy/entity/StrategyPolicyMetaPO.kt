package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity(name = "ALARM_STRATEGY_POLICY_META")
class StrategyPolicyMetaPO : PanacheEntity() {
    companion object : PanacheCompanion<StrategyPolicyMetaPO> {
    }


    lateinit var scope: MessageScope

    lateinit var contrastKey: String

    lateinit var contrastValue: String

    lateinit var contrastType: PolicyContrastType

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var policy: StrategyPolicyPO
}

enum class MessageScope {
    Headers, Payload
}

enum class PolicyContrastType {
    Fixed, Express, Pass
}