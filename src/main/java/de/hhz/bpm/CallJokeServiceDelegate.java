package de.hhz.bpm;

import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.glassfish.jersey.client.ClientConfig;

public class CallJokeServiceDelegate implements JavaDelegate {
	
	private final Logger LOGGER = Logger.getLogger(CallJokeServiceDelegate.class.getName());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		 LOGGER.info("\n\n  ... LoggerDelegate invoked by "
		            + "processDefinitionId=" + execution.getProcessDefinitionId()
		            + ", activtyId=" + execution.getCurrentActivityId()
		            + ", activtyName='" + execution.getCurrentActivityName() + "'"
		            + ", processInstanceId=" + execution.getProcessInstanceId()
		            + ", businessKey=" + execution.getProcessBusinessKey()
		            + ", executionId=" + execution.getId()
		            + " \n\n");
		    
		    //call welcome-message-service
		       
		    Client client = ClientBuilder.newClient(new ClientConfig().register(JokeMBR.class));
		    WebTarget userTarget = client.target("http://api.icndb.com/jokes/random");
		          
		    String first = (String)execution.getVariable("first");    
		    String last = (String)execution.getVariable("last");
		    
		    String joke = userTarget.queryParam("firstName", first).queryParam("lastName", last).request(MediaType.APPLICATION_JSON).get(String.class);
		    
		    //Provide Welcome-Message as a Process Variable
		    execution.setVariable("joke", joke);		
	}

}
