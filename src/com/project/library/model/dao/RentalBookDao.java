package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.project.library.model.vo.RentalBookVo;


public class RentalBookDao { // 나의 대출 이력 조회
	
	

	public static ArrayList<RentalBooksVo> rbs;
	
	static {
		
		rbs=new ArrayList<RentalBooksVo>();
	}
	
	
	public static void load() {
		
		String dir="data\\rentalbook.txt";
		
		try {
			BufferedReader reader=new BufferedReader(new FileReader(dir));

			String line=null;
			
			while((line=reader.readLine()) != null) {
				String[] temp=line.split(",");
				
				RentalBooksVo rb=new RentalBooksVo();
//				1,U100,9791192925004,2023-07-14,2023-07-20,Y
				rb.setNum(Integer.parseInt(temp[0]));
				rb.setUserNo(temp[1]);
				rb.setISBN(temp[2]);
				rb.setRentalDate(temp[3]);
				rb.setReturnDate(temp[4]);
				rb.setReturnFlag(temp[5]);
				
				rbs.add(rb);
			}
			
			reader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


	
}
