package parser;

import parser.dataType.Assignment;
import parser.dataType.Expression;
import parser.dataType.Factor;
import parser.dataType.Function;
import parser.dataType.Program;
import parser.dataType.Programs;
import parser.dataType.Term;
import parser.dataType.Variable;
import parser.exceptions.ParseException;

public class Parser extends Source{

	public Parser(String str) {
		super(str);
	}
	
	public Programs programs() throws ParseException {
		Programs progs = new Programs();
		Program prog;
		while(!isEnd() && ((prog = program()) != null)) {
			progs.addProgram(prog);
			if (peek() == ';') {
				next();
			} else {
				throw new ParseException("; was not found");
			}
			lf();
		}
		return progs;
	}
	
	public Program program() throws ParseException {
		Program prog;
		if(isFunc()) {
			prog = new Program(function());
		} else {
			prog = new Program(assingment());
		}
		return prog;
	}
	
	public Assignment assingment() throws ParseException {
		Variable variable = variable();
		space();
		if(peek() != '=') {
			throw new ParseException("= was not found");
		}
		next();
		Expression expr = expression();
		return new Assignment(variable, expr);
	}
	
	public int number() {
		StringBuilder sb = new StringBuilder();
		int ch;
		while ((ch = peek()) >= 0 && Character.isDigit(ch)) {
			sb.append((char) ch);
			next();
		}
		return Integer.parseInt(sb.toString());
	}
	
	public void space() {
		while(peek() == ' ') {
			next();
		}
	}
	
	public void lf() {
		while (peek() == '\n') {
			next();
		}
	}
	
	public Function function() throws ParseException {
		Function function = new Function(identifier());
		next();
		while (peek() != ')') {
			Expression expr = expression();
			function.addArg(expr);
			if(peek() == ',') {
				next();
				continue;
			}
			if(peek() == ')') {
				break;
			}
			throw new ParseException(") was not found");
		}
		next();
		return function;
	}
	
	public String identifier() throws ParseException {
		StringBuilder sb = new StringBuilder();
		int ch;
		boolean first = true;
		while ((ch = peek()) >= 0) {
			char tmp = (char) ch;
			if(first && Character.isDigit(tmp)) {
				throw new ParseException("variable must not start with a number");
			}
			if(!Character.isLetterOrDigit(ch)) {
				break;
			}
			first = false;
			sb.append((char) ch);
			next();
		}
		return sb.toString();
	}
	
	public Variable variable() throws ParseException {
		return new Variable(identifier());
	}
	
	public Factor factor() throws ParseException {
		space();
		int tmp = peek();
		if (tmp < 0) {
			throw new ParseException("error!");
		}
		char ch = (char) tmp;
		Factor factor;
		if(ch == '(') {
			next();
			factor = new Factor(expression());
			if (peek() != ')') {
				throw new ParseException(") was not found");
			}
			next();
		} else if (Character.isDigit(ch)) {
			factor = new Factor(number());
		} else {
			if(isFunc()) {
				factor = new Factor(function());
			} else {
				factor = new Factor(variable());
			}
		}
		space();
		return factor;
	}

	public Term term() throws ParseException {
		Term term = new Term();
		term.addFactor(factor());
		while (true) {
			switch(peek()) {
			case '*':
				term.addOperator('*');
				next();
				term.addFactor(factor());
				continue;
			case '/':
				term.addOperator('/');
				next();
				term.addFactor(factor());
				continue;
			}
			break;
		}
		return term;
	}

	public Expression expression() throws ParseException {
		Expression expression = new Expression();
		expression.addTerms(term());
		while(true) {
			switch(peek()) {
			case '+':
				expression.addOperators('+');
				next();
				expression.addTerms(term());
				continue;
			case '-':
				expression.addOperators('-');
				next();
				expression.addTerms(term());
				continue;
			}
			break;
		}
		return expression;
	}
	
}

