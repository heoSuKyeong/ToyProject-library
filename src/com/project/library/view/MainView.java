package com.project.library.view;

import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.dao.BookDao;
import com.project.library.model.dao.OverdueBookDao;
import com.project.library.model.dao.RentalBookDao;
import com.project.library.model.dao.UserDao;


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
				case "2" : //bookChView();break;
				case "3" : loginView();break;
				case "4" : System.out.println("종료합니다.");return;
				default : Controller.msg();break;
			}
		}
	}

	private static void load() {

		BookDao.load();
		RentalBookDao.load();
		UserDao.load();
		OverdueBookDao.load();
		
	}

	public static void save() {

		BookDao.save();
		RentalBookDao.save();
		UserDao.save();
	}
	
	public static void joinView() {
//		Controller.dash();
		Controller.title("회원가입");
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("*이름은 한글 2~6글자로 입력해주세요.");
		System.out.print("이름을 입력하세요: ");
		String name = scan.nextLine();
		
		System.out.println("*전화번호는 \"010-XXXX-XXXX\" 입력해주세요.");
		System.out.print("전화번호를 입력하세요: ");
		String tel = scan.nextLine();
		
		System.out.println("*이메일은 '@'포함하여 입력해주세요.");
		System.out.print("이메일을 입력하세요: ");
		String email = scan.nextLine();
		
		System.out.println("*아이디는 영어+숫자 조합으로 4~15글자 입력해주세요.");
		System.out.print("아이디를 입력하세요: ");
		String id = scan.nextLine();

		System.out.println("*비밀번호는 영어+숫자 조합으로 4~15글자 입력해주세요.");
		System.out.print("비밀번호를 입력하세요: ");
		String pw = scan.nextLine();
		
		
		boolean joinCheck = Controller.join(name, tel, email, id, pw);
		
		if (joinCheck) {
			Controller.userAdd(name, tel, email, id, pw);
			Controller.doubleDash();
			
			
			while(true) {
				System.out.print("로그인하시겠습니까?(Y/N): ");
				String input = scan.nextLine().toUpperCase();
				Controller.dash();
				if (input.equals("Y")) {
					loginView(); return;
				} else if (input.equals("N")) {
					return;
				} else {
					System.out.println("입력값이 올바르지 않습니다.");
				}
				
			}
			
		} else {
			Controller.msg();
		}
		
	}
	
	private static void loginView() {

		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			
			System.out.print("아이디를 입력하세요: ");
			String id = scan.nextLine();
			System.out.print("비밀번호를 입력하세요: ");
			String pw = scan.nextLine();
			
			Controller.dash();
			
			int level=Controller.login(id,pw);
			
			if(level == 1) {
				MemberView.memberView();
				break;
			}else if(level == 0) {
				AdminView.adminView();
				break;
			}else {
				System.out.println("아이디 또는 비밀번호가 틀립니다");
				Controller.msg();
				return;
			}
			
		}
		
	}

}
