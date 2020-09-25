package com.manoel.relogio.api.core;

/***
 * Classe de configuração do Model Mapper para uso do DTO
 */

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig 
{
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}
