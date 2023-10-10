package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import com.project.library.model.vo.BookVo;
import com.project.library.model.vo.RentalBookVo;

public class BookDao {

	
	public static ArrayList<BookVo> tBook;
	public static ArrayList<BookVo> fBook;
	
	static {
		tBook=new ArrayList<BookVo>();
		fBook=new ArrayList<BookVo>();
	}
	
	public static void load() {
		
		String dir1=".\\data\\trueBook.txt";
		String dir2=".\\data\\falseBook.txt";
		
		bookLoad(dir1, tBook);
		bookLoad(dir2, fBook);
		
		
		
	}

	private static void bookLoad(String dir, ArrayList<BookVo> list) {
		try {
			
			BufferedReader reader=new BufferedReader(new FileReader(dir));
			
			String line=null;
			
			while((line=reader.readLine()) != null){
				
				String[] temp=line.split("\\|");
				
				BookVo b=new BookVo();
				
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
		ArrayList<BookVo> bs=new ArrayList<BookVo>();
		
		
		for(BookVo b : tBook) {
			if(b.getTitle().contains(title)) {
				bs.add(b);
			}
		}
		
		for(BookVo b : fBook) {
			if(b.getTitle().contains(title)) {
				bs.add(b);
			}
		}
		
		return bs;
		
	}

	public static int addBook(String title, String ISBN, String publisher, String author) {
		int result=1;
		
		for(BookVo b : tBook) {
			if(b.getISBN().equals(ISBN)) {
				result=-1;
			}
		}
		for(BookVo b : fBook) {
			if(b.getISBN().equals(ISBN)) {
				result=-1;
			}
		}
		
		if(result == 1) {
			BookVo b=new BookVo();
			b.setTitle(title);
			b.setISBN(ISBN);
			b.setPublisher(publisher);
			b.setAuthor(author);
			
			Random rnd=new Random();
			int temp=rnd.nextInt(700)+100;
			String name=author.substring(0,1);
			b.setLocation(temp + "." + name);
			
			fBook.add(b);
		}
		
		
		return result;
	}

	public static int bookDel(String ISBN) {
		int result=-1;
		
		for(BookVo b : tBook) {
			if(b.getISBN().equals(ISBN)) {
				result=1;
			}
		}
		for(BookVo b : fBook) {
			if(b.getISBN().equals(ISBN)) {
				result=1;
			}
		}
		
		if(result == 1) {
			for(RentalBookVo b : RentalBookDao.rbs) { //렌탈중인지 확인
				if(b.getISBN().equals(ISBN) && b.getReturnFlag().equals("N")) {
					result=0;
				}
			}
		}
		
		if(result == 1) {//도서 삭제
			for(int i=0; i<tBook.size(); i++) {
				if(tBook.get(i).getISBN().equals(ISBN)) {
					tBook.remove(i);
				}
			}
			
			for(int i=0; i<fBook.size(); i++) {
				if(fBook.get(i).getISBN().equals(ISBN)) {
					fBook.remove(i);
				}
			}
			
		}
		
		
		
		
		return result;
		
		
	}
	
	
	
	
	
}
