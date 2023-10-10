package com.project.library.model.vo;

public class BookVo {

	private String ISBN;
	private String title;
	private String author;
	private String publisher;
	private String location;
	
	public BookVo() {
	}

	public BookVo(String iSBN, String title, String author, String publisher, String location) {
		super();
		ISBN = iSBN;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.location = location;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "BookVo [ISBN=" + ISBN + ", title=" + title + ", author=" + author + ", publisher=" + publisher
				+ ", location=" + location + "]";
	}
	
	
	
	
}
