package com.project.library.view;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.dao.ApplyBookDao;
import com.project.library.model.dao.BookDao;
import com.project.library.model.dao.OverdueBookDao;
import com.project.library.model.dao.RefusalBookDao;
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
				case "2" : SearchCategory();break;
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
		ApplyBookDao.load();
		RefusalBookDao.load();
		
	}

	public static void save() {

		BookDao.save();
		RentalBookDao.save();
		UserDao.save();
		ApplyBookDao.save();
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
	
	//도서명 저자 출판사 ISBN으로 검색어를 입력받는다
	//정렬 옵션
	public static void SearchCategory(){
		//1. 조회옵션선택(도서명 저자 출판사 ISBN)
		System.out.print("조회옵션 입력(1.도서명, 2.저자, 3.출판사, 4.ISBN): ");
		Scanner scan = new Scanner(System.in);
		int search_opt = scan.nextInt();
		scan.nextLine();
		
		//1-1. 조회옵선선택에서 옵션이 선택된 경우
		if(search_opt >= 1 && search_opt <= 4) {
			//2. 검색어 입력
			System.out.print("검색어입력: ");
			String search_word = scan.nextLine();
			
			//2-1. 조회옵션에서 해당 키워드를 발견하지 못했을 경우
			ArrayList<String[]> SearchResult = Searchb(search_word, search_opt);
			if(SearchResult == null || SearchResult.isEmpty()) {
				System.out.println("해당 도서가 존재하지 않습니다");
				return;
			}
			
			//3. 정렬옵션 선택(도서명순 저자순)
			System.out.print("정렬옵션을 입력하세요(1.도서명순, 2.저자순): ");
			int sort_opt = scan.nextInt();
			
			//4. 정렬후 출력
			if(sort_opt >= 1 && sort_opt <= 2) {
				ArrayList<String[]> SortedResult = SortBook(SearchResult, sort_opt);
				//줄별로 출력
				for(String[] sr : SortedResult) {
					for(String sr2: sr) {
						System.out.printf("%s ", sr2);
					}
					System.out.println();
				}				
			}
			//3-1. 정렬옵션 입력 실패
			else {
				System.out.println("정렬옵션을 다시 입력하세요.");
				return;
			}
			
		}
		//1-2. 조회옵션 입력 실패
		else {
			System.out.println("조회옵션을 다시 입력하세요.");
			return;
		}

	}
		
	public static ArrayList<String[]> Searchb(String word, int opt) {
		
		try {
			String dir="data\\trueBook.txt";
			BufferedReader reader=new BufferedReader(new FileReader(dir));
			
			String line=null;
			
			ArrayList<String[]> list1 = new ArrayList<String[]>();
			
			while((line=reader.readLine()) != null) {

				String[] temp=line.split("\\|");
				if(temp[opt].contains(word)) {
					list1.add(temp);
				}
			}
			
			reader.close();
			return list1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	public static ArrayList<String[]> SortBook(ArrayList<String[]> source, int opt) {
		int size = source.size();
		for(int i=0;i<size-1;i++) {
			for(int j=i+1; j<size;j++) {
				if(source.get(i)[opt].compareTo(source.get(j)[opt])>0) {
					String[] tmp = source.get(i);
					source.set(i, source.get(j)); 
					source.set(j, tmp);
					break;
					
				}
			}
		}
		return source;
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
