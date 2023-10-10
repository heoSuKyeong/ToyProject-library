package com.project.library.view;

import java.util.ArrayList;
import java.util.Scanner;


public class MainView {

	public static void startView() {
		
		load();
		
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			System.out.println("📖📗📔쌍용도서관📖📗📔");
			System.out.println("1. 회원가입");
			System.out.println("2. 도서조회");
			System.out.println("3. 로그인");
			System.out.println("4. 종료");
			
			System.out.print("번호를 선택하세요: ");
			String input=scan.nextLine();
			Controller.dash();
			switch(input) {
				case "1" : joinView();break;
				case "2" : bookChView();break;
				case "3" : loginView();break;
				case "4" : System.out.println("종료합니다.");return;
				default : Controller.msg();break;
			}
		}
	}



	private static void load() {

		
	}

	public static void save() {

		
		
	}


}
