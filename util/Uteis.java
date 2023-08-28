package util;

import java.util.Scanner;

public class Uteis {
	private static Scanner t = new Scanner(System.in);
	
	public static void mostrarLinha() {
		System.out.println("----------------------------------------------------");
	}
	public static int leInt(String msg) {
		int num = 0;
		while(true){
			System.out.print(msg);
			try{
		num = Integer.parseInt(t.nextLine());
		break;
		}catch(java.lang.NumberFormatException e){
			System.out.println("//ERROR: Digite um numero inteiro//");
		}
	}
	return num;
	}
	public static String leString(String msg) {
		System.out.print(msg);
		String info = t.nextLine();
		return info;
	}
}
