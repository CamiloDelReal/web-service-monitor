package com.xapps.services.resourcemonitor.monitors.alerts

/**
 * Alert provider
 * <p>
 * Provides base interface for alert providers
 */
interface AlertProvider {

    /**
     * Notifies the alert
     */
    fun notify(message: String)

}