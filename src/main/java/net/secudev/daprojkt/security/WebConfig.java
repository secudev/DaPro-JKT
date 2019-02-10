package net.secudev.daprojkt.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	    //Gestion des urls statiques
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
        	//on peut aussi utiliser un dossier situé n'importe où sur le filesystem
        	//il vaut mieux définir cette valeur dans une variable de configuration
            registry.addResourceHandler("files/**").addResourceLocations("file:./files/");            
        }
}