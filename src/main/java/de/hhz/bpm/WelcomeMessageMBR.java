package de.hhz.bpm;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

public class WelcomeMessageMBR implements MessageBodyReader<WelcomeMessage>{

	@Override
	public boolean isReadable(Class<?> arg0, Type arg1, Annotation[] arg2, MediaType arg3) {
		return true;
	}

	@Override
	public WelcomeMessage readFrom(Class<WelcomeMessage> arg0, Type arg1, Annotation[] arg2, MediaType arg3,
			MultivaluedMap<String, String> arg4, InputStream arg5) throws IOException, WebApplicationException {
		
		JsonReader reader = Json.createReader(arg5);
		JsonObject object = reader.readObject();
		String message = object.getString("message");
		String gender = object.getString("gender");
		String first = object.getString("first");
		String last = object.getString("last");
		WelcomeMessage welcomeMessage = new WelcomeMessage(message, gender, first, last);
		
		return welcomeMessage;
	}

}
