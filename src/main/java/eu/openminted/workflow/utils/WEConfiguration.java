package eu.openminted.workflow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


@Configuration
@ComponentScan(value={"eu.openminted.workflow.utils"})
@PropertySource("classpath:application.properties")
public class WEConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(WEConfiguration.class);
	
	
	@Value("${export.wfengine.url}")
	private String exportWorkflowEngineUrl;

	@Value("${export.wfengine.apiKey}")
	private String exportWorkflowEngineApiKey;
	
	@Bean(name="wf_exporter") 
	public WorkflowEngineReplicator getWorkflowEngineToExport() {
		logger.info("Connect to workflow engine for exporting");
		logger.info("Galaxy Url :: " + this.exportWorkflowEngineUrl);
		logger.info("Galaxy Api :: " + this.exportWorkflowEngineApiKey);
		WorkflowEngineReplicator workflow_engine = new WorkflowEngineReplicator(exportWorkflowEngineUrl, exportWorkflowEngineApiKey);
		return workflow_engine;
	}
	
	
	@Value("${import.wfengine.url}")
	private String importWorkflowEngineUrl;

	@Value("${import.wfengine.apiKey}")
	private String importWorkflowEngineApiKey;
	
	@Bean(name="wf_importer") 
	public WorkflowEngineReplicator getWorkflowEngineToImport() {
		logger.info("Connect to workflow engine for importing");
		logger.info("Galaxy Url :: " + this.importWorkflowEngineUrl);
		logger.info("Galaxy Api :: " + this.importWorkflowEngineApiKey);
		WorkflowEngineReplicator workflow_engine = new WorkflowEngineReplicator(importWorkflowEngineUrl, importWorkflowEngineApiKey);
		return workflow_engine;
	}
	
}	
