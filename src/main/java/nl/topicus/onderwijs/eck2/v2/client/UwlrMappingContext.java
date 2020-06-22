package nl.topicus.onderwijs.eck2.v2.client;

import java.util.HashMap;
import java.util.Map;

public class UwlrMappingContext
{
	private Map<Object, Object> properties;

	public UwlrMappingContext()
	{
		properties = new HashMap<>();
	}

	public UwlrMappingContext(Map<Object, Object> properties)
	{
		this.properties = properties;
	}

	public Object getProperty(Object key)
	{
		return properties.get(key);
	}

	public void setProperty(Object key, Object value)
	{
		properties.put(key, value);
	}

	public Map<Object, Object> getProperties()
	{
		return properties;
	}

}
