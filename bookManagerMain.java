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
			System.out.println("���α׷��� �����մϴ�.");
			System.exit(0);
			break;
		default:
			System.out.println("�ٽ� �Է����ּ���");
		}

	}

	private static int iuputData() {
		int choice = -1;

		
		System.out.println("�����ϼ���: ");
		choice = scan.nextInt(); // ���� int�� �ڷḦ �Է¹���

		System.out.println("������ �޴�: " + choice);

		System.out.println("");
		return choice;
	}

	private static void printMenu() {
		System.out.println("-�������� ���α׷�-");
		System.out.println("1. ��ü ��� ���");
		System.out.println("2. ���� �˻�");
		System.out.println("3. �ű� ���� �߰�");
		System.out.println("4. ���� ���� �б�");
		System.out.println("0. ���α׷� ����");
	}

	private static void printBooks() {
		System.out.println("��������� ����մϴ�.");

	}

	private static void searchBooks() {
		System.out.println("å�� �˻��մϴ�.");
		
	}

	private static void addBook() throws IOException {
		System.out.println("������ �Է����ּ���");
		
		scan.nextLine(); // ���� printmenu ���� ���� nextLine �� addBook���� �� �� �д°ɷ� �Ǿ� name �Է�â�� �پ�Ѵ� �����ذ�
		
		System.out.println("å �̸�: ");
		String name =scan.nextLine();
		
		System.out.println("�� ��: ");
		String author = scan.nextLine();
		
		System.out.println("���ǻ�: ");
		String publisher = scan.nextLine();
		
		System.out.println("�� ��: ");
		String cost = scan.nextLine();
		
		System.out.println("�Էµ� ���� �Դϴ�.");
		System.out.println("å �̸�: "+name+" ����: "+author+" ���ǻ�: "+publisher+" �� ��: "+cost);
		
		String filePath = "c:\\testFile\\book.txt";
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath,true)); 
		
		bw.write(name+"\t"+author+"\t"+publisher+"\t"+cost);
		bw.newLine();
		bw.close();
	}

	private static void deleteBook() {
		System.out.println("å�� �����մϴ�.");

	}

}
