package com.xapps.services.resourcemonitor.monitors.alerts.email

import com.xapps.services.resourcemonitor.monitors.alerts.AlertProvider
import com.xapps.services.resourcemonitor.utilities.logger
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.stereotype.Component

/**
 * Email alert provider
 * <p>
 * Responsible for sending email alerts
 *
 * @param configuration The email configuration
 */
@ConditionalOnProperty(
    prefix = "monitor.alerts.email",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
@Component
data class EmailAlertProvider(
    private val configuration: EmailConfiguration
) : AlertProvider {

    private val mailSender: JavaMailSender by lazy {
        JavaMailSenderImpl().apply {
            host = configuration.server.host
            protocol = "smtp"
            port = configuration.server.port
            username = configuration.server.username
            password = configuration.server.password
            javaMailProperties["mail.transport.protocol"] = "smtp"
            javaMailProperties["mail.smtp.auth"] = true
            javaMailProperties["mail.smtp.starttls.enable"] = true
            javaMailProperties["mail.smtp.starttls.required"] = true
            javaMailProperties["mail.smtp.socketFactory.port"] = 465
            javaMailProperties["mail.smtp.debug"] = true
            javaMailProperties["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
            javaMailProperties["mail.smtp.socketFactory.fallback"] = true
        }
    }

    override fun notify(message: String) {
        configuration.recipients.forEach { recipient ->
            val mailMessage = SimpleMailMessage()
            mailMessage.from = configuration.template.from
            mailMessage.setTo(recipient.email)
            mailMessage.subject = configuration.template.subject
            mailMessage.text = message
            mailSender.send(mailMessage)
            logger().info("Email sent to ${recipient.email}")
        }
    }

}