package com.bc.revan.Config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    ModelMapper modelMapper() {
   ModelMapper m=	new ModelMapper();
   m.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return m;
	}

}
