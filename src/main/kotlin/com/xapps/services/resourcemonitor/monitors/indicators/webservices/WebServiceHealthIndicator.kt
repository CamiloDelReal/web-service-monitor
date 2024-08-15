package com.xapps.services.resourcemonitor.monitors.indicators.webservices

import com.xapps.services.resourcemonitor.monitors.indicators.CustomHealthIndicator
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.Status
import org.springframework.web.client.RestClient
import org.springframework.web.client.support.RestClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

/**
 * Web service health indicator
 *
 * @param target The target to monitor
 */
data class WebServiceHealthIndicator(
    val target: WebService
) : CustomHealthIndicator {

    private val client: HealthClient by lazy {
        val restClient = RestClient.builder()
            .baseUrl(target.url)
            .build()

        val restClientAdapter = RestClientAdapter.create(restClient)

        val restClientHttpFactory = HttpServiceProxyFactory.builderFor(restClientAdapter)
            .build()

        restClientHttpFactory.createClient(HealthClient::class.java)
    }

    override fun tag(): String = target.tag

    /**
     * Check the health of the target web service
     */
    override fun health(): Health =
        Health.Builder().apply {
            withDetail("tag", target.tag)
            withDetail("url", target.url)
            try {
                if (client.health().statusCode.is2xxSuccessful) {
                    status(Status.UP)
                } else {
                    status(Status.DOWN)
                }
            } catch (e: Exception) {
                status(Status.DOWN)
                withDetail("error", e.message)
            }
        }.build()

}
