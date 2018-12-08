package stas.exceptions;

import java.io.IOException;

class FileFormatException extends IOException
{

public FileFormatException() {}
   public FileFormatException(String gripe)
   {
      super(gripe);
      try {
	
	} catch (RuntimeException e) {
		// TODO: handle exception 
	}
   
   }
   
   
}
