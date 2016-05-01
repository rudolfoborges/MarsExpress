package br.com.rb.marsexpress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import br.com.rb.marsexpress.security.CustomUserDetailsService;

@Configuration
public class SecurityConfiguration {

	private static final String RESOURCE_ID = "resources";

	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

		@Autowired
		private AuthenticationManager authenticationManager;
		@Autowired
		private CustomUserDetailsService userDetailsService;

		private TokenStore tokenStore = new InMemoryTokenStore();

		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.tokenStore(this.tokenStore).authenticationManager(this.authenticationManager)
					.userDetailsService(userDetailsService);
		}

		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.inMemory().withClient("acme").secret("acmesecret")
					.authorizedGrantTypes("authorization_code", "refresh_token", "password").authorities("ADMIN")
					.scopes("read", "write").resourceIds(RESOURCE_ID).refreshTokenValiditySeconds(600)
					.accessTokenValiditySeconds(30);
		}
	}

	@Configuration
	@EnableResourceServer
	protected class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		public void configure(ResourceServerSecurityConfigurer resources) {
			resources.resourceId(RESOURCE_ID);
		}

		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated();
		}
	}
	
	
	@Configuration
	@EnableWebSecurity
	public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Autowired
		private CustomUserDetailsService userDetailsService;
		
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			 auth.userDetailsService(userDetailsService);
		}

		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	}

}
