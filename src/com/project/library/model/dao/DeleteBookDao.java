package com.project.library.model.dao;

import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.vo.FalseBookVo;
import com.project.library.model.vo.RentalBooksVo;
import com.project.library.model.vo.TrueBookVo;

public class DeleteBookDao {
	
	public static boolean checkBookExistence(String isbn) {
		
		TrueBookDao.load();
		FalseBookDao.load();
		RentalBooksDao.load();
		
		boolean isBookExisted = false;
		
		for (TrueBookVo t : TrueBookDao.list) {
			
			if (isbn.equals(t.getIsbn())) {

				isBookExisted = true;
				break;
				
			}
			
		}

		for (FalseBookVo f : FalseBookDao.list) {
					
			if (isbn.equals(f.getIsbn())) {

				isBookExisted = true;
				break;
								
			}
							
		}
		
		return isBookExisted;
	    
	}

	public static void deleteBook(String isbn) {
	    	
	    boolean isBookRented = false;
	    	
	    for (RentalBooksVo b : RentalBooksDao.rbs) {
	    		
	    	if (isbn.equals(b.getISBN())) {
	    		
	    		isBookRented = true;
	            break;
	                
	        }
	    		
	    }

	    if (isBookRented) {
	        	
	        System.out.println("해당 도서는 대출 중이므로 삭제 불가합니다.");
	        return;
	            
	    } else {
	        	
	        Scanner scan = new Scanner(System.in);

	        while (true) {
	        	
	        	Controller.dash();
	        	
	        	System.out.print("해당 도서를 삭제하시겠습니까?(Y/N): ");
	 	        String answer = scan.nextLine();

	 	        if (answer.equalsIgnoreCase("Y")) {
	 	        	
	 	        	for (TrueBookVo t : TrueBookDao.list) {
	 	    			
	 	    			if (isbn.equals(t.getIsbn())) {

	 	    				TrueBookDao.list.remove(t);
	 	    				TrueBookDao.save();
	 	    				break;
	 	    				
	 	    			}
	 	    			
	 	        	}
	 	    				
	 	    		for (FalseBookVo f : FalseBookDao.list) {
	 	    					
	 	    			if (isbn.equals(f.getIsbn())) {

	 	    				FalseBookDao.list.remove(f);
	 	    				FalseBookDao.save();
	 	    				break;
	 	    								
	 	    			}
	 	    							
	 	    		}
	 	    		
	 	    		System.out.println("도서 삭제가 완료되었습니다.");
	 	    		return;
	
	 	    	} else if (answer.equalsIgnoreCase("N")) {
	 	            	
	 	            System.out.println("도서 삭제가 취소되었습니다.");
	 	            break;
	 	                
	 	        } else {
	 	            	
	 	            System.out.println("Y 또는 N을 입력하세요.");
	 	                
	 	        }
	 	        
	 	    }
	        
	    }

	}

}
