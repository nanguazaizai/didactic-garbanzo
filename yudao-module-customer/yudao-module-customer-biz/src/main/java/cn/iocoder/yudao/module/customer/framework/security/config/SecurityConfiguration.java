package cn.iocoder.yudao.module.customer.framework.security.config;

import cn.iocoder.yudao.framework.security.config.AuthorizeRequestsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * customer 模块的 Security 配置
 */
@Configuration(proxyBeanMethods = false, value = "customerSecurityConfiguration")
public class SecurityConfiguration {

    @Bean("customerAuthorizeRequestsCustomizer")
    public AuthorizeRequestsCustomizer authorizeRequestsCustomizer() {
        return new AuthorizeRequestsCustomizer() {

            @Override
            public void customize(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {
                // TODO 芋艿：这个每个项目都需要重复配置，得捉摸有没通用的方案
                // Swagger 接口文档
                registry.requestMatchers("/v3/api-docs/**").permitAll() // 元数据
                        .requestMatchers("/swagger-ui.html").permitAll(); // Swagger UI
                // Druid 监控
                registry.requestMatchers("/druid/**").permitAll();
                // Spring Boot Actuator 的安全配置
                registry.requestMatchers("/actuator").permitAll()
                        .requestMatchers("/actuator/**").permitAll();
            }

        };
    }

}
