package parser.dataType;

import java.util.ArrayList;
import java.util.List;

import parser.exceptions.ParseException;

public class Term {
	
	private List<Factor> factors = new ArrayList<>();
	private List<Character> operators = new ArrayList<>();
	
	public Term() {
		
	}
	
	public void addOperator(char ope) {
		operators.add(ope);
	}
	
	public void addFactor(Factor fac) {
		factors.add(fac);
	}
	
	public int culc() throws ParseException {
		int res = factors.get(0).culc();
		for(int i = 0; i < operators.size(); i++) {
			switch(operators.get(i)) {
			case '*' :
				res *= factors.get(i + 1).culc();
				break;
			case '/':
				res /= factors.get(i + 1).culc();
				break;
			}
		}
		return res;
	}
	
}
