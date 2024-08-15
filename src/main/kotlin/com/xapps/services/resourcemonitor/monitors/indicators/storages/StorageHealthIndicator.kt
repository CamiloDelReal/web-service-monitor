package com.xapps.services.resourcemonitor.monitors.indicators.storages

import com.xapps.services.resourcemonitor.monitors.indicators.CustomHealthIndicator
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.Status
import org.springframework.util.unit.DataSize

/**
 * Storage target to monitor
 *
 * @param target The target to monitor
 */
data class StorageHealthIndicator(
    val target: Storage
) : CustomHealthIndicator {

    override fun tag(): String = target.tag


    /**
     * Check the health of the target storage
     */
    override fun health(): Health =
        Health.Builder().apply {
            val diskFreeInBytes: Long = target.path.getUsableSpace()
            if (diskFreeInBytes >= target.threshold.toBytes()) {
                status(Status.UP)
            } else {
                status(Status.DOWN)
            }
            withDetail("total", "${DataSize.ofBytes(target.path.getTotalSpace()).toGigabytes()}GB")
            withDetail("free", "${DataSize.ofBytes(diskFreeInBytes).toGigabytes()}GB")
            withDetail("threshold", "${DataSize.ofBytes(target.threshold.toBytes()).toGigabytes()}GB")
            withDetail("path", target.path.absolutePath)
            withDetail("exists", target.path.exists())
        }.build()


}