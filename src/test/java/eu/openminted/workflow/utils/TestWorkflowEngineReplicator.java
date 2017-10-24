package eu.openminted.workflow.utils;

import java.io.IOException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import eu.openminted.workflow.utils.WEConfiguration;
import eu.openminted.workflow.utils.WorkflowEngineReplicator;

@Configuration
@PropertySource("classpath:application.properties")
public class TestWorkflowEngineReplicator {

	private static final Logger logger = LoggerFactory.getLogger(TestWorkflowEngineReplicator.class);
	

	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WEConfiguration.class);
		
		logger.info("Exporting workflows from workflow engine");
		WorkflowEngineReplicator wfEngineToExport = context.getBean("wf_exporter", WorkflowEngineReplicator.class);
		wfEngineToExport.exportWorkflows("/home/gkirtzou/foo");
		
		logger.info("Importing workflows from workflow engine");
		WorkflowEngineReplicator wfEngineToImport = context.getBean("wf_importer", WorkflowEngineReplicator.class);
		wfEngineToImport.importWorkflows("/home/gkirtzou/foo/ToImport/");
		
		context.close();
	
	}
	
}
