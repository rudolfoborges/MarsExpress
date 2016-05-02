package br.com.rb.marsexpress.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

	private final TokenStore tokenStore;
	private @Autowired AuthenticationManager authenticationManager;
	private @Autowired CustomUserDetailsService userDetailsService;
	
	public OAuth2Configuration(){
		this.tokenStore = new InMemoryTokenStore();
	}

	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(this.tokenStore).authenticationManager(this.authenticationManager)
				.userDetailsService(userDetailsService);
	}

	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("acme").secret("acmesecret")
				.authorizedGrantTypes("authorization_code", "refresh_token", "password").authorities("ADMIN")
				.scopes("read", "write").resourceIds(ResourceServerConfiguration.RESOURCE_ID).refreshTokenValiditySeconds(600)
				.accessTokenValiditySeconds(30);
	}
	
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.allowFormAuthenticationForClients();
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setTokenStore(tokenStore);
		return tokenServices;
	}
}
