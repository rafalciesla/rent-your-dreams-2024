package pl.ciesla.ryd

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.stereotype.Component
import pl.ciesla.ryd.dto.NotificationDTO

//TODO: remove
@Component
class SendExample(
    val streamBridge: StreamBridge
) {
    fun send(notification: NotificationDTO) {
        streamBridge.send("sendCommunication-out-0", notification)
    }
}