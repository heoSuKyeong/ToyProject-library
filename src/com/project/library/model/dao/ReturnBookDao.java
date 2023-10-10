package com.project.library.model.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

import com.project.library.model.vo.RentalBooksVo;

public class ReturnBookDao {
	
	// 미반납 내역
	public static ArrayList<RentalBooksVo> getRentalBooks() {
		
		TrueBookDao.load();
		FalseBookDao.load();
		RentalBooksDao.load();
		
		ArrayList<RentalBooksVo> list = new ArrayList<RentalBooksVo>();
		
		for (RentalBooksVo b : RentalBooksDao.getRentalBooks()) {
			
			if (b.getReturnFlag().equals("N")) {
				
				list.add(b);
				
			}
			
		}

		return list;
		
	}
	
	// 반납예정일 계산
	public static Calendar getReturnDue(RentalBooksVo b) {
		
		Calendar due = Calendar.getInstance();
		int dueYear = Integer.parseInt(b.getRentalDate().substring(0, 4));
		int dueMonth = Integer.parseInt(b.getRentalDate().substring(5, 7)) - 1;
		int dueDate = Integer.parseInt(b.getRentalDate().substring(8));
		due.set(dueYear, dueMonth, dueDate);
		due.add(Calendar.DATE, 6);
		
		return due;
		
	}
	
	// 변경 사항 저장
	public static void save() {
		
		try {
					
			String path = String.format("data\\rentalBook.txt");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (RentalBooksVo b : RentalBooksDao.rbs) {
				
				writer.write(String.format("%s,%s,%s,%s,%s,%s\n",
										   b.getNum(), b.getUserNo(), b.getISBN(), b.getRentalDate(), b.getReturnDate(), b.getReturnFlag()));
						
			}
					
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}
	
	// 반납 처리
	public static Calendar returnBook(ArrayList<RentalBooksVo> list, int num) {
		
		boolean success = false;
		
		Calendar today = Calendar.getInstance();
	    int year = today.get(Calendar.YEAR);
	    int month = today.get(Calendar.MONTH) + 1;
	    int date = today.get(Calendar.DATE);

	    RentalBooksVo returnedBook = null;

	    for (RentalBooksVo b : list) {
	    	
	        if (UserDao.auth.getUserNo().equals(b.getUserNo()) && b.getReturnFlag().equals("N") && b.getNum() == num) {
	        	
	            String returnDate = String.format("%d-%d-%d", year, month, date);
	            
	            b.setReturnDate(returnDate);
	            b.setReturnFlag("Y");
	            
	            success = true;
	            
	            returnedBook = b;

	            break;
	            
	        }
	        
	    }

	    if (success) {
	    	
	        save();

	        long overtime = today.getTimeInMillis() - getReturnDue(returnedBook).getTimeInMillis();
	        int overDate = (int) (overtime / 1000 / 60 / 60 / 24) + 1;
	        today.add(Calendar.DATE, overDate);
	        
	    } else {
	    	
	    	today.set(0, 0, 1);
	    	
	    }
	    
	    return today;
		
	}

}
