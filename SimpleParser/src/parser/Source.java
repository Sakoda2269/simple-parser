package parser;

public class Source {
	
	private final String str;
	private int pos;
	
	public Source(String str) {
		this.str = str;
		this.pos = 0;
	}
	
	public int peek() {
		if (pos < str.length()) {
			return str.charAt(pos);
		}
		return -1;
	}
	
	public void next() {
		pos++;
	}
	
	public boolean isFunc() {
		int count = 0;
		while(true) {
			char c = str.charAt(count + pos);
			if(c == '(') {
				return true;
			} else if (!Character.isLetterOrDigit(c)) {
				return false;
			}
			count++;
		}
	}
	
	public boolean isEnd() {
		return str.length() == pos;
	}
	
}
