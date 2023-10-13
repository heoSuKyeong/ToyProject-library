package com.project.library.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.library.controller.Controller;
import com.project.library.model.dao.BookDao;
import com.project.library.model.vo.BookVo;
import com.project.library.model.vo.RentalBookVo;

public class ReturnBookView {
	
	public static void getReturnBookView() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			Controller.title("도서 반납");
			
			ArrayList<RentalBookVo> list = Controller.getRentalBooks();

			if (list.isEmpty()) {
					
				System.out.println("대출 내역이 없습니다.");
				Controller.msg();
					
			} else {

				System.out.println("[일련번호]\t[도서명]\t[저자]\t[출판사]\t[ISBN]\t[대출일]\t[반납예정일]");
						
				for (RentalBookVo b : list) {
							
					for (BookVo t : BookDao.tBook) {
									
						if (b.getISBN().equals(t.getISBN())) {

							System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%tF\n",
											   b.getNum(), t.getTitle(), t.getAuthor(), t.getPublisher(), b.getISBN(),
											   b.getRentalDate(), Controller.getReturnDue(b));
								
							break;

						}
							
					}
						
					for (BookVo f : BookDao.fBook) {
										
						if (b.getISBN().equals(f.getISBN())) {

							System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%tF\n",
											   b.getNum(), f.getTitle(), f.getAuthor(), f.getPublisher(), b.getISBN(),
											   b.getRentalDate(), Controller.getReturnDue(b));
								
							break;
										
						}

					}
						
				}
					
				Controller.dash();
						
				while (true) {
							
					System.out.print("반납할 도서의 일련번호를 입력하세요: ");
					String num = scan.nextLine();
					
					boolean result = Controller.checkValidation(list, num);
					
					if (!result) {
						
						System.out.println("올바른 번호를 입력하세요.");
						Controller.msg2();

					} else {
						
						while (true) {
							
							Controller.dash();
							System.out.print("해당 도서를 반납하시겠습니까?(Y/N): ");
							String answer = scan.nextLine();
							
							if (answer.equalsIgnoreCase("Y")) {
								
								Calendar availableDate = Controller.returnBook(list, num);
								System.out.println("반납이 완료되었습니다.");
								
								if (availableDate.get(Calendar.YEAR) != 1) {
									
									System.out.printf("%tF부터 대출 가능합니다.\n", availableDate);
									
								}

								break;
								
							}  else if (answer.equalsIgnoreCase("N")) {
						 		
						 		System.out.println("반납이 취소되었습니다.");
						 		break;
						 		
						 	} else {
						 		
						 		System.out.println("Y 또는 N을 입력하세요.");
						 		Controller.msg2();
						 		
						 	}
							
						}
						
						break;

					}
					
				}
				
			}
			
			Controller.msg();
			return;
				
		}
			
	}

}
