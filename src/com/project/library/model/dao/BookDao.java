package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.library.model.vo.BookVo;

public class BookDao {

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

}
