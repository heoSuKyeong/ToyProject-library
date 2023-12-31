﻿package com.project.library.view;

import java.util.Scanner;

//import com.project.erpsystem.dao.MypageDao;
import com.project.library.controller.Controller;
import com.project.library.model.dao.UserDao;

public class MemberView {
	
	public static void memberView() {
		
		System.out.println(UserDao.auth.getName() + "님 환영합니다.");
		
		
		Scanner scan=new Scanner(System.in);
		
		while(true) {
			Controller.title("회원 메뉴");
			System.out.println("1. 도서 대출");
			System.out.println("2. 도서 반납");
			System.out.println("3. 희망 도서 신청");
			System.out.println("4. 나의 대출/이력 조회");
			System.out.println("5. 비밀번호 변경");
			System.out.println("6. 로그아웃");
			System.out.print("번호를 선택하세요: ");
			
			int input=scan.nextInt();
			Controller.dash();
			
			switch(input) {

				case 1 : RentalBookView.rentalBookView();break;
				case 2 : Controller.returnBookView();break;
				case 3 : ApplyBookView.applyBookView();break;
				case 4 : RentalBookListView.rentalChView();break;
				case 5 : 
				// 비밀번호 변경 메서드
				if (ChangePw.changePw() == 1) {
					break;
				} else {
//					InitialView.erpStart();
//					return 0;
//					break;
				}
				case 6 : System.out.println("로그아웃되었습니다.");
						Controller.save();
						Controller.doubleDash();
						return;
				default : Controller.msg();break;
			
			}
			
		}
		
	}
	
}
