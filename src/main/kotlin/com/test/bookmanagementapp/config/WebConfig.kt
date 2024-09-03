package com.test.bookmanagementapp.config

import com.test.bookmanagementapp.interceptor.RequestLoggingInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(private val requestLoggingInterceptor: RequestLoggingInterceptor) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(requestLoggingInterceptor)
    }
}
