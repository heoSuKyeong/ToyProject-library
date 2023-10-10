package com.project.library.view;

import java.util.Scanner;

public class MsgView {

	/**
	 * 이전메뉴
	 */
	public static void msg() {
		Scanner scan = new Scanner(System.in);
		
		doubleDash();
		System.out.println("엔터를 누르시면 이전 메뉴로 돌아갑니다");
		doubleDash();
		scan.nextLine();
		
	}
	
	/**
	 * 계속진행
	 */
	public static void msg2() {
		Scanner scan = new Scanner(System.in);
		
		doubleDash();
		System.out.println("엔터를 누르시면 계속 진행됩니다");
		doubleDash();
		scan.nextLine();
		
	}
	
	public static void dash() {
		System.out.println("--------------------------------------------------------------");
	}
	
	public static void doubleDash() {
		System.out.println("==============================================================");
		
	}

	public static void title(String title) {
		System.out.println("["+title+"]");
	}

}
