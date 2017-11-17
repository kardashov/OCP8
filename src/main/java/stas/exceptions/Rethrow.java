package stas.exceptions;
import java.io.*;
public class Rethrow {

   public Rethrow() {
   }
   public static void main(String[] args) {
      Rethrow test = new Rethrow();
      try {
         test.doFileInput("bogus.file");
      }
      catch (IOException ex) {
         System.out.println("Second exception handle stack trace");
         ex.printStackTrace();
      }
   }

//   Так как при вторичном возбуждении используется один и тот же объект Exception,
//   стек в обоих случаях будет содержать одну и ту же последовательность вызовов. 
//   То есть при повторном возбуждении исключения,
//   если мы используем тот же объект, изменения его параметров не происходит.
   private String doFileInput(String fileName) 
         throws FileNotFoundException,IOException {
      String retStr = "";
      java.io.FileInputStream fis = null;
      try {
         fis = new java.io.FileInputStream(fileName);
      }
      catch (FileNotFoundException ex) {
         System.out.println("First exception handle stack trace");
         ex.printStackTrace();
         throw ex;
      }
      return retStr;
   }
}


