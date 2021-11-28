package io.iamcyw.ams.job.strategy.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity

@Entity(name = "ALARM_SOURCE")
class AlarmSourcePO() : PanacheEntity() {
    constructor(id: Long) : this() {
        this.id = id
    }

    companion object : PanacheCompanion<AlarmSourcePO> {
        fun findByName(name: String) = find("name", name).singleResult()
    }

    lateinit var name: String

}