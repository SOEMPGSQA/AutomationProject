package control;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * A simple Java Servlet that triggers a REST call back to JIRA when the servlet URL is accessed. This is a slightly
 * contrived example, as it is not efficient to use REST to communicate with the JIRA instance that the plugin is
 * actually deployed on (you should use the JIRA Java API instead -
 * https://developer.atlassian.com/display/JIRADEV/JIRA+Java+API+Reference).
 *
 * The URL to access this servlet is defined in the atlassian-plugin.xml file.
 */
public class JIRA_control{
	
}