package core;

import java.util.ArrayList;
import java.util.List;

public class Core {

	private String className;
	private String varName;
	private int beginIndex;
	private boolean insertTabBefore;
	private List<String> params;

	public Core(String className, String prefixVar, int beginIndex, boolean insertTabBefore, String... params) {
		this.className = className;
		this.varName = prefixVar;
		this.beginIndex = beginIndex;
		this.insertTabBefore = insertTabBefore;
		this.params = new ArrayList<>();
		for (String s : params) {
			this.params.add(s);
		}
	}

	public void iterateAndPrint(int numberOfIteration) {
		System.out.println(iterate(numberOfIteration));
	}

	private String iterate(int numberOfIteration) {
		StringBuilder sb = new StringBuilder();
		int index = beginIndex;
		for (int i = 0; i < numberOfIteration; i++) {
			String s = iteration(index + i);
			s = insertTabBefore ? "\t" + s : s;
			sb.append(s + "\n");
		}
		return sb.toString();
	}

	private String iteration(int varIndex) {
		return className + " " + varName + varIndex + " = " +endPrefix() + ";";
	}
	
	private String endPrefix() {
		switch (className) {
		case "String":
			return "\"\"";
		case "int":
			return "0";
		case "boolean":
			return "true";
		case "double":
			return "0.0";
		case "float":
			return "0.0f";
		default:
			return "new " + className + convertParameters();
		}
	}

	private String convertParameters() {
		if (params.isEmpty()) {
			return "()";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for (String s : params) {
				sb.append(s + ",");
			}
			sb.append(")");
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		Core c = new Core("String", "s", 12, true);
		c.iterateAndPrint(100);
	}
}
