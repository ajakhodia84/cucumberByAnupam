package framework.configuration;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import framework.exceptions.EnvironmentNotSetupException;
import framework.shared.FrameworkConstants;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EnvironmentDeserializer implements JsonDeserializer<Environment> {
	private String JSON = null;

	public Environment deserialize(JsonElement arg0, Type arg1, JsonDeserializationContext arg2)
			throws JsonParseException {
		return null;
	}

	public EnvironmentDeserializer() throws IOException {
		JSON = new String(Files.readAllBytes(Paths.get(FrameworkConstants.ENVIRONMENT_JSON_FILE_PATH)));
	}

	public Environment getEnvironment() throws EnvironmentNotSetupException {
		System.out.println("Execution environment is being picked from 'Environment.JSON'");
		Type targetClassType = new TypeToken<ArrayList<Environment>>() {
		}.getType();
		List<Environment> targetCollection = new Gson().fromJson(JSON, targetClassType);
		Environment environment = null;
		try{environment = targetCollection.stream().filter(x -> x.getExecutionflag() == true).findFirst().get();}
		catch(NullPointerException ex) {
			throw new EnvironmentNotSetupException(
					"Environment is not set up properly in the Environment.JSON file");
		}
		if (environment == null) {
			throw new EnvironmentNotSetupException(
					"Environment is not set up properly in the Environment.JSON file");
		}
		System.out.println("Environment set to :: " + environment.getEnvironmentName());
		return environment;
	}

	public Environment getEnvironment(String environmentName) throws EnvironmentNotSetupException {
		System.out.println("Setting environment passed from command line parameter as env=" + environmentName);
		Type targetClassType = new TypeToken<ArrayList<Environment>>() {
		}.getType();
		List<Environment> targetCollection = new Gson().fromJson(JSON, targetClassType);
		Environment environment = targetCollection.stream()
				.filter(x -> x.getEnvironmentName().trim().equals(environmentName.trim())).findFirst().get();
		if (environment == null) {
			throw new EnvironmentNotSetupException(
					"Environment is not set up properly in the config/Environment.JSON file");
		}
		System.out.println("Environment set to :: " + environment.getEnvironmentName());
		return environment;
	}
}
