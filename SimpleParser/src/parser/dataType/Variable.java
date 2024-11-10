package parser.dataType;

import java.util.HashMap;
import java.util.Map;

import parser.exceptions.ParseException;

public class Variable {
	
	private static Map<String, Integer> variables = new HashMap<>();
	private final String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	public int getValue() throws ParseException {
		if(!variables.containsKey(name)) {
			throw new ParseException(name + " is undefined");
		}
		return variables.get(name);
	}
	
	public void setValue(int value) {
		variables.put(name, value);
	}
	
}
