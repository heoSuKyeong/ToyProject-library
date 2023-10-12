package com.project.library.model.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import com.project.library.model.vo.RentalBookVo;

public class ReturnBookDao {
	
	// 미반납 내역
	public static ArrayList<RentalBookVo> getRentalBooks() {
		
		BookDao.load();
		RentalBookDao.load();
		
		ArrayList<RentalBookVo> list = new ArrayList<RentalBookVo>();
		
		for (RentalBookVo b : RentalBookDao.rbs) {
			
			if (b.getUserNo().equals(UserDao.auth.getUserNo()) && b.getReturnFlag().equals("N")) {
				
				list.add(b);
				
			}
			
		}

		return list;
		
	}
	
	// 반납예정일 계산
	public static Calendar getReturnDue(RentalBookVo b) {
		
		Calendar due = Calendar.getInstance();
		int dueYear = Integer.parseInt(b.getRentalDate().substring(0, 4));
		int dueMonth = Integer.parseInt(b.getRentalDate().substring(5, 7)) - 1;
		int dueDate = Integer.parseInt(b.getRentalDate().substring(8));
		due.set(dueYear, dueMonth, dueDate);
		due.add(Calendar.DATE, 6);
		
		return due;
		
	}
	
	// 일련번호 유효성 검사
	public static boolean checkValidation(ArrayList<RentalBookVo> list, int num) {
		
		boolean result = false;
		
		for (RentalBookVo b : list) {
	    	
	        if (UserDao.auth.getUserNo().equals(b.getUserNo()) && b.getReturnFlag().equals("N") && b.getNum() == num) {
	        	
	        	result = true;
	        	
	        }
	        
		}
		
		return result;
		
	}

	
	// 반납 처리
	public static Calendar returnBook(ArrayList<RentalBookVo> list, int num) {
		
		Calendar today = Calendar.getInstance();
	    int year = today.get(Calendar.YEAR);
	    int month = today.get(Calendar.MONTH) + 1;
	    int date = today.get(Calendar.DATE);

	    for (RentalBookVo b : list) {
	    	
	        String returnDate = String.format("%d-%d-%d", year, month, date);
	        
	        b.setReturnDate(returnDate);
	        b.setReturnFlag("Y");
	        
	        RentalBookDao.save();
	        
	        Calendar returnDue = getReturnDue(b);
	        
	        if (today.compareTo(returnDue) == 1) {
	        	
	        	long overtime = today.getTimeInMillis() - returnDue.getTimeInMillis();
		        int overDate = (int) (overtime / 1000 / 60 / 60 / 24) + 1;
		        today.add(Calendar.DATE, overDate);
		        
		        try {
					
					BufferedWriter writer = new BufferedWriter(new FileWriter(String.format("data\\overdueBook.txt")));
					
					writer.write(String.format("%s|%s|%tF|%tF\n", UserDao.auth.getUserNo(), num, returnDue, today));
							
					writer.close();
					
				} catch (Exception e) {
					
					e.printStackTrace();
					
				}
	        	
	        }  else {
		    	
		    	today.set(0, 0, 1);
		    	
		    }
	        
	    }
	    
	    return today;
		
	}

}
