package com.project.library.view;

import java.util.Scanner;

import com.project.library.controller.Controller;

public class DeleteBookView {
	
	public static void getDeleteBookView() {
		
		Scanner scan = new Scanner(System.in);
			
		Controller.dash();
			
		Controller.title("도서 삭제");
			
		System.out.print("삭제할 도서의 ISBN을 입력하세요: ");
		String isbn = scan.nextLine();
					
		boolean isBookExisted = Controller.checkBookExistence(isbn);

		if (!isBookExisted) {
			
			System.out.println("해당 도서가 존재하지 않습니다.");

		} else {
			
			boolean isBookRented = Controller.checkBookRented(isbn);
			
			if (isBookRented) {
				
				System.out.println("해당 도서는 대출 중이므로 삭제 불가합니다.");

			} else {
				
				while (true) {
					
					Controller.dash();
					System.out.print("해당 도서를 삭제하시겠습니까?(Y/N): ");
					String answer = scan.nextLine();
					
					if (answer.equalsIgnoreCase("Y")) {
						
						Controller.deleteBook(isbn);
						System.out.println("삭제가 완료되었습니다.");
						break;
						
					} else if (answer.equalsIgnoreCase("N")) {
				 		
				 		System.out.println("삭제가 취소되었습니다.");
				 		break;
				 		
				 	} else {
				 		
				 		System.out.println("Y 또는 N을 입력하세요.");
				 		Controller.msg2();
				 		
				 	}
					
				}
				
			}

		}
		
		Controller.msg();
		return;
			
	}

}
