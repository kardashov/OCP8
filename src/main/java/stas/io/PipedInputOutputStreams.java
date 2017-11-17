package stas.io;

import java.io.*;
import java.util.Arrays;

public class PipedInputOutputStreams {

	public static void main(String[] args) {

		try {
			int countRead = 0;
			byte[] toRead = new byte[100];
			PipedInputStream pipeIn = new PipedInputStream();
			PipedOutputStream pipeOut = new PipedOutputStream(pipeIn);
			
			// —читывать в массив, пока он полностью не будет заполнен
			while (countRead < toRead.length) {

				// «аписать в pipeOut поток некоторое количество байт
				for (int i = 0; i < (Math.random() * 10); i++) {
					pipeOut.write((byte) (Math.random() * 127));
				}
				System.out.println(Arrays.toString(toRead));
				
				int willRead = pipeIn.available();// —читать из потока доступные данные, 
													//добавить их к уже считанным.
				if (willRead + countRead > toRead.length)// Ќужно считать только до предела массива
					willRead = toRead.length - countRead;
				
				countRead += pipeIn.read(toRead, countRead, willRead);
			}
		} catch (IOException e) {
			System.out.println("Impossible IOException occur: ");
			e.printStackTrace();
		}
	}

}
