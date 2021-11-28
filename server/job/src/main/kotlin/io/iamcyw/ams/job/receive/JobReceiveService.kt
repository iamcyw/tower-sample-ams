package io.iamcyw.ams.job.receive

import io.iamcyw.ams.job.predicate.QueryAllowStrategy
import io.iamcyw.ams.job.strategy.entity.AlarmSourcePO
import io.iamcyw.ams.notification.cache.AddAlarm
import io.iamcyw.tower.commandhandling.CommandHandle
import io.iamcyw.tower.commandhandling.gateway.CommandGateway
import io.iamcyw.tower.queryhandling.gateway.QueryGateway
import io.quarkus.runtime.annotations.RegisterForReflection
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.inject.Named
import javax.transaction.Transactional

@ApplicationScoped
@Named("alarm-income")
@RegisterForReflection
@Transactional
class JobReceiveService @Inject constructor(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    @CommandHandle
    fun receive(receiveAlarm: ReceiveAlarm) {
        val alarmSourcePO = AlarmSourcePO.findByName(receiveAlarm.source)
        val strategies = queryGateway.query<List<Long>>(QueryAllowStrategy(alarmSourcePO.id!!, receiveAlarm.message))
        strategies.map {
            AddAlarm(it, alarmSourcePO.id!!, receiveAlarm.message)
        }.forEach {
            commandGateway.send(it)
        }
    }
}