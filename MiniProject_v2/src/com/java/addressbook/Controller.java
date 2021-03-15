package com.java.addressbook;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Controller {
	static final String rootPath = System.getProperty("user.dir");	
	static final String source = rootPath + "\\addressBook.txt";
	
	InitAddressBook initAddressBook = InitAddressBook.getAddressBook();
	private static List<Address> addressList = InitAddressBook.getAddressList();
	
	Scanner s = new Scanner(System.in);
	
	private String name = null;			// 등록 시 등록할 데이터의 이름을 입력받을 변수
	private String hp = null;			// 등록 시 등록할 데이터의 휴대전화 번호를 입력받을 변수
	private String tel = null;			// 등록 시 등록할 데이터의 집전화를 입력받을 변수
	private int n = 0;					// 삭제 시 삭제할 데이터 위치를 입력받을 변수
	private String sname = null;		// 검색 시 검색할 이름을 입력받을 변수
	
	public void printList() {
		// 1번 기능 리스트
		addressList.clear();
		
		FileReader fr = null;
		BufferedReader br = null;
		
		String line = null;
		String[] splitLine = null;
		try {
			fr = new FileReader(source);
			br = new BufferedReader(fr);
			
			while((line=br.readLine()) != null) {		// txt 파일에 저장된 컨텐츠를 라인단위로 받음
				splitLine = line.split(",");			// 라인단위로 나눈 것을 ,를 기준으로 나누어 배열에 저장 
				addressList.add(new Address(splitLine[0], splitLine[1], splitLine[2]));
			}
			
		}
		catch(FileNotFoundException e) {
			System.err.println("파일 탐색 실패!");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				fr.close();
				br.close();
			}
			catch(Exception e) {
				
			}
		}
		
		for(int i=0; i<addressList.size(); i++) {
			System.out.printf("%d. %s %s %s%n", i+1, addressList.get(i).getName(), 
					addressList.get(i).getHp(), addressList.get(i).getTel());
		}
		System.out.println();
	}
	
	public void registerAddress() {
		// 2번 기능 등록		
		System.out.print("> 이름 : ");
		name =  s.nextLine();
		System.out.print("> 휴대전화 : ");
		hp =  s.nextLine();
		System.out.print("> 집전화 : ");
		tel =  s.nextLine();
		System.out.println();
		
		addressList.add(new Address(name, hp, tel));
		
		String input = name + "," + hp +"," + tel;
		
		FileWriter writer = null;
		
		try {
			writer = new FileWriter(source, true);
			writer.write(input + "\n");
			
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
		
		//s.close();		왜? NoSuchElementException
	}
	
	public void deleteAddress() {
		// 3번 기능 삭제
		System.out.print("> 번호 : ");
		n =  s.nextInt();
		
		FileWriter writer = null;
		try {
			addressList.remove(n-1);
			System.out.println("\n[삭제되었습니다.]\n");
			
			writer = new FileWriter(source);
			for(int i=0; i<addressList.size(); i++) {
				String line = addressList.get(i).getName()+","+addressList.get(i).getHp()+","+addressList.get(i).getTel();
				writer.write(line + "\n");
			}
		}
		catch(IndexOutOfBoundsException e) {
			System.err.println("해당 번호의 내용이 없습니다.\n");
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
	
	public void searchAddress() {
		// 4번 기능 검색
		System.out.print("> 이름 : ");
		sname =  s.nextLine();
		
		boolean tof = false;
		
		for(int i=0; i<addressList.size(); i++) {
			if(addressList.get(i).getName().contains(sname)) {
				System.out.printf("%d. %s %s %s%n", i+1, addressList.get(i).getName(), 
						addressList.get(i).getHp(), addressList.get(i).getTel());
				tof = true;
			}
		}
		System.out.println();
		
		if(tof == false)
			System.out.println("검색 결과가 없습니다.\n");
	}
}
