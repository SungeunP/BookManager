package bookManager;

import java.io.BufferedReader;
import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Engine {

   static Scanner scan = new Scanner(System.in);

   static String filePath = "C:\\test\\book.txt"; 


   public int inputNumber() {

      int choice = -1; // 메뉴선택 번호의 초기값 설정

      choice = scan.nextInt(); //메뉴번호를 저장함
      scan.nextLine(); //기능 메소드에 들어가서 한 줄이 더 인식되기때문에 버퍼를 여기서 해결
      return choice; //메뉴번호를 반환
   }

   public void printMenu() {
      System.out.println("");
      System.out.println("기능을 선택해주세요");
      System.out.println("----------------------------------------");
      System.out.print("1. 도서 리스트 출력하기   ");
      System.out.println("2. 도서 검색하기 ");
      System.out.print("3. 도서 추가하기   ");
      System.out.print("4. 도서 폐기하기   ");
      System.out.println("0. 프로그램 종료하기   ");
      System.out.println("----------------------------------------");

   }

   public void printBooks() throws FileNotFoundException {

      BufferedReader br = new BufferedReader(new FileReader(filePath));

      System.out.println("책 목록을 출력합니다.");
      int count = 1;
      String str = "";
      //try catch로 구현 
      try {
         while ((str = br.readLine()) != null) { //라인에 아무것도 업을 때까지 읽음
            System.out.println("(" + count + ")" + str); //목록의 번호를 나타내고 책의 정보출력
            count++;
         }
         br.close();
      } catch (IOException e) { //오류가 발생 시 오류메세지 출력
         System.out.println("책 정보를 읽어 올 수 없습니다.");
         e.printStackTrace();
      }
      count--;
      System.out.println("총 출력 개수는 " + count + "개 입니다."); //출력 개수는 ~개 입니다 출력
   }

   public void searchBook() throws FileNotFoundException {
      int menuSelect;
      System.out.println("책 이름으로 검색 - 1"); //(추가기능들) 책 이름으로만 검색, 책 저자로만 검색, 통합검색
      System.out.println("저자로 검색 - 2");
      System.out.println("통합 검색 - 3");

      menuSelect = scan.nextInt();

      switch (menuSelect) {
      case 1: //이름검색
         searchByBookname();
         break;
      case 2: //저자검색
         searchByAuthor();
         break;
      case 3: //통합검색
         System.out.print("찾을 키워드를 입력해주세요");
         scan.nextLine(); //버퍼해결
         String keyword = scan.nextLine(); //찾을 키워드를 받음

         try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String str = "";
            while ((str = br.readLine()) != null) { //반복문을 돌며 str문자열에 keyword가 포함되어있으면 그 문자열 출력
               if (str.contains(keyword)) {
                  System.out.println(str);

               }

            }
            br.close();
         } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         break;
      default:
         System.out.println("올바른 값을 입력해주세요"); //올바르지않은 메뉴 선택 시 출력

      }

   }

   public void searchByBookname() throws FileNotFoundException {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      scan.nextLine();
      
      System.out.print("책의 이름을 입력해주세요 : ");
      String bookName = "";
      bookName = scan.nextLine(); //책의 이름을 받음
      int count = 0;
      String str = "";
      try {
         while ((str = br.readLine()) != null) { //반복문을 돌며 라인에 아무것도 없을때까지 읽음
            StringTokenizer st1 = new StringTokenizer(str); //Tokenizer로 만들어준 뒤
            String readName = st1.nextToken(); //Token을 readNamed에 저장
            if (readName.contains(bookName)) { /*readName에 사용자가 원하는 bookName이 있을 
                                       *시 처음 반복문에서 읽은 line 출력(str)*/
               System.out.println(str);
               count++; //몇 개가 검색되었는지에 대한 count
            }
         }
         System.out.println(count + "개가 검색 되었습니다."); //count 출력 
         br.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }
   
   public void searchByAuthor() throws FileNotFoundException {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      scan.nextLine();
      //저자로만 검색
      System.out.print("저자의 이름을 입력해주세요 : ");
      String authorName = "";
      authorName = scan.nextLine(); //사용자가 원하는 저자의 이름을 받음
      String str = "";
      int count = 0;
      
      try {
         while ((str = br.readLine()) != null) {
            StringTokenizer st1 = new StringTokenizer(str);
            String readName = st1.nextToken(); //name을 먼저 읽고 그 다음
            String readAuthor = st1.nextToken(); /*토큰을 읽음으로써 
                                         *저자가 쓰여진 칸을 읽게 됨*/
            if (readAuthor.contains(authorName)) { /*사용자가 원하는 이름이 
                                            *포함되어있으면 그 라인 출력*/
               System.out.println(str);
               count++; //몇개가 검색되었는지에대한 count
            }
         }
         System.out.println(count + "개가 검색 되었습니다."); //count 출력
         br.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

   

   public void addBook() throws IOException {
      String reInput;
      book comeBook = new book();

      comeBook = inputBookInfo();
      
      scan.nextLine();
      System.out.println("재 입력 여부에 따라 Y혹은 N 을 입력해주세요");
      reInput = scan.nextLine();
      switch (reInput) {
      case "Y":
         System.out.println("재입력을 합니다.");
         addBook();
         break;

      case "N":
         saveBook(comeBook);
         System.out.println("입력하신 그대로 입력하였습니다.");
         break;

      case "y":
         System.out.println("재입력을 합니다.");
         addBook();
         break;

      case "n":
         saveBook(comeBook);
         System.out.println("입력하신 그대로 입력하였습니다 ");
         break;

      default:
         System.out.println("값이 올바르지 않아 재입력취소로 취급하였습니다.");
         break;

      }
   }

   
   public book inputBookInfo() throws IOException {
      book b = new book();
      /*try catch 로 구현 book객체의 setName이 받는 파라미터의 자료형이 
       * string 인데 다른 자료형으로 받으면 예외처리 해서 사용자가 바로 다시 
       * 올바른 값을 입력할수있게 유도하였음*/
      try{
         System.out.print("책이름 : "); 
         b.setName(scan.nextLine());
         System.out.print("저 자 : ");
         b.setAuthor(scan.nextLine());
         System.out.print("출판사 : ");
         b.setPublisher(scan.nextLine());
         System.out.print("가 격 : ");
         b.setCost(scan.nextInt());
         
      }catch(Exception e){
         System.out.println("올바른 값을 입력해주세요");
         scan.nextLine();//버퍼 처리
         inputBookInfo(); 
      }
      System.out.println("책 이름: "+b.getName()+" 저 자: "+b.getAuthor()+
               " 출판사: "+b.getPublisher()+" 가 격: "+b.getCost());
      
      return b;
   }
   
   //북 객체의 정보를 저장
   public void saveBook(book tmpBook) throws IOException {
      book b = tmpBook;//book 형태의 tmpBook을 받아와 b객체에 저장

      //b객체의 정보들을 write함
      BufferedWriter bw = new BufferedWriter(new FileWriter
                                   (filePath, true));
      bw.write(b.getName() + "\t" + b.getAuthor() + "\t" + b.getPublisher() + "\t" + b.getCost());
      bw.newLine(); 
      bw.close();

   }

   public void deleteBook() throws IOException {
      System.out.println("책을 삭제합니다.");

      int count = 1;
      String tmpFilePath = filePath + ".tmp";

      //삭제할 책의 라인번호를 받음
      System.out.println("삭제할 책 번호를 입력해주세요: ");
      int deleteLineNumber = scan.nextInt(); 

      //사용자가 번호 확인
      System.out.println("입력받은 번호: " + deleteLineNumber); 

      BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));
      BufferedReader br = new BufferedReader(new FileReader(filePath));

      String str = "";

      while ((str = br.readLine()) != null) { 
          //임시 파일에 삭제 할 라인이 아닌 라인의 책 정보만 write함
         if (count != deleteLineNumber) {
            bw.write(str);
            bw.write("\r\n");
         }
         count++;
         
      }
      
      bw.close();
      br.close();

      FileInputStream fis = new FileInputStream(tmpFilePath);
      FileOutputStream fos = new FileOutputStream(filePath);

      int data = 0;
      //book.tmp파일에 있는 책의 정보들을 불러옴
      while ((data = fis.read()) != -1) { 
         fos.write(data);
      }

      fis.close();
      fos.close();

      File f = new File(tmpFilePath); 
      f.delete(); //book.tmp 삭제

   }

}
