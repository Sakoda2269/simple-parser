package parser.dataType;

import java.util.ArrayList;
import java.util.List;

import parser.exceptions.ParseException;

public class Programs {
	
	private List<Program> progs = new ArrayList<>();
	
	public Programs() {
	}
	
	public void addProgram(Program prog) {
		progs.add(prog);
	}
	
	public void execute() throws ParseException {
		for(Program prog : progs) {
			prog.execute();
		}
	}
}
