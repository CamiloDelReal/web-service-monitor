package com.xapps.services.resourcemonitor.monitors.indicators

import org.springframework.boot.actuate.health.HealthIndicator

/**
 * Indicator provider
 * <p>
 * Provides health indicators for the targets defined in the configuration
 */
interface IndicatorProvider {

    /**
     * The name of the provider
     */
    fun name(): String

    /**
     * Provides the health indicators
     */
    fun provideHealthIndicators(): List<CustomHealthIndicator>

}