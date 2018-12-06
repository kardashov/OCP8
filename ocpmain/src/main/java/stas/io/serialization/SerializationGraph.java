package stas.io.serialization;

import java.io.*;
//���� ������������

/*�� ����� �� ������������� �������, ������� ����� ���� ���� ����������� �����.
���� �� ������������� ������ ��������� �� ������ �������, �� ����� ���������� 
��������� (�������� � ����� ����), � ��� �������������� � ������������.
��� �������, � ���� �������, ����� ����� ��������� �� ��������� �������.
��� ���� �����, ��� ���� ��������� ������ ��������� �� ���� � ��� �� ������,
�� ���� ������ ������ ���� ������������ ���� �������, � ��� �������������� ��� 
������ ������ ����� ��������� �� ���� ������. ��������, ������������� ������ A 
��������� �� ������� B � C, ������ �� �������, � ���� �������, ��������� �� 
���� � ��� �� ������ D. ����� �������������� �� ������ ��������� ��������, 
����� B ��������� �� D1, � C � �� D2, ��� D1 � D2 � ������, �� ��� �� ��������� �������.

��� ����������� ������ �������� ����������� �������� ������������ ������ ����,
���������� � ���� ��� ����������� ������� � ������ ����� ����. ���� ��������� 
������ ��������� �� ��������� ������, ������� ����������� � ��� �� ������ ������� � �����. 
���� ���� � ������ ������ ��� �� �������������. ���� ��� � ����� ������ ����������� � ����.

��� ���������� ����� ����� ����������� ������, ����������� �� ������,
�� ������������ ��������� Serializable. � ���� ������ ������������ �����������,
������������ ���������� java.io.NotSerializableException.*/



/* http://old.intuit.ru/department/pl/javapl/15/3.html

 0. transient � static ���� �� ����������� � �� �����������������;
 1. ������������� ������ �������, ����������� ���. Serializable (�����
 Exception);
 2. �������� Serializable ���������� �� ���������� (���� ����);
 3. ��� Serializable �������� ������������ ���������� ����������
 ���������� JVM;
 4. ���� ���� ���� � not Serializable ������� � ��������, �� �����
 ������ ������ ����������� ����� ����������� ��� ����������, � ���
 ������� ����� ������� ����� ������� �� ����� �������������� (����
 ����� ������� �� �������������);

 5. ���������� ����������� ������������
 ��������� ������ �������������� ������� ��������� ������ ������ ���
 ������������. ��� ���������� ������������ ��������� ����� �������� �
 ������ ��� ������ � ����� ����� ����������:

 private void writeObject(java.io.ObjectOutputStream out)  
 	throws IOException;
 private void readObject(java.io.ObjectInputStream in) 
 	throws IOException, ClassNotFoundException;

 6.���� �� ��������� ������������ � ����� ���������� �� �����������,
 ��
 ��� ����� ������� ������������ �������������� ���������
 java.io.Externalizable.

 ��� ������������� ����� ���������� � ����� ������������� ������������
 ������ ������������� ������. ��������� � ������������ ��� ����������
 � ��������� ���������� ������ ��� �����. ��� ����� � ��� ������ ����
 ��������� ������ writeExternal() � readExternal() ����������
 Externalizable. ��� ������ ������ ���������� ���������� ���������,
 ������������ ������ ������ ������ � ��� �����������.

 ��� �������������� Externalizable -������� ��������� ��������� �����
 ������ ������������ ��� ����������, ����� ���� ���������� �����
 readExternal.

 ����� writeExternal ����� ���������:

 void writeExternal(ObjectOutput out)
 throws IOException;
 ��� ���������� ��������� ���������� ������ ObjectOutput, � �������
 ������� ����� �������� ��� �����������, ��� � ��������� ��������. ���
 ���������� ������ � ��������������� ������

 void readExternal(ObjectInput in)
 throws IOException,ClassNotFoundException;
 ��� �������� ������ ���� ������� � ��� �� ����� �������.


7. ��� ������� ��������, ���������� ������ �������, �������� ���� ������������.
���� ��� ���� ���������� ��������������� ������ */

class Point implements Serializable {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + "," + y + ") reference=" + super.toString();
    }
}

class Line implements Serializable {
    Point point1;
    Point point2;
    int index;

    public Line() {
        System.out.println("Constructing empty line");
    }

    Line(Point p1, Point p2, int index) {
        System.out.println("Constructing line: " + index);
        this.point1 = p1;
        this.point2 = p2;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int newIndex) {
        index = newIndex;
    }

    public void printInfo() {
        System.out.println("Line: " + index);
        System.out.println(" Object reference: " + super.toString());
        System.out.println(" from point " + point1);
        System.out.println(" to point " + point2);
    }
}

public class SerializationGraph {

    public static void main(java.lang.String[] args) {
        Point p1 = new Point(1.0, 1.0);
        Point p2 = new Point(2.0, 2.0);
        Point p3 = new Point(3.0, 3.0);
        Line line1 = new Line(p1, p2, 1);
        Line line2 = new Line(p2, p3, 2);
        System.out.println("line 1 = " + line1);
        System.out.println("line 2 = " + line2);
        String fileName = "//home//st//serialized.data";
        try {
            // ���������� ������� � ����
            FileOutputStream os = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(line1);
            oos.writeObject(line2);
            // ������ ��������� line1 � ���������� ��� ��� ���
            line1.setIndex(3);
            // oos.reset();   		// ���������, ��� ����� ������������ ��������!!!!!!!!!!!!
            // � ����� ���������� ���������� �������
            oos.writeObject(line1);
            // ��������� ������
            // ���������� ������� ������ �����-����������
            oos.close();
            // ��������� �������
            System.out.println("Read objects:");
            FileInputStream is = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(is);
            for (int i = 0; i < 3; i++) { // ��������� 3 �������
                Line line = (Line) ois.readObject();
                line.printInfo();
            }
            ois.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}