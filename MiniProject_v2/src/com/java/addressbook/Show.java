package com.java.addressbook;

import java.util.Scanner;

public class Show {
	private Controller c = new Controller();
	
	private int n = 0;
	
	public void show() {
		System.out.println("************************************");
		System.out.println("*	   전화번호 관리 프로그램	   *");
		System.out.println("************************************");
		
		Scanner s = new Scanner(System.in);
		for(;;) {
			System.out.println("1. 리스트  2. 등록  3. 삭제  4. 검색  5. 종료");
			System.out.println("------------------------------------");
			System.out.print("메뉴번호> ");
			n = s.nextInt();
			
			switch(n) {
			case 1:
				// 리스트
				System.out.println("<1. 리스트>");
				c.printList();
				continue;
			case 2:
				// 등록
				System.out.println("<2. 등록>");
				c.registerAddress();
				continue;
			case 3:
				// 삭제
				System.out.println("<3. 삭제>");
				c.deleteAddress();
				continue;
			case 4:
				// 검색
				System.out.println("<4. 검색>");
				c.searchAddress();
				continue;
			case 5:
				// 종료
				System.out.println();
				System.out.println("************************************");
				System.out.println("*	      감사합니다.		   *");
				System.out.println("************************************");
				break;
			default:
				System.out.println("[다시 입력해 주세요.]");
				continue;
			}
			
			if(n == 5) break;
			s.close();
		}
	}
}
