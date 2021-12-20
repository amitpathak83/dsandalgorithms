/**
 * 
 */
package com.classic.problems;

import java.util.LinkedHashMap;

/**
 * @author amitp
 *
 */
public class RateLimiter {

	volatile LinkedHashMap<Integer, Integer> counter = null;
	volatile int maxCounter = 0;
	boolean run = true;
	volatile int currentCounter = 0;
	volatile int currentKey = 0;

	public RateLimiter(int window, int maxrate) {
		maxCounter = maxrate;

		this.counter = new LinkedHashMap<Integer, Integer>() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(java.util.Map.Entry<Integer, Integer> eldest) {
				if (this.size() == window) {
					System.out.println("currentCounter "+currentCounter +" removing "+ eldest.getValue());
					RateLimiter.this.currentCounter = RateLimiter.this.currentCounter - eldest.getValue();
					System.out.println("currentCounter now "+currentCounter +" removing "+ eldest.getValue());
					
					return super.removeEldestEntry(eldest);
				}
				return false;

			}
		};
		Thread thread = new Thread(new Runnable() {
			int counterSec = 0;

			@Override
			public void run() {
				while (true && run) {
					try {
						currentKey = counterSec % window;
						counter.put(currentKey, 0);
						System.out.println("added "+currentKey);
						if (counterSec == window) {
							counterSec = 0;
						}
						Thread.sleep(2000);
						counterSec++;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
		thread.start();
	}

	public boolean putMessage(String mesages) {
		if (this.currentCounter > maxCounter) {
			System.out.println("no Space ");
			System.out.println("currentCounter now in no Space "+currentCounter );
		} else {
			this.currentCounter++;
			this.counter.put(currentKey, this.counter.get(currentKey) + 1);
			System.out.println("went through "+mesages);
		}
		return run;

	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		RateLimiter limiter = new RateLimiter(10, 8);
		for (int i = 0; i < 100; i++) {
			Thread.sleep(500);
			limiter.putMessage("Hello" + i);
		}

	}

	private static class Counter {
		long sec;
		int count;

	}

}
