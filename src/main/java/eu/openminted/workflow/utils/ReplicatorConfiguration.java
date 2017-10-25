package eu.openminted.workflow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
//@ComponentScan(value={"eu.openminted.workflow.utils"})
@PropertySource("classpath:application.properties")
public class ReplicatorConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(ReplicatorConfiguration.class);
	
	@Value("${export.wfengine.url}")
	private String exportWorkflowEngineUrl;

	@Value("${export.wfengine.apiKey}")
	private String exportWorkflowEngineApiKey;
	
	@Value("${import.wfengine.url}")
	private String importWorkflowEngineUrl;

	@Value("${import.wfengine.apiKey}")
	private String importWorkflowEngineApiKey;
	
	@Bean(name="wf_exporter") 
	public GalaxyConnector getWorkflowEngineToExport() {
		logger.info("Connect to workflow engine for exporting");
		logger.info("Galaxy Url :: " + this.exportWorkflowEngineUrl);
		logger.info("Galaxy Api :: " + this.exportWorkflowEngineApiKey);
		GalaxyConnector galaxyConnector = new GalaxyConnector(exportWorkflowEngineUrl, exportWorkflowEngineApiKey);
		return galaxyConnector;
	}
		
	@Bean(name="wf_importer") 
	public GalaxyConnector getWorkflowEngineToImport() {
		logger.info("Connect to workflow engine for importing");
		logger.info("Galaxy Url :: " + this.importWorkflowEngineUrl);
		logger.info("Galaxy Api :: " + this.importWorkflowEngineApiKey);
		GalaxyConnector galaxyConnector = new GalaxyConnector(importWorkflowEngineUrl, importWorkflowEngineApiKey);
		return galaxyConnector;
	}
	
}	
