package eu.openminted.workflow.utils;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.jmchilton.blend4j.galaxy.beans.Workflow;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowDetails;

import eu.openminted.workflow.utils.WEConfiguration;
import eu.openminted.workflow.utils.WorkflowEngineStatus;

//@RunWith(SpringJUnit4ClassRunner.class) 
@Configuration
@PropertySource("classpath:application-import.properties")
public class TestExport {

	private static final Logger logger = LoggerFactory.getLogger(TestExport.class);
	
	@Value("${galaxy.url}")
	String galaxyInstanceUrl;

	@Value("${galaxy.apiKey}")
	String galaxyApiKey;
	
	//@Test
	public void TestConnection() {
		logger.info("Setting test");
		logger.info("galaxyInstanceUrl:" + galaxyInstanceUrl);
		logger.info("galaxyApiKey:" + galaxyApiKey);
		WorkflowEngineStatus wes = new WorkflowEngineStatus();
	}
	
	public static void main(String[] args) throws IOException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(WEConfiguration.class);
		WorkflowEngineStatus wf_engine = context.getBean(WorkflowEngineStatus.class);
		
		//wf_engine.exportWorkflows("/home/gkirtzou/foo");
		//Workflow importedWF = wf_engine.importWorkflow("/home/gkirtzou/foo/fd767e8b7355e21a");
		//logger.info(importedWF.toString());
		//logger.info("Workflow id :: " + importedWF.getId());
		//logger.info("Workflow name :: " + importedWF.getName());
		//logger.info("Workflow url :: " + importedWF.getUrl());
		//wf_engine.setWorkflowPublished(importedWF.getId(), true); //		0a248a1f62a0cc04  // 03501d7626bd192f
		WorkflowDetails wf = wf_engine.setWorkflowPublished("0a248a1f62a0cc04", true);
		logger.info("ID " + wf.getId());
		logger.info("Name " + wf.getName());
		logger.info("IsPublished " + wf.isPublished());
		logger.info("Owner " + wf.getOwner());
		
		//close the context
		context.close();
		logger.info("TestConnection ended.");
	}
	
}
