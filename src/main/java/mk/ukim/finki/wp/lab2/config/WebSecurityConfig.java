package mk.ukim.finki.wp.lab2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //ovde pravime bazichna konfiguracija za SpringSecurity kako na nas shto ni odgovara
        http.csrf().disable()
                .authorizeRequests() //koi stranici na koi url treba da bidat dozvoleni
                .antMatchers("/","/login")
                .permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN") //samo onie koi imaat uloga administrator
                .anyRequest()
                .authenticated() //za site drugi korisnikot treba da bide najaven
                .and()
                .formLogin() //da ja iskonfigurirame login formata
                .loginPage("/login")
                .permitAll() //pri nekoja si greshka
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/courses") //pri uspeshna najava
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/login") //pri useshna odjava da go navratime korisnikot pak na logIn
                    .and()
                    .exceptionHandling().accessDeniedPage("/deniedPage");
    }
    //za da ne go koristime predifiniraniot password i user, definirame nash harkodiran

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            //kje koristime inMemoryProvider so hardkodirani user i admin
            auth.inMemoryAuthentication()
                    //da definirame koj se useri kje postojat vo nashiot
                    .withUser("klementina.gjorigeva") //ne smee da se koristi plain text password
                    .password(passwordEncoder.encode("kg"))
                    .authorities("ROLE_USER") //kazuvame kakov authorities kje ima, ovoj user da ima samo rolja user
                    .and()
                    .withUser("admin")
                    .password(passwordEncoder.encode("admin"))
                    .authorities("ROLE_ADMIN");
    }

}
