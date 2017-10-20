package eu.openminted.workflow.utils;

import java.util.Iterator;
import java.util.List;

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
import com.github.jmchilton.blend4j.galaxy.beans.ToolSection;

@Component
public class WorkflowEngineStatus {

	private static final Logger logger = LoggerFactory.getLogger(WorkflowEngineStatus.class);
	
	@Autowired
	private GalaxyInstance galaxy = null;
	

	public void testInstance(String msg) {
		logger.info("Trivial function to test spring :: " + msg);
		ToolsClient toolClient = galaxy.getToolsClient();
		List<ToolSection> tools = toolClient.getTools();
		Iterator<ToolSection> iter = tools.iterator();
		while (iter.hasNext()) {
			ToolSection tool = iter.next();
			logger.info("Tool :: " + tool.getId() + " - " + tool.getName());
		}
	}
}
