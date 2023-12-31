package com.project.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.dao.ApplyBookDao;
import com.project.library.model.dao.BookDao;
import com.project.library.model.dao.UserDao;
import com.project.library.model.vo.ApplyBookVo;

public class AdminView {

	public static void adminView() {
		
		System.out.println(UserDao.auth.getName() + " 관리자님 환영합니다.");
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			Controller.title("관리자 메뉴");
			System.out.println("1. 도서 추가");
			System.out.println("2. 도서 삭제");
			System.out.println("3. 연체 관리 ");
			System.out.println("4. 희망 도서 신청 관리");
			System.out.println("5. 로그아웃");

			System.out.print("번호를 입력해주세요: ");
			String input = scan.nextLine();
			Controller.dash();

			switch (input) {

			case "1": addBookView();break;
			case "2": Controller.deleteBookView(); break;
			case "3":
				System.out.println("[연체 관리]");
				break;
			case "4": applyBookChView();break;
			case "5": System.out.println("로그아웃되었습니다.");Controller.doubleDash();return;
			default: Controller.msg();break;
			}

		}
	}
	

	private static void bookAddView() {//1
		Scanner scan = new Scanner(System.in);
		
		System.out.print("도서명: ");
		String title=scan.nextLine();
		System.out.print("ISBN: ");
		String ISBN=scan.nextLine();
		System.out.print("출판사: ");
		String publisher=scan.nextLine();
		System.out.print("저자: ");
		String author=scan.nextLine();
		
		int ch=Controller.addBook(title, ISBN, publisher, author);
		
		if(ch == 1) {
			System.out.println("신규도서 추가 되었습니다");
			Controller.msg();
		}else if(ch == -1) {
			System.out.println("기존에 등록된 도서입니다");
			Controller.msg();
		}
		
		
	}

	private static void bookDel() {//2

		Scanner scan = new Scanner(System.in);
		
		System.out.print("INBS: ");
		String ISBN=scan.nextLine();
		
		int ch=Controller.bookDel(ISBN); // -1 없는도서, 0 대출중, 1삭제완료
		
		if(ch == 1) {
			System.out.println("삭제 완료 되었습니다");
		}else if(ch == 0) {
			System.out.println("대출 중인 책입니다");
		}else if(ch == -1) {
			System.out.println("해당 도서가 존재하지 않습니다");
		}
		
		
	}
	
	private static void applyBookChView() {//4

		Scanner scan = new Scanner(System.in);
		ArrayList<ApplyBookVo> list = ApplyBookDao.abs;
		
		int count=0;
		for(ApplyBookVo b : list) {
			count++;
			System.out.println("목록 번호: "+count);
			System.out.println("회원 코드: "+b.getUserNo());
			System.out.println("신청날짜: "+b.getApplyDate());
			System.out.println("ISBN: "+b.getISBN());
			System.out.println("도서명: "+b.getTitle());
			System.out.println("저자: "+b.getAuthor());
			System.out.println("출판사: "+b.getPublisher());
			System.out.println("가격: "+b.getPrice());
			String ap="";
			if(b.getApproval() == 1) {
				ap="승인";
			}else if(b.getApproval() == 0) {
				ap="대기";
			}else if(b.getApproval() == -1) {
				ap="반려";
			}
			System.out.println("승인여부: "+ap);
			
			Controller.doubleDash();
		}
		System.out.print("변경할 도서 번호 입력해주세요: ");
		int input=scan.nextInt();
		Controller.dash();
		
		if(input < 1 || input > count) {
			System.out.println("잘 못 선택하셨습니다");
			Controller.msg();
			return;
		}
		
//		System.out.println(count);
		scan.nextLine();
		while(true) {
			System.out.println(input+"번 선택하셨습니다");
			System.out.println("1. 승인");
			System.out.println("2. 반려");
			System.out.println("3. 취소(이전화면)");
			System.out.print("번호를 입력해주세요: ");
			int select=scan.nextInt();
			Controller.dash();
			
			if(select == 1) {
				Controller.applyBooksSatus(count, select);
				System.out.println("승인 되었습니다");
				Controller.msg();
				break;
			}else if(select == 2) {
				Controller.applyBooksSatus(count, select);
				System.out.println("반려 되었습니다");
				Controller.msg();
				break;
			}else if(select == 3) {
//				Controller.msg();
				return;
			}
		}
		
		
	
	}

	
	private static void addBookView() {
		Scanner scan = new Scanner(System.in);
		
//		Controller.dash();
		Controller.title("도서 추가");
		Controller.dash();

		System.out.print("도서명: ");
		String title = scan.nextLine();
		
		System.out.print("ISBN: ");
		String isbn = scan.nextLine();
		
		System.out.print("저자: ");
		String author = scan.nextLine();
		
		System.out.print("출판사: ");
		String publisher = scan.nextLine();
		
		int result = Controller.addBook(isbn);
		
		if (result == -1) {
			
			System.out.println("올바른 ISBN을 입력해주세요.(숫자)");
			Controller.msg();
			return;
			
		} else if (result == -2) {
			
			System.out.println("기존에 등록된 도서입니다.");
			Controller.msg();
			return;
			
		}else if(result == 1) {

			System.out.printf("<%s>도서를 추가하시겠습니까?(Y/N): ", title);
			String input = scan.nextLine();
			
			if (input.equalsIgnoreCase("Y")) {
				
				String location = Controller.addBookList(title, isbn, author, publisher);
				System.out.println("도서 추가가 완료되었습니다.");
				Controller.doubleDash();
				System.out.printf("도서명: %s\n", title);
				System.out.printf("ISBN: %s\n", isbn);
				System.out.printf("저자: %s\n", author);
				System.out.printf("출판사: %s\n", publisher);
				System.out.printf("위치: %s\n", location);
				Controller.doubleDash();
				
				
				
			} else if (input.equalsIgnoreCase("N")) {
				System.out.println("도서 추가가 취소되었습니다.");
			} else {
				System.out.println("Y/N으로 입력해주세요.");
			}
			
			Controller.msg2();
			
			

		}
		
	}



}
