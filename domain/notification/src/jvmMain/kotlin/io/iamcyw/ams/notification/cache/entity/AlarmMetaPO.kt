package io.iamcyw.ams.notification.cache.entity

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.ManyToOne

@Entity(name = "ALARM_META")
class AlarmMetaPO constructor() : PanacheEntity() {
    companion object : PanacheCompanion<AlarmMetaPO> {

    }

    constructor(metaKey: String, metaValue: String) : this() {
        this.metaKey = metaKey
        this.metaValue = metaValue
    }

    lateinit var metaKey: String

    lateinit var metaValue: String

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var alarm: AlarmPO


}