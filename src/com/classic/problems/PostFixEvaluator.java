/**
 * 
 */
package com.classic.problems;

import java.util.ArrayDeque;

/**
 * @author amitp
 *
 */
public class PostFixEvaluator {
	static String operators = "+/-*^";

	public static int evaluate(String expression) {
		char[] symbols = expression.toCharArray();
		ArrayDeque<Integer> expressionStack = new ArrayDeque<Integer>();
		for (Character character : symbols) {
			if (!expressionStack.isEmpty() && operators.indexOf(character.toString()) > -1) {
				int op2 = expressionStack.pop();
				int op1 = expressionStack.pop();

				switch (character) {
				case '+':
					expressionStack.push(op1 + op2);
					break;
				case '-':
					expressionStack.push(op1 - op2);
					break;
				case '*':
					expressionStack.push(op1 * op2);
					break;
				case '/':
					expressionStack.push(op1 / op2);
					break;
				case '^':
					expressionStack.push((int) Math.pow(op1, op2));
					break;

				default:

					break;
				}

			} else {
				expressionStack.push(Character.getNumericValue(character));
			}

		}
		return expressionStack.pop();

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(PostFixEvaluator.evaluate("623+-382/+*2^3+"));

	}

}
