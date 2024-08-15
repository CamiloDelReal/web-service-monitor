package com.xapps.services.resourcemonitor.monitors.indicators.webservices

import org.springframework.http.ResponseEntity
import org.springframework.web.service.annotation.GetExchange
import org.springframework.web.service.annotation.HttpExchange

/**
 * Declarative rest client to check the health status of an url
 */
@HttpExchange
interface HealthClient {

    /**
     * Check the health of the service
     */
    @GetExchange
    fun health(): ResponseEntity<Void>

}