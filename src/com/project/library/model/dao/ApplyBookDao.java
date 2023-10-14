package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.library.model.vo.ApplyBookVo;

public class ApplyBookDao {

public static ArrayList<ApplyBookVo> abs;
	
	static {
		
		abs=new ArrayList<ApplyBookVo>();
	}
	
	
	public static void load() {
		
		String dir="data\\applyBook.txt";
		
		try {
			BufferedReader reader=new BufferedReader(new FileReader(dir));

			String line=null;
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
				
				ApplyBookVo ab=new ApplyBookVo();
//				U100,2023-08-11,1062926180251,쌍용의자바,정훈훈,좋은책,14000,1
				ab.setUserNo(temp[0]);
				ab.setApplyDate(temp[1]);
				ab.setISBN(temp[2]);
				ab.setTitle(temp[3]);
				ab.setAuthor(temp[4]);
				ab.setPublisher(temp[5]);
				ab.setPrice(temp[6]);
				ab.setApproval(Integer.parseInt(temp[7]));
				
				abs.add(ab);
				
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void save() {
		
		String dir="data\\apllyBook.txt";
			
		try {
			
			BufferedWriter write = new BufferedWriter(new FileWriter(dir));
			
			for(ApplyBookVo b : abs) {
				
				String temp = String.format("%s,%s,%s,%s,%s,%s,%s,%d\r\n"
											,b.getUserNo()
											,b.getApplyDate()
											,b.getISBN()
											,b.getTitle()
											,b.getAuthor()
											,b.getPublisher()
											,b.getPrice()
											,b.getApproval());
				
				
				write.write(temp);
			}
			write.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	


	public static ArrayList<ApplyBookVo> getBook() {
		ArrayList<ApplyBookVo> bs=new ArrayList<ApplyBookVo>();
		
		for(ApplyBookVo b : abs) {
			if(b.getUserNo().equals(UserDao.auth.getUserNo())) { //회원코드 로그인쪽에서 가져와서 넣어줘야됨
				bs.add(b);
			}
		}

		
		
		
		return bs;
	}


	public static void setApplyBook(ApplyBookVo b) {

		abs.add(b);
		
	}


	public static void applyBooksSatus(int count, int select) {
		int temp=0;
		if(select == 1) {
			temp=1;
		}else if(select == 2) {
			temp=-1;
		}
		
		
		if(temp != 0) {
			abs.get(count-1).setApproval(temp);
		}
		
	}


}
