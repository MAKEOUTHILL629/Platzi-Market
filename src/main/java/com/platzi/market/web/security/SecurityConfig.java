package com.platzi.market.web.security;

import com.platzi.market.domain.service.PlatziUserDetailsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity//Se le dice a spring que vamos a tener la configuracion de la seguridad
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PlatziUserDetailsServer platziUserDetailsServer;//Le enviamos el usuario que se creo con nombre y contrasenia

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(platziUserDetailsServer);
    }
}
