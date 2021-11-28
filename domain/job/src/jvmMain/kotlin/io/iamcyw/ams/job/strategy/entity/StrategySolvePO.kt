package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

/**
 * alarm ack 策略
 */
@Entity(name = "ALARM_STRATEGY_SOLVE")
class StrategySolvePO : PanacheEntity() {
}