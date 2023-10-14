package com.project.library.model.vo;

public class RefusalBookVo {

	private String userNo;
	private String ISBN;
	private String title;
	private String author;
	private String publisher;
	private String price;
	private String reason;
	
	public RefusalBookVo() {
	}

	public RefusalBookVo(String userNo, String iSBN, String title, String author, String publisher, String price,
			String reason) {
		super();
		this.userNo = userNo;
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.reason = reason;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String toString() {
		return "RefusalBookVo [userNo=" + userNo + ", ISBN=" + ISBN + ", title=" + title + ", author=" + author
				+ ", publisher=" + publisher + ", price=" + price + ", reason=" + reason + ", getUserNo()="
				+ getUserNo() + ", getISBN()=" + getISBN() + ", getTitle()=" + getTitle() + ", getAuthor()="
				+ getAuthor() + ", getPublisher()=" + getPublisher() + ", getPrice()=" + getPrice() + ", getReason()="
				+ getReason() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
