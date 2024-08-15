package com.xapps.services.resourcemonitor.monitors.indicators.webservices

/**
 * Web service target to monitor
 *
 * @param tag The tag of the target
 * @param url The url of the target
 */
data class WebService(
    val tag: String,
    val url: String
)