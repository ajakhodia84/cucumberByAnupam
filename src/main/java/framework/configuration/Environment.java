package framework.configuration;

import com.google.gson.annotations.SerializedName;
import gherkin.deps.com.google.gson.annotations.Expose;

public class Environment {

	@SerializedName("environment")
	@Expose
	private String environment;
	@SerializedName("executionflag")
	@Expose
	private Boolean executionflag;

	public String getEnvironmentName() {
	return environment;
	}

	public void setEnvironmentName(String environment) {
	this.environment = environment;
	}

	public Boolean getExecutionflag() {
	return executionflag;
	}

	public void setExecutionflag(Boolean executionflag) {
	this.executionflag = executionflag;
	}


}
