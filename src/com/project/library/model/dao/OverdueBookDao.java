package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import com.project.library.model.vo.OverdueBookVo;

public class OverdueBookDao {

public static ArrayList<OverdueBookVo> list;
	
	static {
		
		list = new ArrayList<OverdueBookVo>();
	}
	
	static String path = "data\\overdueBook.txt";
	
	public static void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				
				String[] temp = line.split(",");
				
				OverdueBookVo o = new OverdueBookVo(temp[0], Integer.parseInt(temp[1]), temp[2], temp[3]);
				
				OverdueBookDao.list.add(o);
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("at OverdueBookDao.load");
			e.printStackTrace();
		}
		
	}//load
	
	public static void save() {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			
			for (OverdueBookVo o : list) {
				
				writer.write(String.format("%s,%d,%s,%s\r\n"
										, o.getUserNo()
										, o.getRentalNo()
										, o.getDelinquencyStartDate()
										, o.getDelinquencyEndDate()));
			}
			
			writer.close();
			
		} catch (Exception e) {
			System.out.println("at OverdueBookDao.save");
			e.printStackTrace();
		}
		
		
		
	}//save
	
}
