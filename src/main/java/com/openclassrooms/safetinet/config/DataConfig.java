package com.openclassrooms.safetinet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.openclassrooms.safetinet.data.DataWrapper;
import com.openclassrooms.safetinet.repository.DataLoader;


@Configuration
public class DataConfig {

	@Bean
	public DataWrapper dataWrapper() {
		DataLoader dataload = new DataLoader("FileData.json");
		return dataload.loadJsonData();
	}
}
