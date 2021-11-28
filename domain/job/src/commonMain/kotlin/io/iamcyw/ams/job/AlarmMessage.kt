package io.iamcyw.ams.job

/**
 * 外部进入的消息，
 * 进入内部payload必须是json格式
 */
data class AlarmMessage(val headers: MutableMap<String, String>, val payload: String)