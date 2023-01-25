package fenoreste.inspei.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import fenoreste.inspei.entity.Tabla;
import fenoreste.inspei.entity.TablaPK;
import fenoreste.inspei.service.ITablaService;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig 	extends WebSecurityConfigurerAdapter {
		
	    @Autowired
	    private ITablaService tablaService;

	    @Autowired
	    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	    	TablaPK tablaPK = new TablaPK("spei_entrada","usuario_ws");
	    	Tabla tabla = tablaService.buscarPorId(tablaPK);
	        auth.inMemoryAuthentication().withUser(tabla.getDato1()).password(tabla.getDato2()).roles("USER");
	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.csrf().disable().authorizeRequests()
	        .antMatchers("/**").hasRole("USER")
	        .and().httpBasic();
	    }

	    @SuppressWarnings("deprecation")
	    @Bean
	    public static NoOpPasswordEncoder passwordEncoder() {
	        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	    }
}
