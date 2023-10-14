package com.project.library.model.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.project.library.model.vo.RefusalBookVo;

public class RefusalBookDao {

	static ArrayList<RefusalBookVo> list;
	
	static {
		list=new ArrayList<RefusalBookVo>();
	}
	
	
	public static void load() {
		
		try {
			
			String dir="data\\refusalBook.txt";
			
			BufferedReader read=new BufferedReader(new FileReader(dir));
			
			String line=null;
			
			while((line=read.readLine()) != null) {
				
				RefusalBookVo b=new RefusalBookVo();
				
				String[] temp=line.split(",");
				
				b.setUserNo(temp[0]);
				b.setISBN(temp[1]);
				b.setTitle(temp[2]);
				b.setAuthor(temp[3]);
				b.setPublisher(temp[4]);
				b.setPrice(temp[5]);
				b.setReason(temp[6]);
				
				list.add(b);
				
				
			}
			
			
			read.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
	}


	public static String reason(String ISBN) {
		String result="";
		for(RefusalBookVo b : list) {
			if(b.getISBN().equals(ISBN)) {
				result=b.getReason();
			}
		}
		
		
		return result;
	}
	
	
	
	
}
