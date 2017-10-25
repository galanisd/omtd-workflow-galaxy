package eu.openminted.workflow.utils;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import eu.openminted.workflow.utils.ReplicatorConfiguration;
import eu.openminted.workflow.utils.GalaxyConnector;

@Configuration
@PropertySource("classpath:application.properties")
public class TestWorkflowEngineReplicator {

	private static final Logger logger = LoggerFactory.getLogger(TestWorkflowEngineReplicator.class);
	

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ReplicatorConfiguration.class);
		
		logger.info("Exporting workflows from workflow engine");
		GalaxyConnector wfEngineToExport = context.getBean("wf_exporter", GalaxyConnector.class);
		wfEngineToExport.exportWorkflows("/home/ilsp/Desktop/foo/");
		
		logger.info("Importing workflows from workflow engine");
		GalaxyConnector wfEngineToImport = context.getBean("wf_importer", GalaxyConnector.class);
		wfEngineToImport.importWorkflows("/home/ilsp/Desktop/foo/ToImport/");
		
		context.close();
	
	}
	
}
