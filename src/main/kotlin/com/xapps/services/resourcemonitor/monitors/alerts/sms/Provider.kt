package com.xapps.services.resourcemonitor.monitors.alerts.sms

/**
 * SMS provider
 *
 * @param sid The Twilio account SID
 * @param token The Twilio account token
 * @param number The Twilio phone number
 */
data class Provider(
    val sid: String,
    val token: String,
    val number: String
)
