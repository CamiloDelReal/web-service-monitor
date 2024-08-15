package com.xapps.services.resourcemonitor.monitors

import com.xapps.services.resourcemonitor.monitors.alerts.AlertProvider
import com.xapps.services.resourcemonitor.monitors.indicators.IndicatorProvider
import com.xapps.services.resourcemonitor.utilities.logger
import org.springframework.boot.actuate.health.HealthContributorRegistry
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.boot.actuate.health.Status
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

/**
 * Monitor service
 * <p>
 * Provides monitoring of the targets for `DOWN` notifications
 *
 * @param registry The main health contributor registry of the service. This is provided by the framework
 */
@Service
data class MonitorService(
    private val registry: HealthContributorRegistry,
    private val indicatorProviders: List<IndicatorProvider>,
    private val alertProviders: List<AlertProvider>
) {

    init {
        indicatorProviders.map { indicatorProvider ->
            logger().info("Provider detected: ${indicatorProvider.name()}")
            indicatorProvider.provideHealthIndicators().forEach { healthIndicator ->
                logger().info("Registering health indicator: ${healthIndicator.tag()}")
                registry.registerContributor(healthIndicator.tag(), healthIndicator)
            }
        }
    }

    @Scheduled(cron = "\${monitor.schedule}")
    fun monitor() {
        logger().info("Monitoring health of targets")
        registry.forEach {
            logger().info("Checking health of target: ${it.name}: ${(it.contributor as HealthIndicator).health().status}")
        }
        val healths = registry.map { (it.contributor as HealthIndicator).health() }
        if (healths.any { it.status != Status.UP }) {
            alertProviders.forEach { it.notify("There is a service DOWN") }
        }
    }


}