package stas.io.serialization;
import java.io.*;
								//Граф сериализации

/*До этого мы рассматривали объекты, которые имеют поля лишь примитивных типов.
Если же сериализуемый объект ссылается на другие объекты, их также необходимо 
сохранить (записать в поток байт), а при десериализации – восстановить.
Эти объекты, в свою очередь, также могут ссылаться на следующие объекты.
При этом важно, что если несколько ссылок указывают на один и тот же объект,
то этот объект должен быть сериализован лишь однажды, а при восстановлении все 
ссылки должны вновь указывать на него одного. Например, сериализуемый объект A 
ссылается на объекты B и C, каждый из которых, в свою очередь, ссылается на 
один и тот же объект D. После десериализации не должно возникать ситуации, 
когда B ссылается на D1, а C – на D2, где D1 и D2 – равные, но все же различные объекты.

Для организации такого процесса стандартный механизм сериализации строит граф,
включающий в себя все участвующие объекты и ссылки между ними. Если очередная 
ссылка указывает на некоторый объект, сначала проверяется – нет ли такого объекта в графе. 
Если есть – объект второй раз не сериализуется. Если нет – новый объект добавляется в граф.

При построении графа может встретиться объект, порожденный от класса,
не реализующего интерфейс Serializable. В этом случае сериализация прерывается,
генерируется исключение java.io.NotSerializableException.*/



/* http://old.intuit.ru/department/pl/javapl/15/3.html

 0. transient и static поля не сохраняются и не восстанавливаются;
 1. Сериализуются только объекты, реализующие инт. Serializable (иначе
 Exception);
 2. Свойство Serializable передается по наследству (ясен пень);
 3. для Serializable объектов сериализация проводится внутренним
 механизмом JVM;
 4. если есть поля у not Serializable предков в иерархии, то такие
 предки ДОЛЖНЫ обязательно иметь конструктор без параметров, с его
 помощью будут созданы такие объекты во время десериализации (поля
 таких классов не сериализуются);

 5. Расширение стандартной сериализации
 Некоторым сложно организованным классам требуется особый подход для
 сериализации. Для расширения стандартного механизма можно объявить в
 классе два метода с точно такой сигнатурой:

 private void writeObject(java.io.ObjectOutputStream out)  
 	throws IOException;
 private void readObject(java.io.ObjectInputStream in) 
 	throws IOException, ClassNotFoundException;

 6.Если же процедура сериализации в корне отличается от стандартной,
 то
 для таких классов предназначен альтернативный интерфейс
 java.io.Externalizable.

 При использовании этого интерфейса в поток автоматически записывается
 только идентификация класса. Сохранить и восстановить всю информацию
 о состоянии экземпляра должен сам класс. Для этого в нем должны быть
 объявлены методы writeExternal() и readExternal() интерфейса
 Externalizable. Эти методы должны обеспечить сохранение состояния,
 описываемого полями самого класса и его суперкласса.

 При восстановлении Externalizable -объекта экземпляр создается путем
 вызова конструктора без аргументов, после чего вызывается метод
 readExternal.

 Метод writeExternal имеет сигнатуру:

 void writeExternal(ObjectOutput out)
 throws IOException;
 Для сохранения состояния вызываются методы ObjectOutput, с помощью
 которых можно записать как примитивные, так и объектные значения. Для
 корректной работы в соответствующем методе

 void readExternal(ObjectInput in)
 throws IOException,ClassNotFoundException;
 эти значения должны быть считаны в том же самом порядке.


7. Для сложных объектов, включающих другие объекты, строится граф сериализации.
Если при этом попадается несериализуемый объект */

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
			// записываем объекты в файл
			FileOutputStream os = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			oos.writeObject(line1);
			oos.writeObject(line2); 
			// меняем состояние line1 и записываем его еще раз
			line1.setIndex(3);
			// oos.reset();   		// Указывает, что сеанс сериализации завершен!!!!!!!!!!!!
									// и можно записывать измененные объекты
			oos.writeObject(line1);
			// закрываем потоки
			// достаточно закрыть только поток-надстройку
			oos.close();
			// считываем объекты
			System.out.println("Read objects:");
			FileInputStream is = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(is);
			for (int i = 0; i < 3; i++) { // Считываем 3 объекта
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