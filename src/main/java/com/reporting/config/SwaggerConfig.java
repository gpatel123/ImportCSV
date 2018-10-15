package com.reporting.config;

import static com.google.common.base.Predicates.and;
import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	
	
	private Predicate<String> apiPaths() {
		return regex("/leads.*");
	}
	/*List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[3];
        authorizationScopes[0] = new AuthorizationScope(Authority.SYS_ADMIN.name(), "System administrator");
        authorizationScopes[1] = new AuthorizationScope(Authority.TENANT_ADMIN.name(), "Tenant administrator");
        authorizationScopes[2] = new AuthorizationScope(Authority.CUSTOMER_USER.name(), "Customer");
        return newArrayList(
                new SecurityReference("X-Authorization", authorizationScopes));
  }*/
	
	private Predicate<String> securityPaths() {
        return and(
                 regex("/api.*"),
                 not(regex("/api/noauth.*"))
        );
   }
	
	/*private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("DX-Access REST API").description(
				"For more information about the application please visit <a href='https://html.dataexchange.io/newhtml/dx-access.html'>DX-Access App</a>.")
				.contact(new Contact("DX-Things team", "http://dev.dataexchange.io", "developer@dataexchange.io"))
				.license("DX-Things License Version 2.0")
				.licenseUrl("https://github.com/DataExchange/DXthings/blob/master/LICENSE").version("1.4.0").build();
	}*/
}