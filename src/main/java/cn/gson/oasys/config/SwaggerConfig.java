package cn.gson.oasys.config;

import com.google.common.base.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * @description: Swagger配置文件
 * @author: xz
 */
@Configuration
@EnableSwagger2//开启Swagger2
public class SwaggerConfig {

    //注入配置文件中的项目名称
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 构建 swagger2 api 文档的详细信息函数
     * @return
     */
    private ApiInfo initApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("springSecurity测试项目 Platform API")//大标题
                .version( "1.0.0")//版本
                .description(initContextInfo())//描述
                .contact(new Contact("xz", "https://wwwxz.blog.csdn.net/", "123456@qq.com"))//作者信息
                .license("The System Server, Version 1.0")//网站链接显示文字
                .licenseUrl("https://wwwxz.blog.csdn.net/")//网站链接
                .build();

        return apiInfo;
    }

    private String initContextInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("REST API 设计在细节上有很多自己独特的需要注意的技巧，并且对开发人员在构架设计能力上比传统 API 有着更高的要求。")
                .append("<br/>")
                .append("本文通过翔实的叙述和一系列的范例，从整体结构，到局部细节，分析和解读了为了提高易用性和高效性，REST API 设计应该注意哪些问题以及如何解决这些问题。");

        return sb.toString();
    }

    /**
     * swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
     * @return
     */
    @Bean
    public Docket restfulApi() {
        System.out.println("http://localhost:8001" + contextPath + "/swagger-ui.html");
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(initApiInfo())
                .groupName("RestfulApi")
                //.genericModelSubstitutes(DeferredResult.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .pathMapping(contextPath) // base，最终调用接口后会和paths拼接在一起
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //暴露接口地址的包路径（即此包下的类，才生成接口文档）
                .apis(RequestHandlerSelectors.basePackage("com.xz.springsecuritydemo.modules.sys.controller"))
                .paths(doFilteringRules())//自定义的过滤规则
                .build();
    }

    /**
     * 设置过滤规则
     * 这里的过滤规则支持正则匹配
     * @return
     */
    private Predicate<String> doFilteringRules() {
        return or(
                regex("/testUser.*"),
                regex("/hello.*")
        );
    }
}
