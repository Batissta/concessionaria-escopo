package util;

import java.util.Scanner;

public class Uteis {
	private static Scanner t = new Scanner(System.in);
	
	public static void mostrarLinha() {
		System.out.println("----------------------------------------------------");
	}
	public static int leInt(String msg) {
		System.out.print(msg);
		int num = Integer.parseInt(t.nextLine());
		return num;
	}
	public static String leString(String msg) {
		System.out.print(msg);
		String info = t.nextLine();
		return info;
	}
}
