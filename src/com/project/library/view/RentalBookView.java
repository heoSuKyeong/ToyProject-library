package com.project.library.view;

import java.util.Calendar;
import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.dao.BookDao;
import com.project.library.model.dao.RentalBookDao;
import com.project.library.model.dao.UserDao;

public class RentalBookView {

	public static void rentalBookView() {
		Scanner scan = new Scanner(System.in);
		
		while (true) {
//			Controller.dash();
			Controller.title("도서 조회 및 대출");
			System.out.println("0. 이전 화면");
			System.out.println("1. 도서 조회");
			System.out.println("2. 도서 대출"); //조회에서 리스트 인덱스를 가져올 수 있을지 몰라서 일단 ISBN입력받아 대출하는걸로
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			
			switch(input) {
				case "0" : return;
				case "1" : System.out.println("도서 조회 메서드 호출 예정"); break;
				case "2" : rentalByISBN(); break;
				default  : Controller.msg();
			}
			
		}//while문
	}
	
	public static void rentalByISBN() {
		Calendar cal = Calendar.getInstance();
//		Calendar today = Calendar.getInstance();
		
		Scanner scan = new Scanner(System.in);
		
//		System.out.println(RentalBookDao.list);
		Controller.dash();
		System.out.print("대출할 도서의 ISBN번호를 입력하세요: ");
		String input = scan.nextLine();
		
		
		switch (Controller.rentalByISBN(input)) {
			case -1 : 
				System.out.println("유효한 ISBN을 입력해주세요");
				Controller.msg();
				return;
			case -2 : 
				System.out.println("대출중인 도서입니다.");
				Controller.msg();
				return;
			case -3 :
				System.out.println("로그인 후에 도서 대출이 가능합니다.");
				System.out.println("합치고 로그인 메서드 호출 예정"); 
				Controller.msg();
				return;
			case -4 :
				System.out.println("연체로 인한 대출 제한으로 대출이 불가합니다.");
				Controller.msg();
				return;
			case -5 :
				System.out.println("1인 최대 대출 권수 초과로 대출이 불가합니다.");
				Controller.msg();
				return;
//			case 0 :
//				return;
			case 1 : 
				System.out.printf("<%s>도서를 대출하시겠습니까?(Y/N): ", BookDao.book.getTitle());
				input = scan.nextLine();
				
				if (input.equalsIgnoreCase("Y")) {
					cal.add(cal.DATE, 6);
					
					Controller.addRentalBook();
					
//					System.out.println(RentalBookDao.list);
					System.out.println("대출이 완료되었습니다."); //대출 완료 후 book == null 작업이 필요할까?
					Controller.doubleDash();
					System.out.println("[대출 도서 정보]");
					System.out.println("도서명: " + BookDao.book.getTitle());
					System.out.println("저자: " + BookDao.book.getAuthor());
					System.out.println("출판사: " + BookDao.book.getPublisher());
					System.out.printf("반납기한: %tF\n", cal);
					Controller.doubleDash();
					
					
				} else if (input.equalsIgnoreCase("N")) {
					System.out.println("대출이 취소되었습니다.");
				} else {
					System.out.println("Y/N으로 입력해주세요.");
				}
				
				Controller.msg2();
		}
		
		
	}
}
