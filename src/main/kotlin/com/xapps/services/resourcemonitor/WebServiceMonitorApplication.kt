package com.xapps.services.resourcemonitor

import com.xapps.services.resourcemonitor.monitors.alerts.email.EmailConfiguration
import com.xapps.services.resourcemonitor.monitors.alerts.sms.SmsConfiguration
import com.xapps.services.resourcemonitor.monitors.indicators.storages.StorageIndicatorConfiguration
import com.xapps.services.resourcemonitor.monitors.indicators.webservices.WebServiceIndicatorConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

/**
 * Web service monitor application
 */
@SpringBootApplication
@EnableConfigurationProperties(
    *arrayOf(
        WebServiceIndicatorConfiguration::class,
        StorageIndicatorConfiguration::class,
        EmailConfiguration::class,
        SmsConfiguration::class
    )
)
@EnableScheduling
class WebServiceMonitorApplication

fun main(args: Array<String>) {
    runApplication<WebServiceMonitorApplication>(*args)
}
