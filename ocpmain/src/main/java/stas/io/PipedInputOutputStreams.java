package stas.io;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

public class PipedInputOutputStreams {

    public static void main(String[] args) {

        try {
            int countRead = 0;
            byte[] toRead = new byte[100];
            PipedInputStream pipeIn = new PipedInputStream();
            PipedOutputStream pipeOut = new PipedOutputStream(pipeIn);

            // ��������� � ������, ���� �� ��������� �� ����� ��������
            while (countRead < toRead.length) {

                // �������� � pipeOut ����� ��������� ���������� ����
                for (int i = 0; i < (Math.random() * 10); i++) {
                    pipeOut.write((byte) (Math.random() * 127));
                }
                System.out.println(Arrays.toString(toRead));

                int willRead = pipeIn.available();// ������� �� ������ ��������� ������,
                //�������� �� � ��� ���������.
                if (willRead + countRead > toRead.length)// ����� ������� ������ �� ������� �������
                    willRead = toRead.length - countRead;

                countRead += pipeIn.read(toRead, countRead, willRead);
            }
        } catch (IOException e) {
            System.out.println("Impossible IOException occur: ");
            e.printStackTrace();
        }
    }

}
