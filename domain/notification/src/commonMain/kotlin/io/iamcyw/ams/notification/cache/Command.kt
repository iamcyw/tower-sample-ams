package io.iamcyw.ams.notification.cache

import io.iamcyw.ams.job.AlarmMessage

data class AddAlarm(val strategy: Long, val source: Long, val payload: AlarmMessage)

/**
 * 处理alarm通知
 */
data class PushAlarm(val alarm: Long)

/**
 * 重试alarm通知
 */
data class RetryAlarm(val alarm: Long)

/**
 * 实际通知的处理器
 */
data class NotificationHandler(
    val pushType: String,
    val push: Long,
    val alarm: Long,
    val level: Long
)