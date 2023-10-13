package com.project.library.model.vo;

public class OverdueBookVo {

	private String userNo;
	private int rentalNo;
	private String delinquencyStartDate;
	private String delinquencyEndDate;
	
	public OverdueBookVo(String userNo, int rentalNo, String delinquencyStartDate, String delinquencyEndDate) {
		super();
		this.userNo = userNo;
		this.rentalNo = rentalNo;
		this.delinquencyStartDate = delinquencyStartDate;
		this.delinquencyEndDate = delinquencyEndDate;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public int getRentalNo() {
		return rentalNo;
	}

	public void setRentalNo(int rentalNo) {
		this.rentalNo = rentalNo;
	}

	public String getDelinquencyStartDate() {
		return delinquencyStartDate;
	}

	public void setDelinquencyStartDate(String delinquencyStartDate) {
		this.delinquencyStartDate = delinquencyStartDate;
	}

	public String getDelinquencyEndDate() {
		return delinquencyEndDate;
	}

	public void setDelinquencyEndDate(String delinquencyEndDate) {
		this.delinquencyEndDate = delinquencyEndDate;
	}

	@Override
	public String toString() {
		return "OverdueBookVo [userNo=" + userNo + ", rentalNo=" + rentalNo + ", delinquencyStartDate="
				+ delinquencyStartDate + ", delinquencyEndDate=" + delinquencyEndDate + "]";
	}
}
