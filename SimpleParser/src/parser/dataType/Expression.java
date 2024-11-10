package parser.dataType;

import java.util.ArrayList;
import java.util.List;

import parser.exceptions.ParseException;

public class Expression {

	private List<Term> terms = new ArrayList<>();
	private List<Character> operators = new ArrayList<>();
	
	public Expression() {
		
	}
	
	public void addTerms(Term term) {
		terms.add(term);
	}
	
	public void addOperators(char ope) {
		operators.add(ope);
	}
	
	public int culc() throws ParseException {
		int res = terms.get(0).culc();
		for(int i = 0; i < operators.size(); i++) {
			switch(operators.get(i)) {
			case '+' :
				res += terms.get(i + 1).culc();
				break;
			case '-':
				res -= terms.get(i + 1).culc();
				break;
			}
		}
		return res;
	}
	
}
