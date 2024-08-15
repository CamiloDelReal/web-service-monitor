package com.xapps.services.resourcemonitor.monitors.indicators.storages

import com.xapps.services.resourcemonitor.monitors.indicators.CustomHealthIndicator
import com.xapps.services.resourcemonitor.monitors.indicators.IndicatorProvider
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

/**
 * Storage indicator provider
 * <p>
 * Provides health indicators for monitor the storage defined in the configuration
 *
 * @param configuration The storage configuration
 */
@ConditionalOnProperty(
    prefix = "monitor.targets.storages",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
@Component
data class StorageIndicatorProvider(
    val configuration: StorageIndicatorConfiguration
) : IndicatorProvider {

    companion object {
        private const val NAME = "storages"
    }


    override fun name(): String = NAME

    override fun provideHealthIndicators(): List<CustomHealthIndicator> {
        return configuration.sources.map { StorageHealthIndicator(it) }
    }

}