package test;

import java.util.ArrayList;

public class Test2 {

	public static void main(String[] args) {
		ArrayList<UserVo1> list =new ArrayList<UserVo1>();
		
		
		
		UserVo1 u1 = new UserVo1("U101","현빈","010-3451-7890","binbin@gmail.com","bini5","1111",1);
		UserVo1 u2 = new UserVo1("U102","수진","010-3451-7890","binbin@gmail.com","bini5","1111",1);
		UserVo1 u3 = new UserVo1("U103","세비","010-3451-7890","binbin@gmail.com","bini5","1111",1);
		UserVo1 u4 = new UserVo1("U104","싸비","010-3451-7890","binbin@gmail.com","bini5","1111",1);
		UserVo1 u5 = new UserVo1("U105","수호","010-3451-7890","binbin@gmail.com","bini5","1111",1);
		
		
		list.add(u1);
		list.add(u2);
		list.add(u3);
		list.add(u4);
		list.add(u5);
		
		
		
	}
	
	public static class UserVo1 {

		String userNo;
		String name;
		String tel;
		String email;
		String id;
		String pw;
		int level;
		
		public UserVo1() {
			// TODO Auto-generated constructor stub
		}

		public UserVo1(String userNo, String name, String tel, String email, String id, String pw, int level) {
			super();
			this.userNo = userNo;
			this.name = name;
			this.tel = tel;
			this.email = email;
			this.id = id;
			this.pw = pw;
			this.level = level;
		}
	}
}
