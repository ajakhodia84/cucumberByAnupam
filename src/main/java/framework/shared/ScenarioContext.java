package framework.shared;

import java.util.HashMap;

public class ScenarioContext {
	
	private HashMap<Object, Object> scenarioData = new HashMap<Object, Object>();

	public void addScenarioData(Object key, Object value) {
		this.scenarioData.put(key, value);
	}
	
	public Object getScenarioData(Object key) {
		return this.scenarioData.get(key);
	}
	
	public void resetScenarioContext() {
		this.scenarioData.clear();
	}
	
	public Boolean isContains(Object key){
        return scenarioData.containsKey(key.toString());
    }
	
}
