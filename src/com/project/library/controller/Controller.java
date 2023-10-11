package com.project.library.controller;

import java.util.ArrayList;
import java.util.Calendar;

import com.project.library.model.dao.ApplyBookDao;
import com.project.library.model.dao.BookDao;
import com.project.library.model.dao.DeleteBookDao;
import com.project.library.model.dao.RentalBookDao;
import com.project.library.model.dao.ReturnBookDao;
import com.project.library.model.dao.UserDao;
import com.project.library.model.vo.ApplyBookVo;
import com.project.library.model.vo.BookVo;
import com.project.library.model.vo.RentalBookVo;
import com.project.library.view.DeleteBookView;
import com.project.library.view.MainView;
import com.project.library.view.MsgView;
import com.project.library.view.ReturnBookView;

public class Controller {

	public static void mainView() {//main > MainView
		MainView.startView();// 메인뷰 
	}

	public static void msg() {
		MsgView.msg();
		
	}
	
	public static void msg2() {
		MsgView.msg2();
		
	}
	
	public static void dash() {
		MsgView.dash();
		
	}
	
	public static void doubleDash() {
		MsgView.doubleDash();
		
	}

	public static ArrayList<RentalBookVo> rentalbook() {
		ArrayList<RentalBookVo> result=RentalBookDao.getRentalBook();
		return result;
	}


	public static ArrayList<ApplyBookVo> applyBooks() {
		ArrayList<ApplyBookVo> abs=ApplyBookDao.getBook();
		
		return abs;
	}

	public static void setApplyBook(ApplyBookVo b) {
		ApplyBookDao.setApplyBook(b);
		
	}
	
	public static int login(String id, String pw) {
		int result=0;
		result=UserDao.loginCh(id,pw);
		
		return result; 
		
	}
	
	//회원가입
	public static boolean join(String name, String tel, String email, String id, String pw) {
		
		return UserDao.checkJoin(name, tel, email, id, pw);
	}

	public static void userAdd(String name, String tel, String email, String id, String pw) {
		UserDao.userAdd(name, tel, email, id, pw);
	}
/*
	public static int userAdd(String name, String tel, String email, String id, String pw) {
		int result=UserDao.userAdd(name, tel, email, id, pw);
		
		return result;
	}
*/
	public static ArrayList<BookVo> bookCh(String title) {
		return BookDao.bookCh(title);
		
	}

	public static void title(String title) {
		MsgView.title(title);
		
	}

	public static boolean memberRentalCh() {
		boolean result=false;
		result=RentalBookDao.memberRentalCh();
		
		return result;
	}

	public static boolean addRentalBook(BookVo book) {
		boolean result=false;
		result=RentalBookDao.rentalBook(book);
		return result;
		
	}

	public static String returnBook() {
		
		return RentalBookDao.returnBook();
	}

	public static int newPw(String pw, String nPw) {
		int result=UserDao.chagePw(pw, nPw);
		
		return result;
	}

	public static int addBook(String title, String ISBN, String publisher, String author) {
		int result=BookDao.addBook(title, ISBN, publisher, author);
		
		return result;
		
	}

	public static int bookDel(String ISBN) {
		int result=BookDao.bookDel(ISBN);
		return result;
	}

	public static void applyBooksSatus(int count, int select) {
		ApplyBookDao.applyBooksSatus(count, select);
		
	}


	// 도서 반납
	public static void returnBookView() {
		ReturnBookView.getReturnBookView();
	}
	
	public static ArrayList<RentalBookVo> getRentalBooks() {
		return ReturnBookDao.getRentalBooks();
	}
	
	public static Calendar getReturnDue(RentalBookVo b) {
		return ReturnBookDao.getReturnDue(b);
	}
	
	public static boolean checkValidation(ArrayList<RentalBookVo> list, int num) {
		return ReturnBookDao.checkValidation(list, num);
	}
	
	public static Calendar returnBook(ArrayList<RentalBookVo> list, int num) {
		return ReturnBookDao.returnBook(list, num);
	}
	
	// 도서 삭제
	public static void deleteBookView() {
		DeleteBookView.getDeleteBookView();
	}
	
	public static boolean checkBookExistence(String isbn) {
		return DeleteBookDao.checkBookExistence(isbn);
	}
	
	public static boolean checkBookRented(String isbn) {
		return DeleteBookDao.checkBookRented(isbn);
	}
	
	public static void deleteBook(String isbn) {
		DeleteBookDao.deleteBook(isbn);
	}

	

	public static void save() {
		MainView.save();
	}

}
