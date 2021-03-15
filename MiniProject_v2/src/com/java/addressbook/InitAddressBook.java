package com.java.addressbook;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class InitAddressBook {
	static final String rootPath = System.getProperty("user.dir");
	static final String filename = rootPath + "\\addressBook.txt";
	
	private static List<Address> addressBook = new ArrayList<>();
	
	private static InitAddressBook initAddressBook = new InitAddressBook();
	
	private InitAddressBook() {
		// 초기 설정
		String[] initInfo = { "고길동,010-10**-23**,02-43**-90**",
				"도우너,010-55**-55**,02-43**-90**",
				"마이콜,010-99**-77**,02-43**-90**",
				"또치,010-88**-90**,02-43**-90**",
				"강민승,010-55**-65**,032-2**-65**" };
		
		File f = new File(filename);		// 파일 생성
		Writer writer = null;
		
		try {
			writer = new FileWriter(f);
			
			for(int i=0; i<initInfo.length; i++) {
				initList(initInfo[i]);		// 초기 정보 리스트 초기화
				writer.write(initInfo[i] + "\n");	// 생성 된 파일에 초기 정보 초기화
			}
			
			//내부 버퍼 비우기
			writer.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				writer.close();
			}
			catch(Exception e) {
				
			}
		}	
	}
	
	public static InitAddressBook getAddressBook() {		// 싱글톤 패턴 인스턴스 받아오는 메서드
		return initAddressBook;
	}
	
	public static List<Address> getAddressList() {			// 리스트 불러오기
		return addressBook;
	}
	
	public void initList(String s) {						// 초기 정보 리스트 초기화 메서드
		String[] ss = s.split(",");
		addressBook.add(new Address(ss[0], ss[1], ss[2]));
	}
}
