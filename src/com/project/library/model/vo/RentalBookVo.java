package com.project.library.model.vo;

public class RentalBookVo {

//	1,U100,9791192925004,2023-07-14,2023-07-20,Y
	
//	대여번호
//	회원코드(userNo)
//	도서명(title)
//	ISBN(ISBN)
//	대여날짜(rentalDate)
//	반납날짜(returnDate)
//	반납여부(returnFlag)

	private int num;
	private String userNo;
	private String title;
	private String ISBN;
	private String rentalDate;
	private String returnDate;
	private String returnFlag;
	
	public RentalBookVo() {
		// TODO Auto-generated constructor stub
	}

	public RentalBookVo(int num, String userNo, String title, String iSBN, String rentalDate, String returnDate,
			String returnFlag) {
		super();
		this.num = num;
		this.userNo = userNo;
		this.title = title;
		ISBN = iSBN;
		this.rentalDate = rentalDate;
		this.returnDate = returnDate;
		this.returnFlag = returnFlag;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getRentalDate() {
		return rentalDate;
	}

	public void setRentalDate(String rentalDate) {
		this.rentalDate = rentalDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getReturnFlag() {
		return returnFlag;
	}

	public void setReturnFlag(String returnFlag) {
		this.returnFlag = returnFlag;
	}

	@Override
	public String toString() {
		return "RentalBookVo [num=" + num + ", userNo=" + userNo + ", title=" + title + ", ISBN=" + ISBN
				+ ", rentalDate=" + rentalDate + ", returnDate=" + returnDate + ", returnFlag=" + returnFlag + "]";
	}

	
	
	

}
