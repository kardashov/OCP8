package stas.exceptions;

class PreciseRethrow {
    public static void main(String []str) {
                          try {
                                          foo();
                          }
                          catch(NumberFormatException ife) {
                                          System.out.println(ife);
                                          ife.printStackTrace();
                          }
    }
    
          static private void foo() throws NumberFormatException {
                          try {
                                          int i = Integer.parseInt("ten");
                          }
                          catch(Exception e) {
                        	  
                                          throw e;
                          }
    }}

