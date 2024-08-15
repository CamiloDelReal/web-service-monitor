package com.xapps.services.resourcemonitor.monitors.indicators.storages

import org.springframework.util.unit.DataSize
import java.io.File

/**
 * Storage target to monitor
 *
 * @param tag The tag of the target
 * @param path The path of the target
 * @param threshold The threshold of the target
 */
data class Storage(
    val tag: String,
    val path: File,
    val threshold: DataSize
)
