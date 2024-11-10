package parser.dataType;

import java.util.ArrayList;
import java.util.List;

import parser.exceptions.ParseException;

public class Function {
	
	private final String name;
	private List<Expression> args = new ArrayList<>();
	
	public Function(String name) {
		this.name = name;
	}
	
	public void addArg(Expression fact) {
		args.add(fact);
	}
	
	public int call() throws ParseException {
		switch(name) {
		case "print":
			String s = "";
			for(Expression fact : args) {
				System.out.print(s);
				System.out.print(fact.culc());
				s = " ";
			}
			System.out.println();
			break;
		case "max":
			if(args.size() < 1) {
				throw new ParseException("min requires at least 1 arg");
			}
			int ma = args.get(0).culc();
			for(int i = 1; i < args.size(); i++) {
				ma = Math.max(ma, args.get(i).culc());
			}
			return ma;
		case "min":
			if(args.size() < 1) {
				throw new ParseException("min requires at least 1 arg");
			}
			int mi = args.get(0).culc();
			for(int i = 1; i < args.size(); i++) {
				mi = Math.min(mi, args.get(i).culc());
			}
			return mi;
		case "pow":
			if(args.size() != 2) {
				throw new ParseException("pow must have just 2 args");
			}
			return (int) Math.pow(args.get(0).culc(), args.get(1).culc());
		}
		return 0;
	}
	
}
