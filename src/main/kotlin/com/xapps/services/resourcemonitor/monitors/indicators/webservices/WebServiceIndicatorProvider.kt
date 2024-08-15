package com.xapps.services.resourcemonitor.monitors.indicators.webservices

import com.xapps.services.resourcemonitor.monitors.indicators.CustomHealthIndicator
import com.xapps.services.resourcemonitor.monitors.indicators.IndicatorProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

/**
 * Web service indicator provider
 * <p>
 * Provides health indicators for monitor web services defined in the configuration
 *
 * @param configuration The web service configuration
 */
@ConditionalOnProperty(
    prefix = "monitor.targets.web-services",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
@Component
data class WebServiceIndicatorProvider(
    val configuration: WebServiceIndicatorConfiguration
) : IndicatorProvider {

    companion object {
        private const val NAME = "webServices"
    }

    override fun name(): String = NAME

    override fun provideHealthIndicators(): List<CustomHealthIndicator> =
        configuration.sources.map { WebServiceHealthIndicator(it) }

}