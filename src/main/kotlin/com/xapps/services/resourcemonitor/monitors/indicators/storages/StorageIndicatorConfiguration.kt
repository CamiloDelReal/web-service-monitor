package com.xapps.services.resourcemonitor.monitors.indicators.storages

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Storage configuration
 *
 * @param enabled Whether the storage indicator is enabled
 * @param sources The list of storage sources to monitor
 */
@ConfigurationProperties(prefix = "monitor.targets.storages")
data class StorageIndicatorConfiguration(
    val enabled: Boolean = true,
    var sources: List<Storage> = emptyList()
) {
}