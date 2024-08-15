package com.xapps.services.resourcemonitor.monitors.alerts.sms

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * SMS configuration
 *
 * @param enabled Whether the SMS alerts are enabled
 * @param phoneNumbers The phone numbers to send the SMS alerts to
 */
@ConfigurationProperties(prefix = "monitor.alerts.sms")
data class SmsConfiguration(
    val enabled: Boolean = false,
    val provider: Provider,
    val phoneNumbers: List<PhoneNumber>
)