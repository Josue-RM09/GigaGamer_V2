package info.gigagamer.config;
import info.gigagamer.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/login", "/register", "/css/**", "/js/**", "/img/**").permitAll()  // Permitir acceso a recursos estáticos
                .anyRequest().authenticated()
                .and()
                .csrf().disable() // Deshabilita CSRF para permitir métodos DELETE (en entornos de desarrollo, no recomendable para producción sin protección adicional)
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/bienvenido", true)
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}