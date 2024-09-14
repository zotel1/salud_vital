package com.demo.salud_vital.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfigurations {

  //  @Value("${AUTH0_AUDIENCE")
//    private String audience;

//    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  //  private String issuer;

   // @Value("${CORS_ALLOWED_ORIGINS}")
//   @Value("http://localhost:5173")
//    private String corsAllowedOrigins;

  //  @Value("${spring.websecurity.debug:false}")
//    boolean webSecurityDebug;

  //  @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  //      http
    //            .csrf((csrf) -> csrf.disable())
      //          .cors(withDefaults()) // por defecto spring va a buscar un bean con el nombre "corsConfigurationSource"
        //        .authorizeHttpRequests(authorizeRequests ->
          //              authorizeRequests
                               // .requestMatchers("/api/public/**").permitAll()
                               // .requestMatchers("/login").permitAll()
                               // .requestMatchers("/api/admin/**").hasAuthority("administrador")
                               // .requestMatchers("/api/client/**").hasAuthority("cliente")
            //                    .requestMatchers("/**").permitAll()
              //                   .requestMatchers("/login").permitAll()
                //                 .requestMatchers("/consultas/**").permitAll()
                  //               .requestMatchers("/medicos/**").permitAll()
                    //            .requestMatchers("/pacientes/**").permitAll()
                      //          .anyRequest().authenticated()
//                        )
  //              .oauth2ResourceServer(oAuth2ResourceServer ->
    //                    oAuth2ResourceServer
      //                          .jwt(jwt ->
        //                                jwt
          //                                      .decoder(jwtDecoder())
            //                                    .jwtAuthenticationConverter(jwtAuthenticationConverter())
              //                  )
//                );
  //      return http.build();
//    }

  //  @Bean
//    CorsConfigurationSource corsConfigurationSource() {
  //      CorsConfiguration configuration = new CorsConfiguration();
    //    configuration.setAllowedOrigins(Arrays.asList(corsAllowedOrigins));
      //  configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PATCH", "PUT", "DELETE", "OPTIONS", "HEAD"));
//        configuration.setAllowCredentials(true);
  //      configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
    //    configuration.setExposedHeaders(Arrays.asList("X-Get-Header"));
      //  configuration.setMaxAge(3600L);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
  //      source.registerCorsConfiguration("/**", configuration);
    //    return source;
//    }

  //  @Bean
//    JwtDecoder jwtDecoder() {
  //      NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);

    //    OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
      //  OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
//        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

  //      jwtDecoder.setJwtValidator(withAudience);
//
  //      return jwtDecoder;
 //   }

//    @Bean
  //  JwtAuthenticationConverter jwtAuthenticationConverter() {
    //    JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
      //  converter.setAuthoritiesClaimName("https://saludvital/roles");
 //       converter.setAuthorityPrefix("");

   //     JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
     //   jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
 //       return jwtConverter;
   // }

   // @Autowired
   // private SecurityFilter securityFilter;

    //@Bean
   // public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
     //   return httpSecurity.csrf().disable().sessionManagement()
       //         .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Le indicamos a Spring el tipo de sesion
         //       .and().authorizeRequests()
           //     .requestMatchers(HttpMethod.POST, "/login").permitAll()
             //   .requestMatchers("/swagger-ui.html", "/v3/api-docs/**","/swagger-ui/**").permitAll()
             //   .anyRequest()
               // .authenticated()
              //  .and()
              //  .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
              //  .build();
   // }

 //   @Bean
   // public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
     //       throws Exception {
 //       return authenticationConfiguration.getAuthenticationManager();
//    }

  //  @Bean
 //   public PasswordEncoder passwordEncoder() {
   //     return new BCryptPasswordEncoder();
 //   }

//}


@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Esto permite usar anotaciones de seguridad a nivel de método, si lo necesitas.
public class SecurityConfigurations {

    @Value("${AUTH0_AUDIENCE}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Desactiva CSRF si estás usando una API sin cookies
                .cors(withDefaults()) // Aplica la configuración CORS global
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        // Deja las rutas públicas si lo necesitas, por ejemplo, la página de inicio de sesión
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/public/**").permitAll() // Rutas que no requieren autenticación

                        // Protege las rutas que requieren autenticación
                        .requestMatchers("/consultas/**").authenticated()
                        .requestMatchers("/medicos/**").authenticated()
                        .requestMatchers("/pacientes/**").authenticated()

                        .anyRequest().authenticated() // Requiere autenticación para cualquier otra ruta
                )
                .oauth2ResourceServer(oAuth2ResourceServer ->
                        oAuth2ResourceServer
                                .jwt(jwt -> jwt
                                        .decoder(jwtDecoder()) // Decodifica el token JWT
                                        .jwtAuthenticationConverter(jwtAuthenticationConverter()) // Convierte el JWT a una autenticación válida
                                )
                );
        return http.build();
    }

    // Configuración CORS (verifica que allowedOrigins esté correctamente configurado)
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Asegúrate de que esta URL sea la de tu frontend
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Si estás enviando cookies o credenciales
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Decodificación del JWT utilizando el issuer de Auth0
    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer);
        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);
        jwtDecoder.setJwtValidator(withAudience);
        return jwtDecoder;
    }

    // Conversión de las autoridades desde el token JWT
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
        converter.setAuthoritiesClaimName("https://saludvital/roles"); // Cambia esta claim según cómo Auth0 devuelve roles en tu token JWT
        converter.setAuthorityPrefix(""); // No agregar prefijos como "ROLE_"

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
        return jwtConverter;
    }
}
