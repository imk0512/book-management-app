package com.test.bookmanagementapp.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.slf4j.LoggerFactory

@Component
class RequestLoggingInterceptor : HandlerInterceptor {

    private val logger = LoggerFactory.getLogger(RequestLoggingInterceptor::class.java)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        // リクエストの情報をログに記録
        logger.info("Request URL: ${request.requestURL}")
        logger.info("HTTP Method: ${request.method}")
        logger.info("Client IP: ${request.remoteAddr}")
        logger.info("Headers: ${request.headerNames.toList().joinToString(", ")}")
        return true
    }

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        if (ex != null) {
            // 例外発生時のログ記録
            logger.error("Request completed with error: ${ex.message}", ex)
        } else {
            logger.info("Request completed successfully with status: ${response.status}")
        }
    }
}
