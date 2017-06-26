package com.shawn.security;

import com.shawn.service.UserService;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * @author Xiaoyue Xiao
 */
@Configuration
@EnableAuthorizationServer
@MapperScan("com.shawn.repository.mybatis")
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private Environment env;
    
    @Autowired
	private DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
        		.jdbc(dataSource)
                .withClient("JunBook")
                    .authorizedGrantTypes("password", "refresh_token")
                    .scopes("read", "write")
                    .secret("JunSecret");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(tokenStore())
            .authenticationManager(authenticationManager)
            .authorizationCodeServices(authorizationCodeServices())
            .userDetailsService(userService);
    }

    @Bean("tokenStore")
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}
    
    @Bean
	protected AuthorizationCodeServices authorizationCodeServices() {
		return new JdbcAuthorizationCodeServices(dataSource);
	}

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true); // support refresh token
        tokenServices.setTokenStore(tokenStore()); // use in-memory token store
        return tokenServices;
    }

//    @Bean(name = "dataSource")
//    public DataSource dataSource() {
//        DriverManagerDataSource ds = new DriverManagerDataSource(
//                "jdbc:mysql://localhost:3306/test?verifyServerCertificate=false&useSSL=true", "root", "tollan123");
//        ds.setDriverClassName("com.mysql.jdbc.Driver");
//        return ds;
//    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
      SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
      sessionFactory.setDataSource(dataSource);
      return sessionFactory.getObject();
    }
}
