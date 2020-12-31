package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // 검색할 루트 폴더 지정가능 , defalut값 componentscan 현재 자바클래스 패키지부터 시작
        basePackages = "hello.core",
    basePackageClasses = AutoAppConfig.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)@ComponentScan(
        // 검색할 루트 폴더 지정가능 , defalut값 componentscan 현재 자바클래스 패키지부터 시작
        basePackages = "hello.core",
    basePackageClasses = AutoAppConfig.class,
    excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = Configuration.class)
)
public class AutoAppConfig {
}
