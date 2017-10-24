package eu.openminted.workflow.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.github.jmchilton.blend4j.galaxy.GalaxyInstance;
import com.github.jmchilton.blend4j.galaxy.GalaxyInstanceFactory;
import com.github.jmchilton.blend4j.galaxy.ToolsClient;
import com.github.jmchilton.blend4j.galaxy.WorkflowsClient;
import com.github.jmchilton.blend4j.galaxy.beans.Tool;
import com.github.jmchilton.blend4j.galaxy.beans.ToolSection;
import com.github.jmchilton.blend4j.galaxy.beans.Workflow;
import com.github.jmchilton.blend4j.galaxy.beans.WorkflowDetails;

@Component
public class WorkflowEngineStatus {

	private static final Logger logger = LoggerFactory.getLogger(WorkflowEngineStatus.class);
	
	@Autowired
	private GalaxyInstance galaxy = null;
	

	public void exportWorkflows(String folder) throws IOException {
		logger.info("Exporting workflows to folder " + folder);
	
		// Get workflows
		WorkflowsClient workflowClient = galaxy.getWorkflowsClient();
		List<Workflow> workflows = workflowClient.getWorkflows();
		logger.info("Number of Workflows " + workflows.size());
		
		/*
		Iterator<Workflow> iterWF = workflows.iterator();	
		while(iterWF.hasNext()) {
			Workflow workflow = iterWF.next();
			logger.info("Workflow::" + workflow.getId());
			
		}
		*/
		String workflowId = workflows.get(0).getId();
		logger.info(workflowId);
		String filename = folder + "/" + workflowId;
		logger.info(filename);
		String workflowJson = workflowClient.exportWorkflow(workflowId);
		
		FileUtils.writeStringToFile(new File(filename), workflowJson);
		
		//workflowClient.importWorkflow(json)
	}
	
	public Workflow importWorkflow(String filename) throws IOException {
		logger.info("Importing workflow " + filename);
		
		// Get workflows
		WorkflowsClient workflowClient = galaxy.getWorkflowsClient();
		
		String workflow_json = FileUtils.readFileToString(new File(filename));
		logger.info("Workflow to import::\n" + workflow_json);
		Workflow importedWorkflow = workflowClient.importWorkflow(workflow_json);
		return importedWorkflow;
		
	}
	
	public WorkflowDetails setWorkflowPublished(String workflowId, boolean published) {
		logger.info("Setting workflow " + workflowId + " as published::" + published);
		
		// Get workflows
		WorkflowsClient workflowClient = galaxy.getWorkflowsClient();
		WorkflowDetails workflowDetails = workflowClient.showWorkflow(workflowId);
		workflowDetails.setPublished(published);
		return workflowDetails;
		
	}
}
