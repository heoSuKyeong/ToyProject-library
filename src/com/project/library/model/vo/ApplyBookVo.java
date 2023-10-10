package com.project.library.model.vo;

public class ApplyBookVo {
	private String userNo;
	private String applyDate;
	private String ISBN;
	private String title;
	private String author;
	private String publisher;
	private String price;
	private int approval;
	
	public ApplyBookVo() {
		// TODO Auto-generated constructor stub
	}

	public ApplyBookVo(String userNo, String applyDate, String iSBN, String title, String author, String publisher,
			String price, int approval) {
		super();
		this.userNo = userNo;
		this.applyDate = applyDate;
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.approval = approval;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
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

	public int getApproval() {
		return approval;
	}

	public void setApproval(int approval) {
		this.approval = approval;
	}

	@Override
	public String toString() {
		return "ApplyBookVo [userNo=" + userNo + ", applyDate=" + applyDate + ", ISBN=" + ISBN + ", title=" + title
				+ ", author=" + author + ", publisher=" + publisher + ", price=" + price + ", approval=" + approval
				+ "]";
	}

	
	
}
