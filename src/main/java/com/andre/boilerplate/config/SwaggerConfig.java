package com.andre.boilerplate.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String ACCESS_TOKEN = "JWT";
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String DEFAULT_INCLUDE_PATTERN = "/.*";

	@Value("${security.client-id}")
	private String CLIENT_ID;

	@Value("${security.client-secret}")
	private String CLIENT_SECRET;

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.andre.boilerplate"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(this.apiInfo())
		        .securitySchemes(Arrays.asList(securityScheme()))
		        .securityContexts(Arrays.asList(securityContext()));

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Project André - Spring Boilerplate")
				.description("API Rest with Oauth2 autoconfigure")
				.contact(
						new Contact("André Carlos", "https://github.com/andresinho20049", "andresinho200498@gmail.com"))
				.license("MIT").version("1.0.0").build();
	}

    private ApiKey apiKey() {
        return new ApiKey(ACCESS_TOKEN, AUTHORIZATION_HEADER, "header");
    }

	@Bean
	public SecurityConfiguration security() {
	    return SecurityConfigurationBuilder.builder()
	        .clientId(CLIENT_ID)
	        .clientSecret(CLIENT_SECRET)
	        .scopeSeparator("")
	        .appName("Boilerplate - Spring Oauth")
	        .realm("restservice")
	        .useBasicAuthenticationWithAccessCodeGrant(true)
	        .build();
	}
	
	private SecurityScheme securityScheme() {
		
		GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("/api/oauth/token");

	    SecurityScheme oauth = new OAuthBuilder()
	    	.name(ACCESS_TOKEN)
	        .grantTypes(Arrays.asList(grantType))
	        .scopes(Arrays.asList(scopes()))
	        .build();
	    
	    return oauth;
	}
	
	private AuthorizationScope[] scopes() {
	    AuthorizationScope[] scopes = { 
	      new AuthorizationScope("all", "accessEverything") };
	    return scopes;
	}
	
	private SecurityContext securityContext() {
	    return SecurityContext.builder()
	      .securityReferences(defaultAuth())
	      .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
	      .build();
	}
	
	private List<SecurityReference> defaultAuth() {
        return Lists.newArrayList(
            new SecurityReference(ACCESS_TOKEN, scopes()));
    }

	

}