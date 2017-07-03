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

      int choice = -1; // �޴����� ��ȣ�� �ʱⰪ ����

      choice = scan.nextInt(); //�޴���ȣ�� ������
      scan.nextLine(); //��� �޼ҵ忡 ���� �� ���� �� �νĵǱ⶧���� ���۸� ���⼭ �ذ�
      return choice; //�޴���ȣ�� ��ȯ
   }

   public void printMenu() {
      System.out.println("");
      System.out.println("����� �������ּ���");
      System.out.println("----------------------------------------");
      System.out.print("1. ���� ����Ʈ ����ϱ�   ");
      System.out.println("2. ���� �˻��ϱ� ");
      System.out.print("3. ���� �߰��ϱ�   ");
      System.out.print("4. ���� ����ϱ�   ");
      System.out.println("0. ���α׷� �����ϱ�   ");
      System.out.println("----------------------------------------");

   }

   public void printBooks() throws FileNotFoundException {

      BufferedReader br = new BufferedReader(new FileReader(filePath));

      System.out.println("å ����� ����մϴ�.");
      int count = 1;
      String str = "";
      //try catch�� ���� 
      try {
         while ((str = br.readLine()) != null) { //���ο� �ƹ��͵� ���� ������ ����
            System.out.println("(" + count + ")" + str); //����� ��ȣ�� ��Ÿ���� å�� �������
            count++;
         }
         br.close();
      } catch (IOException e) { //������ �߻� �� �����޼��� ���
         System.out.println("å ������ �о� �� �� �����ϴ�.");
         e.printStackTrace();
      }
      count--;
      System.out.println("�� ��� ������ " + count + "�� �Դϴ�."); //��� ������ ~�� �Դϴ� ���
   }

   public void searchBook() throws FileNotFoundException {
      int menuSelect;
      System.out.println("å �̸����� �˻� - 1"); //(�߰���ɵ�) å �̸����θ� �˻�, å ���ڷθ� �˻�, ���հ˻�
      System.out.println("���ڷ� �˻� - 2");
      System.out.println("���� �˻� - 3");

      menuSelect = scan.nextInt();

      switch (menuSelect) {
      case 1: //�̸��˻�
         searchByBookname();
         break;
      case 2: //���ڰ˻�
         searchByAuthor();
         break;
      case 3: //���հ˻�
         System.out.print("ã�� Ű���带 �Է����ּ���");
         scan.nextLine(); //�����ذ�
         String keyword = scan.nextLine(); //ã�� Ű���带 ����

         try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String str = "";
            while ((str = br.readLine()) != null) { //�ݺ����� ���� str���ڿ��� keyword�� ���ԵǾ������� �� ���ڿ� ���
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
         System.out.println("�ùٸ� ���� �Է����ּ���"); //�ùٸ������� �޴� ���� �� ���

      }

   }

   public void searchByBookname() throws FileNotFoundException {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      scan.nextLine();
      
      System.out.print("å�� �̸��� �Է����ּ��� : ");
      String bookName = "";
      bookName = scan.nextLine(); //å�� �̸��� ����
      int count = 0;
      String str = "";
      try {
         while ((str = br.readLine()) != null) { //�ݺ����� ���� ���ο� �ƹ��͵� ���������� ����
            StringTokenizer st1 = new StringTokenizer(str); //Tokenizer�� ������� ��
            String readName = st1.nextToken(); //Token�� readNamed�� ����
            if (readName.contains(bookName)) { /*readName�� ����ڰ� ���ϴ� bookName�� ���� 
                                       *�� ó�� �ݺ������� ���� line ���(str)*/
               System.out.println(str);
               count++; //�� ���� �˻��Ǿ������� ���� count
            }
         }
         System.out.println(count + "���� �˻� �Ǿ����ϴ�."); //count ��� 
         br.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
   }
   
   public void searchByAuthor() throws FileNotFoundException {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      scan.nextLine();
      //���ڷθ� �˻�
      System.out.print("������ �̸��� �Է����ּ��� : ");
      String authorName = "";
      authorName = scan.nextLine(); //����ڰ� ���ϴ� ������ �̸��� ����
      String str = "";
      int count = 0;
      
      try {
         while ((str = br.readLine()) != null) {
            StringTokenizer st1 = new StringTokenizer(str);
            String readName = st1.nextToken(); //name�� ���� �а� �� ����
            String readAuthor = st1.nextToken(); /*��ū�� �������ν� 
                                         *���ڰ� ������ ĭ�� �а� ��*/
            if (readAuthor.contains(authorName)) { /*����ڰ� ���ϴ� �̸��� 
                                            *���ԵǾ������� �� ���� ���*/
               System.out.println(str);
               count++; //��� �˻��Ǿ����������� count
            }
         }
         System.out.println(count + "���� �˻� �Ǿ����ϴ�."); //count ���
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
      System.out.println("�� �Է� ���ο� ���� YȤ�� N �� �Է����ּ���");
      reInput = scan.nextLine();
      switch (reInput) {
      case "Y":
         System.out.println("���Է��� �մϴ�.");
         addBook();
         break;

      case "N":
         saveBook(comeBook);
         System.out.println("�Է��Ͻ� �״�� �Է��Ͽ����ϴ�.");
         break;

      case "y":
         System.out.println("���Է��� �մϴ�.");
         addBook();
         break;

      case "n":
         saveBook(comeBook);
         System.out.println("�Է��Ͻ� �״�� �Է��Ͽ����ϴ� ");
         break;

      default:
         System.out.println("���� �ùٸ��� �ʾ� ���Է���ҷ� ����Ͽ����ϴ�.");
         break;

      }
   }

   
   public book inputBookInfo() throws IOException {
      book b = new book();
      /*try catch �� ���� book��ü�� setName�� �޴� �Ķ������ �ڷ����� 
       * string �ε� �ٸ� �ڷ������� ������ ����ó�� �ؼ� ����ڰ� �ٷ� �ٽ� 
       * �ùٸ� ���� �Է��Ҽ��ְ� �����Ͽ���*/
      try{
         System.out.print("å�̸� : "); 
         b.setName(scan.nextLine());
         System.out.print("�� �� : ");
         b.setAuthor(scan.nextLine());
         System.out.print("���ǻ� : ");
         b.setPublisher(scan.nextLine());
         System.out.print("�� �� : ");
         b.setCost(scan.nextInt());
         
      }catch(Exception e){
         System.out.println("�ùٸ� ���� �Է����ּ���");
         scan.nextLine();//���� ó��
         inputBookInfo(); 
      }
      System.out.println("å �̸�: "+b.getName()+" �� ��: "+b.getAuthor()+
               " ���ǻ�: "+b.getPublisher()+" �� ��: "+b.getCost());
      
      return b;
   }
   
   //�� ��ü�� ������ ����
   public void saveBook(book tmpBook) throws IOException {
      book b = tmpBook;//book ������ tmpBook�� �޾ƿ� b��ü�� ����

      //b��ü�� �������� write��
      BufferedWriter bw = new BufferedWriter(new FileWriter
                                   (filePath, true));
      bw.write(b.getName() + "\t" + b.getAuthor() + "\t" + b.getPublisher() + "\t" + b.getCost());
      bw.newLine(); 
      bw.close();

   }

   public void deleteBook() throws IOException {
      System.out.println("å�� �����մϴ�.");

      int count = 1;
      String tmpFilePath = filePath + ".tmp";

      //������ å�� ���ι�ȣ�� ����
      System.out.println("������ å ��ȣ�� �Է����ּ���: ");
      int deleteLineNumber = scan.nextInt(); 

      //����ڰ� ��ȣ Ȯ��
      System.out.println("�Է¹��� ��ȣ: " + deleteLineNumber); 

      BufferedWriter bw = new BufferedWriter(new FileWriter(tmpFilePath));
      BufferedReader br = new BufferedReader(new FileReader(filePath));

      String str = "";

      while ((str = br.readLine()) != null) { 
          //�ӽ� ���Ͽ� ���� �� ������ �ƴ� ������ å ������ write��
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
      //book.tmp���Ͽ� �ִ� å�� �������� �ҷ���
      while ((data = fis.read()) != -1) { 
         fos.write(data);
      }

      fis.close();
      fos.close();

      File f = new File(tmpFilePath); 
      f.delete(); //book.tmp ����

   }

}
