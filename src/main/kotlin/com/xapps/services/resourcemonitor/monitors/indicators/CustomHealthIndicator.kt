package com.xapps.services.resourcemonitor.monitors.indicators

import org.springframework.boot.actuate.health.HealthIndicator

/**
 * Custom health indicator
 * <p>
 * Provides additional information about the indicator
 */
interface CustomHealthIndicator: HealthIndicator {

    /**
     * The tag of the indicator
     */
    fun tag(): String

}