package com.project.library.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.dao.UserDao;
import com.project.library.model.vo.ApplyBookVo;

public class ApplyBookView {

public static void applyBookView() {//3
		
		Scanner scan = new Scanner(System.in);
		
		ArrayList<ApplyBookVo> abs=Controller.applyBooks();
		
		int approval=0;
		System.out.println("희망도서 조회/신청");
		System.out.println("신청 목록");
		System.out.println("===================================");
		if(abs.isEmpty()) {
			System.out.println("신청 목록이 없습니다");
			System.out.println("===================================");
		}else {
			for(ApplyBookVo b : abs) {
				System.out.println("신청 날짜: "+b.getApplyDate());
				System.out.println("도서명: "+b.getTitle());
				System.out.println("저자: "+b.getAuthor());
				System.out.println("출판사: "+b.getPublisher());
				
				if(b.getApproval() == 1) {
					approval=b.getApproval();
					System.out.println("상태: 승인");
				}else if(b.getApproval() == -1) {
					approval=b.getApproval();
					System.out.println("상태: 반려");//반려 사유 가져오기
					String reason=Controller.reason(b.getISBN());
					System.out.println("반려사유: "+reason);
				}else if(b.getApproval() == 0) {
					System.out.println("상태: 대기중");
					approval=b.getApproval();
				}
				System.out.println("===================================");
			}
		}
		System.out.println("계속 진행하시려면 엔터");
		scan.nextLine();
		
		if(approval == 0) {
			System.out.println("희망도서 대기 중 상태입니다");
			System.out.println("이전 메뉴로 돌아갑니다");
			System.out.println("계속 진행하시려면 엔터");
			scan.nextLine();
		}else {
			while(true) {
				System.out.println("희망도서 추가 가능합니다");
				System.out.println("1. 희망도서 추가");
				System.out.println("2. 이전 화면");
				System.out.print("선택: ");
				int input=scan.nextInt();
				
				if(input == 1) {
					ApplyBookVo b=new ApplyBookVo();
					scan.nextLine();
					System.out.print("ISBN: ");
					String isbn=scan.nextLine();
					System.out.print("도서명: ");
					String title=scan.nextLine();
					System.out.print("저자: ");
					String author=scan.nextLine();
					System.out.print("출판사: ");
					String publisher=scan.nextLine();
					System.out.print("가격: ");
					String price=scan.nextLine();
					
					// 데이터 입력
					
					b.setUserNo(UserDao.auth.getUserNo()); //로그인한 정보  나중에 auth변경 해야됨
					
					String date="";
					Calendar now = Calendar.getInstance();
					date=String.format("%tF", now);
					b.setApplyDate(date);
					b.setISBN(isbn);
					b.setTitle(title);
					b.setAuthor(author);
					b.setPublisher(publisher);
					b.setPrice(price);
					b.setApproval(0);
					
					
					//유효성 검사 해야됨
					
					Controller.setApplyBook(b);
					
					System.out.println("완료 되었습니다");
					Controller.msg();
					return;
					
				}else if(input == 2) {
					return;
				}else {
					Controller.msg();
				}
			}
		}
	}
}
