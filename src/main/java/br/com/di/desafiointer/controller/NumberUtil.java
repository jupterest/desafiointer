package br.com.di.desafiointer.controller;

import java.util.Random;

public class NumberUtil {

	static Random rand = new Random(System.currentTimeMillis());
	
	private NumberUtil() {
	}
	
	public static Integer generateId() {
		return Math.abs(rand.nextInt());
	}

}
