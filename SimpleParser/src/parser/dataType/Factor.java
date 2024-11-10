package parser.dataType;

import parser.exceptions.ParseException;

public class Factor {

	private final String type;
	private int num;
	private Variable variable;
	private Expression expression;
	private Function function;
	
	public Factor(int num) {
		this.num = num;
		type = "number";
	}
	
	public Factor(Variable var) {
		variable = var;
		type = "variable";
	}
	
	public Factor(Expression exp) {
		expression = exp;
		type = "expression";
	}
	
	public Factor(Function func) {
		function = func;
		type = "function";
	}
	
	public int culc() throws ParseException {
		if (type.equals("number")) {
			return num;
		} else if(type.equals("expression")) {
			return expression.culc();
		} else if(type.equals("variable")) {
			return variable.getValue();
		} else if(type.equals("function")) {
			return function.call();
		}
		return 0;
	}
	
	
	
}
