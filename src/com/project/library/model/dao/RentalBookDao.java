package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.library.model.vo.RentalBookVo;

public class RentalBookDao { // 나의 대출 이력 조회
	
	public static ArrayList<RentalBookVo> rbs;
	
	static {
		
		rbs = new ArrayList<RentalBookVo>();
	}
	
	public static void load() {
		
		String dir = "data\\rentalbook.txt";
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(dir));

			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split("\\|");
				
				RentalBookVo rb = new RentalBookVo();
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
	
	public static void save() {
		
		try {
					
			String path = String.format("data\\rentalBook.txt");
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (RentalBookVo b : RentalBookDao.rbs) {
				
				writer.write(String.format("%s|%s|%s|%s|%s|%s\n",
										    b.getNum(), b.getUserNo(), b.getISBN(), b.getRentalDate(), b.getReturnDate(), b.getReturnFlag()));
						
			}
					
			writer.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}

	}

}
