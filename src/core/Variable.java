package core;

import java.util.ArrayList;
import java.util.List;

public class Variable implements Iteration {

	private static final String NEEDED_STRING = "%d";
	
	private String className;
	private String varName;
	private int beginIndex;
	private boolean insertTabBefore;
	private List<String> params;

	public Variable(String className, String prefixVar, int beginIndex, boolean insertTabBefore, String... params) {
		this.className = className;
		if (prefixVar.contains(NEEDED_STRING)) {
			this.varName = prefixVar;
		} else {
			throw new IllegalArgumentException("Il manque au moins un \""+NEEDED_STRING+"\" dans le prefix de la variable");
		}
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
		return className + " " + threatVarName(varIndex) + " = " + endPrefix(varIndex) + ";";
	}
	
	private String threatVarName(int varIndex) {
		return varName.replaceAll(NEEDED_STRING, varIndex+"");
	}

	private String endPrefix(int varIndex) {
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
			return "new " + className + convertParameters(varIndex);
		}
	}

	private String convertParameters(int varIndex) {
		if (params.isEmpty()) {
			return "()";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("(");
			for (int i=0; i<params.size(); i++) {
				String currentParam = params.get(i).replaceAll(NEEDED_STRING, varIndex+"");
				if (i == params.size() - 1) {
					sb.append(currentParam);
				} else {
					sb.append(currentParam + ",");
				}
				
			}
			sb.append(")");
			return sb.toString();
		}
	}

	public static void main(String[] args) {

		Variable c = new Variable("String", "s%d", 51, true); 
		c.iterateAndPrint(50);
		 
	}
}
