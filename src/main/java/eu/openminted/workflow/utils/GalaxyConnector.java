package eu.openminted.workflow.utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jmchilton.blend4j.galaxy.GalaxyInstance;
import com.github.jmchilton.blend4j.galaxy.GalaxyInstanceFactory;
import com.github.jmchilton.blend4j.galaxy.WorkflowsClient;
import com.github.jmchilton.blend4j.galaxy.beans.Workflow;


public class GalaxyConnector {

	private static final Logger logger = LoggerFactory.getLogger(GalaxyConnector.class);
	
	private GalaxyInstance galaxy = null;
	
	public GalaxyConnector(String workflowEngineUrl, String workflowEngineApiKey) {
		this.galaxy = GalaxyInstanceFactory.get(workflowEngineUrl, workflowEngineApiKey);
	}

	public void exportWorkflows(String folder) throws IOException {
		logger.info("Export workflows to folder " + folder);
	
		// Get workflow client
		WorkflowsClient workflowClient = galaxy.getWorkflowsClient();
		
		// Get workflows
		List<Workflow> workflows = workflowClient.getWorkflows();
		logger.info("Number of Workflows " + workflows.size());
		
		// Export each workflow to a file
		Iterator<Workflow> iterWF = workflows.iterator();	
		while(iterWF.hasNext()) {
			Workflow workflow = iterWF.next();		
			logger.info("Getting workflow <" + workflow.getName() + ">");
			String filename = folder + "/" + workflow.getName() + ".ga";		
			String workflowJson = workflowClient.exportWorkflow(workflow.getId());
			logger.info("Exporting workflow to " + filename);
			FileUtils.writeStringToFile(new File(filename), workflowJson);					
		}

	}
	
	public void importWorkflows(String folderName) throws IOException {
		logger.info("Import workflow from " + folderName);
		
		// Get workflow client
		WorkflowsClient workflowClient = galaxy.getWorkflowsClient();
		
		// Get workflow descriptions
		File folder = new File(folderName);
		File[] listOfFiles = folder.listFiles();
		
		if(listOfFiles.length > 0){
			// Import each workflow description from a file
			for (File file : listOfFiles) {
				
				if (file.isFile() && file.getName().endsWith(".ga")) {
					String workflow_json = FileUtils.readFileToString(file);
					logger.info("Workflow to import:: " + file.getName());
					workflowClient.importWorkflow(workflow_json);				
				}
			}
		}else{
			logger.info("No worfklows were found.");
		}
		
	}
	
}
