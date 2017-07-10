package stas.misc;

import java.io.IOException;
import java.sql.SQLDataException;

public class ExceptionInitializationBlocks {

	public int a;
	public int b;
	public static void main(String[] args) {
		
	}
	
	public void method1() throws IOException {
		
		
	}
	
	public ExceptionInitializationBlocks() throws IOException, SQLDataException {
		// TODO Auto-generated constructor stub
		this.a = 0;

	}

	public ExceptionInitializationBlocks(int c) throws IOException,
			SQLDataException {
		// TODO Auto-generated constructor stub
		this.a = c;
	}

	{
		this.b = 0;
		if (b > 0)
			throw new SQLDataException();
	}

	{
		this.a = 0;
		if (a > 0)
			throw new IOException();
	}

}

interface ffff {
	
	
};


