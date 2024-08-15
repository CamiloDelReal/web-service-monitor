package com.xapps.services.resourcemonitor.monitors.alerts.email

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Email configuration
 *
 * @param enabled Whether the email alerts are enabled
 * @param template The email template
 * @param server The email server configuration
 * @param recipients The recipients of the email
 */
@ConfigurationProperties(prefix = "monitor.alerts.email")
data class EmailConfiguration(
    val enabled: Boolean = false,
    val template: Template,
    val server: Server,
    val recipients: List<Recipient>
) {


}
