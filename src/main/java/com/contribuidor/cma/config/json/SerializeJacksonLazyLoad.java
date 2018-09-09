package com.contribuidor.cma.config.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;


@Configuration
@EnableWebMvc
public class SerializeJacksonLazyLoad extends WebMvcConfigurationSupport {

    @Override
    protected void extendMessageConverters( List<HttpMessageConverter<?>> converters ) {
        for ( HttpMessageConverter<?> converter : converters ) {
            if ( converter instanceof MappingJackson2HttpMessageConverter ) {
                MappingJackson2HttpMessageConverter jacksonConverter = (MappingJackson2HttpMessageConverter) converter;
                ObjectMapper objectMapper = jacksonConverter.getObjectMapper();

                //--- register hibernateModule in MappingJackson2HttpMessageConverter.objectMapper
                objectMapper.registerModule(new Hibernate4Module());

                //--- other configurations
                jacksonConverter.setPrettyPrint( true );
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            }
        }

        super.configureMessageConverters(converters);
    }
}
