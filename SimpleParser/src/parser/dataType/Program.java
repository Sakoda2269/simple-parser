package parser.dataType;

import parser.exceptions.ParseException;

public class Program {

	private Assignment assing;
	private Function func;
	private final String type;
	
	
	public Program(Assignment a) {
		this.assing = a;
		this.type = "assignment";
	}
	
	public Program(Function func) {
		this.func = func;
		this.type = "function";
	}
	
	public void execute() throws ParseException {
		switch(type) {
		case "assignment":
			assing.eval();
			break;
		case "function":
			func.call();
			break;
		}
	}
	
}
