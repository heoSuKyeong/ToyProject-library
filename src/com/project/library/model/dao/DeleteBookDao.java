package com.project.library.model.dao;

import com.project.library.model.vo.BookVo;
import com.project.library.model.vo.RentalBookVo;

public class DeleteBookDao {
	
	// 해당 도서의 존재 확인
	public static boolean checkBookExistence(String isbn) {
		
		BookDao.load();
		RentalBookDao.load();
		
		boolean isBookExisted = false;
		
		for (BookVo t : BookDao.tBook) {
			
			if (isbn.equals(t.getISBN())) {

				isBookExisted = true;
				break;
				
			}
			
		}

		for (BookVo f : BookDao.fBook) {
					
			if (isbn.equals(f.getISBN())) {

				isBookExisted = true;
				break;
								
			}
							
		}
		
		return isBookExisted;
	    
	}
	
	// 대출 여부 확인
	public static boolean checkBookRented(String isbn) {
		
		boolean isBookRented = false;
    	
	    for (RentalBookVo b : RentalBookDao.rbs) {
	    		
	    	if (isbn.equals(b.getISBN())) {
	    		
	    		isBookRented = true;
	            break;
	                
	        }
	    		
	    }
	    
	    return isBookRented;
		
	}

	// 도서 삭제
	public static void deleteBook(String isbn) {
		
		for (BookVo t : BookDao.tBook) {
	 			
	 		if (isbn.equals(t.getISBN())) {
	 				
	 			BookDao.tBook.remove(t);
	 			BookDao.save("data\\trueBook.txt", BookDao.tBook);
	 			break;
	 				
	 		}
	 			
	 	}
		
		for (BookVo f : BookDao.fBook) {
			
			if (isbn.equals(f.getISBN())) {
				
				BookDao.fBook.remove(f);
	 	    	BookDao.save("data\\falseBook.txt", BookDao.fBook);
	 	    	break;
	 	    								
	 	    }
	 	    
	 	}
	 	
	}

}
