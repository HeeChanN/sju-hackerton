package hackerton.saver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 전역 CORS 설정
        registry.addMapping("/**")                // 모든 경로에 대하여
                .allowedOrigins("*") // 허용할 Origin
                .allowedMethods("*")              // 허용할 HTTP Method (GET, POST, PUT, DELETE 등)
                .allowedHeaders("*")              // 허용할 Header
                .allowCredentials(false)           // 인증정보(쿠키 등) 포함 여부
                .maxAge(3600);                    // preflight 요청 캐싱 시간(초 단위)
    }
}

