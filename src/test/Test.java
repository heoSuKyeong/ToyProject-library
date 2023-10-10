package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

import com.project.library.model.vo.BookVo;

public class Test {

	public static void main(String[] args) {
		
		
		ArrayList<String> list = new ArrayList<String>();
		
		String s1="ssss";
		
		System.out.println(s1.length());
		
		//---
		
		String s2="asadf|2222|ssss|";
		
		String[] sa=s2.split("\\|");
		
		System.out.println(sa[0]+" "+sa[1]);
 		
		
		
		//---
		
		Random rnd=new Random();
		int temp=rnd.nextInt(700)+100;
		
		System.out.println(temp);
		
		String author="안녕아";
		
		String name=author.substring(0,1);
		
		System.out.println(name);
//		ArrayList<BookVo> fBook=new ArrayList<BookVo>();
//		
//		String dir=".\\data\\falseBook.txt";
//		
//		try {
//			
//			BufferedReader reader=new BufferedReader(new FileReader(dir));
//			
//			String line=null;
//			
//			while((line=reader.readLine()) != null){
//				
//				String[] temp=line.split("|");
//				
//				BookVo b=new BookVo();
//				
//				b.setISBN(temp[0]);
//				b.setTitle(temp[1]);
//				b.setAuthor(temp[2]);
//				b.setPublisher(temp[3]);
//				b.setLocation(temp[4]);
//				
//				System.out.println(temp[0] +" "+ temp[1]);
//				
////				fBook.add(b);
//				
//			}
//			
//			reader.close();
//			
//			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	
		
		
		//---
		
		
		String s3="홍길동 안녕하세오료오";
		
		System.out.println(s3.contains("안ㄴ녕")); 
		
		
		
		ArrayList<int[]> li1 =new ArrayList<int[]>();
		
		int[] r1= {1,2,3};
		int[] r2= {4,5,6};
		int[] r3= {7,8,9};
		
		li1.add(r1);
		li1.add(r2);
		li1.add(r3);
		
	
		
		
		
		
		
	}
}
