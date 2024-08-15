package com.xapps.services.resourcemonitor.monitors.indicators.webservices

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Web service configuration
 *
 * @param enabled Whether the web service indicator is enabled
 * @param sources The list of web service sources to monitor
 */
@ConfigurationProperties(prefix = "monitor.targets.web-services")
data class WebServiceIndicatorConfiguration(
    val enabled: Boolean = true,
    var sources: List<WebService> = emptyList()
)
