package com.xapps.services.resourcemonitor.monitors.alerts.sms

import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.xapps.services.resourcemonitor.monitors.alerts.AlertProvider
import com.xapps.services.resourcemonitor.utilities.logger
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component


/**
 * SMS alert provider
 * <p>
 * Responsible for sending SMS alerts
 *
 * @param configuration The SMS configuration
 */
@ConditionalOnProperty(
    prefix = "monitor.alerts.sms",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
@Component
data class SmsAlertProvider(
    private val configuration: SmsConfiguration
) : AlertProvider {

    init {
        Twilio.init(configuration.provider.sid, configuration.provider.token);
    }

    override fun notify(message: String) {
        configuration.phoneNumbers.forEach { phoneNumber ->
            val message = Message.creator(
                com.twilio.type.PhoneNumber(phoneNumber.number),
                com.twilio.type.PhoneNumber(configuration.provider.number),
                message
            ).create()
            logger().info("SMS sent to ${phoneNumber.number} with SID ${message.sid}")
        }
    }
}