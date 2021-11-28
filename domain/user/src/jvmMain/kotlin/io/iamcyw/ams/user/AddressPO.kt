package io.iamcyw.ams.user

import io.quarkus.hibernate.orm.panache.kotlin.PanacheCompanion
import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntity

class AddressPO : PanacheEntity() {

    companion object : PanacheCompanion<AddressPO> {

    }

    var user: Long = 0

    /**
     * 通讯名
     * 为空时默认使用用户名
     */
    lateinit var name: String

    /**
     * 电邮
     */
    lateinit var email: String

}

fun List<AddressPO>.emailStr() = joinToString(";") { "<${it.name}> ${it.email}" }