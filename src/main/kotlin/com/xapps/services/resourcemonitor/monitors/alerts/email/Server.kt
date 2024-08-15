package com.xapps.services.resourcemonitor.monitors.alerts.email

/**
 * Email server configuration
 *
 * @param host The host of the email server
 * @param port The port of the email server
 * @param username The username of the email server
 * @param password The password of the email server
 */
data class Server(
    val host: String,
    val port: Int,
    val username: String,
    val password: String
)
