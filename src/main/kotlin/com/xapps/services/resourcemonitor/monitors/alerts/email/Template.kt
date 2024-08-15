package com.xapps.services.resourcemonitor.monitors.alerts.email

/**
 * Email template
 *
 * @param subject The subject of the email
 */
data class Template(
    val from: String,
    val subject: String
)
