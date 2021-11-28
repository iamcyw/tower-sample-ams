package io.iamcyw.ams.job.receive

import io.iamcyw.ams.job.AlarmMessage

data class ReceiveAlarm(
    /**
     * 警报源ID
     */
    val source: String,
    /**
     * 警报内容
     */
    val message: AlarmMessage,
)