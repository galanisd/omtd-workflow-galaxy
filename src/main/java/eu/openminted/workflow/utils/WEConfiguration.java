package eu.openminted.workflow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.github.jmchilton.blend4j.galaxy.GalaxyInstance;
import com.github.jmchilton.blend4j.galaxy.GalaxyInstanceFactory;

@Configuration
@ComponentScan(value={"eu.openminted.workflow.utils"})
@PropertySource("classpath:application-export.properties")
public class WEConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(WEConfiguration.class);
	
	
	@Value("${galaxy.url}")
	private String galaxyInstanceUrl;

	@Value("${galaxy.apiKey}")
	private String galaxyApiKey;
	
	@Bean 
	public GalaxyInstance getGalaxyInstance() {
		logger.info("Galaxy Url :: " + this.galaxyInstanceUrl);
		logger.info("Galaxy Api :: " + this.galaxyApiKey);
		GalaxyInstance galaxy = GalaxyInstanceFactory.get(galaxyInstanceUrl, galaxyApiKey);
		return galaxy;
	}
	
}	
