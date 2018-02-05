package com.movericks.costmanager.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * CostManager Utility Class that provides all utility functions.
 * 
 * @author sourav
 *
 */
public class CostManagerUtils {

	private static final double EPS = 1e-9;

	public static BufferedReader getReader(File file) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		return bufferedReader;
	}

	public static void close(BufferedReader reader) throws IOException {
		try{
			reader.close();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}

	public static boolean validateFile(String filePath) {
		File file = new File(filePath);
		return file.exists() && !file.isDirectory() && file.canRead();
	}

	public static boolean isZero(double amount) {
		return Math.abs(amount) <= EPS;
	}

	public static boolean isLessThan(double amount1, double amount2) {
		return !isZero(amount1 - amount2) && amount1 < amount2;
	}
}
