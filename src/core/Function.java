package core;

import java.util.ArrayList;
import java.util.List;

public class Function implements Iteration {

	private static final String NEEDED_STRING = "%d";

	private String functionName;
	private String functionParameter;
	private List<Integer> beginIndexes;

	public Function(String functionName, String functionParameter, int... beginIndexes) {
		this.functionName = functionName;
		this.functionParameter = functionParameter;
		this.beginIndexes = new ArrayList<>();
		if (functionParameter.contains(NEEDED_STRING)) {
			if (numberOfNeededStringOccurence(functionParameter) != beginIndexes.length) {
				throw new IllegalArgumentException("Un ou plusieurs index n'ont pas été initialisés, ou trop l'ont été");
			} else {
				for (int s : beginIndexes) {
					this.beginIndexes.add(s);
				}
			}
		}
	}

	private int numberOfNeededStringOccurence(String s) {
		return s.split(NEEDED_STRING, -1).length - 1;
	}

	public void iterateAndPrint(int numberOfIteration) {
		System.out.println(iterate(numberOfIteration));
	}

	private String iterate(int numberOfIteration) {
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<numberOfIteration; i++) {
			sb.append(iteration(i)+"\n");
		}
		return sb.toString();
	}
	
	private String iteration(int iterationNumber) {
		StringBuilder sb = new StringBuilder(functionName);
		sb.append("(");
		sb.append((beginIndexes.isEmpty()) ? functionParameter.trim() : threatParameters(iterationNumber));
		sb.append(");");
		return sb.toString();
	}
	
	private String threatParameters(int iterationNumber) {
		String s = functionParameter;
		for (int i : beginIndexes) {
			s = s.replaceFirst(NEEDED_STRING, (i+iterationNumber)+"");
		}
		return s;
	}

	public static void main(String[] args) {
		Function f = new Function("System.out.println", "\"Bonjour je suis numéro %d\"", 1);
		f.iterateAndPrint(50);
	}
}
