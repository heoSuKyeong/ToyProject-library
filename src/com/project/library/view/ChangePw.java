package com.project.library.view;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.library.model.dao.UserDao;
import com.project.library.model.vo.UserVo;

public class ChangePw {

public static int changePw() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("[마이페이지 - 비밀번호 변경]");
		
		//0. 현재 비밀번호를 저장할 변수 선언
		String currentPw = null;	//로그인시 사용된 비밀번호 변수 가져오기...(??...)??
		String newPw = null;
		
		//1. 현재 비밀번호 확인
		while (true) {
			
			System.out.print("현재 비밀번호를 입력해주세요: ");
			String input = scan.nextLine();
			
			//for문을 돌아서 list에서 로그인한 사용자의 아이디와 입력받은 input(현재 비밀번호)이 일치하는것을 찾아.
			for(UserVo u : UserDao.list) {
				if (u.getId().equals(UserDao.auth.getId()) && u.getPw().equals(input)) {
					
					currentPw = u.getPw();
					break;
				}
			}//for
			
			//현재 비밀번호 일치여부 검증
			if (currentPw == null) { //일치 비밀번호 없음
				
				System.out.println("현재 비밀번호가 잘못 입력되었습니다.");
				System.out.println("다시 입력해주세요.");
			
			} else { //비밀번호 일치
				
				break;
			}
			
		}//while
		
		//2. 변경할 비밀번호 확인
		while (true) {
			
			System.out.println("\n비밀번호는 4~15자리 영문 또는 숫자로 입력해주세요.");
			System.out.print("변경할 비밀번호를 입력해주세요: ");
			newPw = scan.nextLine();
			System.out.println("--------------------------------------------------------------------------------");
			
			//비밀번호 유효성 검사
			if (isValidPw(newPw) == false) {				
				System.out.println("비밀번호는 4~15자리 영문 또는 숫자로 입력해주세요.");
				continue;				//반복문 맨 처음으로
			} else {
				//변경하시겠습니까?Y/N 입력받는거 판단
				while (true) {
					System.out.print("비밀번호를 변경하시겠습니까? (Y/N): ");
					String input = scan.nextLine();
					System.out.println("--------------------------------------------------------------------------------");
					//equalsIgnoreCase를 자주쓰는 경우는 대소문자 구분없이 비교할 떄 많이 사용됩니다.
					if (input.equalsIgnoreCase("Y")) {
						for (UserVo u : UserDao.list) {
							
							if (u.getId().equals(UserDao.auth.getId())) {
								u.setPw(newPw);
							}
							
						}
						System.out.println("비밀번호 변경이 완료되었습니다.");
						System.out.println("초기화면으로 이동하시려면 Enter를 입력해주세요."); 
						UserDao.save();
						UserDao.auth = null;
						scan.nextLine();
						return 0; 
						
					} else if (input.equalsIgnoreCase("N")) {
					
						System.out.println("비밀번호 변경이 취소되었습니다.");
						System.out.println("계속 진행하시려면 Enter를 입력해주세요."); 
						scan.nextLine();
						return 1;
						
					} else {
						
						System.out.println("Y 또는 N으로 입력해주세요.");
						continue;
					}
					
				}//Y/N입력 while	
				
			}//if
			
		}//while
		
	}


	//비밀번호 유효성 검사 메서드
	private static boolean isValidPw(String newPw) {

		//비밀번호 > 8~12자리 영문과 숫자 조합 입력만 허용한다. 
		//조건에 해당하지 않는 비밀번호 입력 시, ‘비밀번호는 4~15자리 영문과 숫자 조합으로 입력해주세요.’ 변경할 비밀번호 재입력 받는다.

		String regex = "^[A-Za-z0-9]{4,15}$";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(newPw);
		
		if (!m1.find()) {
			return false;
		}
		
		return true;
	}

}

