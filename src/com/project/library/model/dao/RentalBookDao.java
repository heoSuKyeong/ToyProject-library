package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import com.project.library.model.vo.BookVo;
import com.project.library.model.vo.OverdueBookVo;
import com.project.library.model.vo.RentalBookVo;
import com.project.library.model.vo.UserVo;


public class RentalBookDao {

	public static ArrayList<RentalBookVo> list;
	
	static {
		
		list = new ArrayList<RentalBookVo>();
	}
	
	static String path="data\\rentalbook.txt";
	
	public static void load() {
		
		
		try {


			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\|");
				
				RentalBookVo r = new RentalBookVo(Integer.parseInt(temp[0]), temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
				
				RentalBookDao.list.add(r);
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("at RentalBookDao.load");
			e.printStackTrace();
		}
		
	}//load()
	
	public static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (RentalBookVo r : list) {
				
				writer.write(String.format("%d|%s|%s|%s|%s|%s|%s\r\n"
										, r.getNum()
										, r.getUserNo()
										, r.getTitle()
										, r.getISBN()
										, r.getRentalDate()
										, r.getReturnDate()
										, r.getReturnFlag()));
			}
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println("at RentalBookDao.save");
			e.printStackTrace();
		}
	}//save()
	
	//------------------------나래----------------
	//도서 대출 메서드
	public static int rentalByISBN(String isbn) {
		
		Scanner scan = new Scanner(System.in);
		
		/*
		1. 해당 도서의 '대출여부:가능' 상태 확인
		 - 가능? > 로그인 확인 메서드로 이동
		 - 불가? > '대출중인 도서입니다.' 출력
		
		2. 로그인 여부 확인(Auth null여부 확인)
		 - 로그인 전(Auth == null): 로그인 메서드로 이동
		 - 로그인 후(Auth !== null): 해댱 회원의 대출 가능 상태 확인 메서드로 이동
		
		3. 대출 가능 상태 확인
		 - 1) 연체 여부 확인
				- YES? > '연체로 인한 대출 제한으로 대출이 불가합니다.'
				- NO? > 대출중인 책 개수 확인
		 - 2) 대출중인 책 개수 확인
				- 10권 이하? > 대출 진행
				- 10권 > '1인 최대 대출 권수 초과로 대출이 불가합니다.'
		
		4. 대출 진행 > 해당 도서 대출여부 상태 변경
		*/
		
		//0. ISBN 유효성 검사
		if (BookDao.book == null) {
//			System.out.println("유효한 ISBN을 입력해주세요");
			return -1;
		}
		
		//1. 해당 도서의 '대출여부: 가능' 상태 확인 > true: 가능, false: 불가
		if (!RentalBookDao.checkReturnFlag(isbn)) {
			
			//대출여부 상태가 false라면,
//			System.out.println("대출중인 도서입니다.");
			return -2;
		}
		
		//대출여부 상태가 true(대출가능)라면 > 로그인 상태 확인 진행

		//2. 로그인 여부 확인
		if (UserDao.auth == null) {
			
//			//auth가 null이면 > 로그인 필요
//			System.out.println("로그인 후에 도서 대출이 가능합니다.");
//			System.out.println("합치고 로그인 메서드 호출 예정");
//			MainVIew.loginViewforRental(); //로그인 후에 다시 이 메서드로 돌아오면 다음 절차 이어서 하겠지?
			return -3;
		}
		
		//auth가 null이 아니면 > 대출가능 상태 확인 진행
		
		//3. 대출 가능 상태 확인
		// 1) 연체 여부 확인 > rentalbook에서 반납상태가 N인지 확인 > N이면 return -4
		// 2) 그다음 연체회원테이블에서 날짜 확인
		//연체회원 테이블에서 회원코드가 auth와 동일하고, 오늘 날짜가 연체종료 날짜보다 이르면(거나 null이면서 대출일로부터 8일째되면 > 연체) 연체 > 대출X
		//					 오늘 날짜가 연체종료 날짜보다 늦으면 대출 O
		LocalDate now = LocalDate.now();
		
		
		for (RentalBookVo r : RentalBookDao.list) {
			
//			if (r.getUserNo().equalsIgnoreCase(UserDao.auth.getUserNo()) && r.getReturnFlag().equalsIgnoreCase("N") && now > r.getRentalDate()) {
			if (r.getUserNo().equalsIgnoreCase(UserDao.auth.getUserNo())) {
				LocalDate date = LocalDate.parse(r.getRentalDate(), DateTimeFormatter.ISO_DATE);
//				System.out.println("대출날짜: " + date);
				LocalDate sevenDaysAfter = date.plusDays(6);
//				System.out.println("반납기한 날짜: " + sevenDaysAfter);
				
				if(r.getReturnFlag().equalsIgnoreCase("N") && now.isAfter(sevenDaysAfter)) {
					
					return -4;
				}
				
			}
		}
		
		
		for (OverdueBookVo o : OverdueBookDao.list) {
			
			if (o.getUserNo().equalsIgnoreCase(UserDao.auth.getUserNo())) {
			
				LocalDate endLocalDate = LocalDate.parse(o.getDelinquencyEndDate());
				
				if (now.isAfter(endLocalDate)) {
					
				} else {
//					System.out.println("연체로 인한 대출 제한으로 대출이 불가합니다.");
					return -4;
					
				}
				
				
			}
		
		}
		
		
		// 2) 대출 중인 책 개수 확인
		//대여정보 테이블에서 회원코드가 auth와 동일하고, 반납 여부가 N이 10개면 대출 불가.
		//												반납 여부 N이 10미만이면 대출 가능
		int sum = 0;

		
		for (RentalBookVo r : list) {
			
			if (r.getUserNo().equalsIgnoreCase(UserDao.auth.getUserNo()) && r.getReturnFlag().equalsIgnoreCase("N")) {
				
				sum++;
			}
			
		}
		
		if (sum >= 10) {
			
			//대출 불가
//			System.out.println("1인 최대 대출 권수 초과로 대출이 불가합니다.");
			return -5;
		}
		
		//10권 이하라면 통과 > 대출 진행
		
		return 1;
		
		
	}
	
	//해당 도서의 대출여부 상태 반환 메서드(true: 대출 가능, false: 대출 불가)
	public static boolean checkReturnFlag(String isbn) {

		for (RentalBookVo r : list) {
			
			if (r.getISBN().equals(isbn)) {
				
				if (r.getReturnFlag().equalsIgnoreCase("Y")) {

//					return true; //"Y" 반납 완료면 대출 가능 > true 반환
					continue;
				} else {
					return false; //"N" 반납 전이면 대출 불가 > false 반환
				}
			}
			
		}
		return true; //대출이력이 아예 없으면 대출 가능 > true 반환
	}
	
	public static void getBookInfo(String isbn) {
		
		for (BookVo b : BookDao.fBook) {
			
			if (b.getISBN().equalsIgnoreCase(isbn)) {
				
				BookDao.book = b;
			}
		}
		
		for (BookVo b : BookDao.tBook) {
			
			if (b.getISBN().equalsIgnoreCase(isbn)) {
				BookDao.book = b;
			}
		}
		
	}
	
	public static void addRentalBook() {
		
//		Calendar cal = Calendar.getInstance();
		Calendar today = Calendar.getInstance();
//		cal.add(cal.DATE, 7);
		
		RentalBookVo r = new RentalBookVo(list.size() + 1, UserDao.auth.getUserNo(), BookDao.book.getTitle(), BookDao.book.getISBN(), String.format("%tF", today), null,"N");
		list.add(r);
	}
	
	


	
}
