package com.project.library.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.vo.RentalBookVo;

public class RentalBookListView {

public static void rentalChView() {//4
		
		ArrayList<RentalBookVo> result=Controller.rentalbooks();
		
		Scanner scan = new Scanner(System.in);
		
		
		System.out.println("도서 대출 현황");
		System.out.println("===================================");
		if(result.isEmpty()) {
			System.out.println("도서 대출 정보가 없습니다");
			System.out.println("===================================");
		}else {
			for(RentalBookVo b : result) {
				System.out.println("대출 날짜: "+b.getRentalDate());
				if(b.getReturnFlag().equals("Y")) {
					System.out.println("반납 날짜: "+b.getReturnDate());
				}else {
					System.out.println("미 반납");
				}
				System.out.println("도서명: "+b.getTitle());
				System.out.println("ISBN: "+b.getISBN());
				System.out.println("===================================");
			}
		}
		
		System.out.println("계속 진행 하려면 엔터");
		scan.nextLine();
		
	}
}
