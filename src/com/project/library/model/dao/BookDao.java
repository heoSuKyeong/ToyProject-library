package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.library.model.vo.BookVo;

public class BookDao {

	public static BookVo book; //ISBN을 입력받아 해당하는 책의 정보를 저장하는 변수	

	public static ArrayList<BookVo> tBook;
	public static ArrayList<BookVo> fBook;
	
	static {
		
		tBook = new ArrayList<BookVo>();
		fBook = new ArrayList<BookVo>();
		
	}
	
	public static void load() {
		
		String dir1 = "data\\trueBook.txt";
		String dir2 = "data\\falseBook.txt";
		
		bookLoad(dir1, tBook);
		bookLoad(dir2, fBook);
		
	}

	
	private static void bookLoad(String dir, ArrayList<BookVo> list) {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(dir));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\|");
				
				BookVo b = new BookVo();
				
				b.setISBN(temp[0]);
				b.setTitle(temp[1]);
				b.setAuthor(temp[2]);
				b.setPublisher(temp[3]);
				b.setLocation(temp[4]);
				
				list.add(b);
				
			}
			
			reader.close();

		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	public static ArrayList<BookVo> bookCh(String title) {
		
		ArrayList<BookVo> bs = new ArrayList<BookVo>();
		
		for (BookVo b : tBook) {
			
			if (b.getTitle().contains(title)) {
				
				bs.add(b);
				
			}
			
		}
		
		for (BookVo b : fBook) {
			
			if (b.getTitle().contains(title)) {
				
				bs.add(b);
				
			}
			
		}
		
		return bs;
		
	}
	
	public static void save(String path, ArrayList<BookVo> list) {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			if (path.equals("data\\trueBook.txt")) {
				
				for (BookVo b : BookDao.tBook) {
					
					writer.write(String.format("%s|%s|%s|%s|%s|%s\n",
												b.getISBN(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getLocation()));
							
				}
				
			} else if (path.equals("data\\falseBook.txt")) {
				
				for (BookVo b : BookDao.fBook) {
					
					writer.write(String.format("%s|%s|%s|%s|%s|%s\n",
												b.getISBN(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getLocation()));
							
				}
				
			}
			
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}


	}


	
	private static void bookSave(String dir, ArrayList<BookVo> list) {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(dir));
			
			for (BookVo b : list) {

				writer.write(String.format("%s|%s|%s|%s|%s\r\n"
										, b.getISBN()
										, b.getTitle()
										, b.getAuthor()
										, b.getPublisher()
										, b.getLocation()));
			}
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println("at BookDao.save");
			e.printStackTrace();
		}
		
	}
	
	public static void save() {
		
		String dir1="data\\trueBook.txt";
		String dir2="data\\falseBook.txt";
		
		bookSave(dir1, tBook);
		bookSave(dir2, fBook);
		
	}
	
	
	public static int addBook(String isbn) {
		
		//ISBN 유효성 검사
		
		if (!isValidIsbn(isbn)) {
			
			return -1;
		}
		
		
		for (BookVo b : BookDao.fBook) {
			
			if (b.getISBN().equals(isbn)) {
				return -2;
			}
		}
		
		for (BookVo b : BookDao.tBook) {
			
			if (b.getISBN().equals(isbn)) {
				return -2;
			}
		}
		
		return 1;
		
	}
	
	private static boolean isValidIsbn(String isbn) {

		String regex = "^\\d$";
		Pattern p1 = Pattern.compile(regex);
		Matcher m1 = p1.matcher(isbn);
		
		if (!m1.find()) {
			return false;
		}
		
		return true;
		
	}

	public static String addBookList(String title, String isbn, String author, String publisher) {
		
		String location = createLocation(author);
		
		BookVo f = new BookVo(isbn, title, author, publisher, location);
		
		BookDao.fBook.add(f);
		
		return location;
	}
	
	//도서 위치를 랜덤으로 생성하는 메서드
	private static String createLocation(String author) {

		//기존 위치와 중복검사를 진행해야 하나? 근데 한 위치에 여러개있을수도있쨔낭 이거잠깐 보류
		Random random = new Random();
		
		return (random.nextInt(701) + 100) + "." + author.substring(0, 1);
		
	}//createLocation
	
	

}
