package bookManager;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class bookManagerMain {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {

		printMenu();

		int choice = iuputData();

		switch (choice) {

		case 1:
			printBooks();
			break;
		case 2:
			searchBooks();
			break;
		case 3:
			addBook();
			break;
		case 4:
			deleteBook();
			break;
		case 0:
			System.out.println("프로그램을 종료합니다.");
			System.exit(0);
			break;
		default:
			System.out.println("다시 입력해주세요");
		}

	}

	private static int iuputData() {
		int choice = -1;

		
		System.out.println("선택하세요: ");
		choice = scan.nextInt(); // 다음 int형 자료를 입력받음

		System.out.println("선택한 메뉴: " + choice);

		System.out.println("");
		return choice;
	}

	private static void printMenu() {
		System.out.println("-도서관리 프로그램-");
		System.out.println("1. 전체 목록 출력");
		System.out.println("2. 도서 검색");
		System.out.println("3. 신규 도서 추가");
		System.out.println("4. 노후 도서 패기");
		System.out.println("0. 프로그램 종료");
	}

	private static void printBooks() {
		System.out.println("도서목록을 출력합니다.");

	}

	private static void searchBooks() {
		System.out.println("책을 검색합니다.");
		
	}

	private static void addBook() throws IOException {
		System.out.println("정보를 입력해주세요");
		
		scan.nextLine(); // 이전 printmenu 에서 사용된 nextLine 이 addBook에서 한 줄 읽는걸로 되어 name 입력창을 뛰어넘는 문제해결
		
		System.out.println("책 이름: ");
		String name =scan.nextLine();
		
		System.out.println("저 자: ");
		String author = scan.nextLine();
		
		System.out.println("출판사: ");
		String publisher = scan.nextLine();
		
		System.out.println("가 격: ");
		String cost = scan.nextLine();
		
		System.out.println("입력된 값들 입니다.");
		System.out.println("책 이름: "+name+" 저자: "+author+" 출판사: "+publisher+" 가 격: "+cost);
		
		String filePath = "c:\\testFile\\book.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true)); 
		
		bw.write(name+"\t"+author+"\t"+publisher+"\t"+cost);
		bw.newLine();
		bw.close();
	}

	private static void deleteBook() {
		System.out.println("책을 삭제합니다.");

	}

}
