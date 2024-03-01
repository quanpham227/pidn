package com.pivinadanang;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PivinadanangApplication {

	public static void main(String[] args) {
		SpringApplication.run(PivinadanangApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
//    modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		modelMapper.createTypeMap(String.class, Date.class);
		modelMapper.getConfiguration().setSkipNullEnabled(true);
		return modelMapper;
	}

}
