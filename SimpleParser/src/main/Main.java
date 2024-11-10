package main;

import parser.Parser;
import parser.exceptions.ParseException;

public class Main {

	public static void main(String[] args) {
		String program = 
"""
x = 10;
y = 3;
a = pow(max(x, y), min(x, y));
print(a);
""";
		Parser p = new Parser(program);
		try {
			p.programs().execute();
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

}
