/**
 * 
 */
package com.classic.problems;

/**
 * @author amitp
 *
 */
public class TowerOFHanoi {

	public void towerofHanoi(int rings, String source, String target, String helper) {
		if (rings == 1) {
			System.out.println("move top ring " + rings + " from " + source + " to " + target);
			return;
		}
		towerofHanoi(rings - 1, source, helper, target);
		towerofHanoi(1, source, target, helper);
		towerofHanoi(rings - 1, helper, target, source);

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new TowerOFHanoi().towerofHanoi(3, "A", "C", "B");

	}

}
