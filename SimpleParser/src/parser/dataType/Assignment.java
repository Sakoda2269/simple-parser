package parser.dataType;

import parser.exceptions.ParseException;

public class Assignment {

	private final Variable variable;
	private final Expression expression;
	
	public Assignment(Variable variabel, Expression expression) {
		this.variable = variabel;
		this.expression = expression;
	}
	
	public void eval() throws ParseException {
		variable.setValue(expression.culc());
	}
	
}
