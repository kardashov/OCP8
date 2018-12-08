package stas.locale;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Stream;

class SystemPropertiesRead {
	public static void main(String[] args) {

		Properties props = System.getProperties();
		// System.out.println(props);
		// props.forEach((a, b) -> System.out.println(a + " ===== " + b));
		// for ( Entry<Object, Object> s:props.entrySet()) {
		// System.out.println(s.getKey() + " : " + s.getValue());
		// }
		
		Map<Object, Object> mm = new TreeMap<>();
		props.forEach((a, b) -> mm.put(a, b));

		mm.forEach((a, b) -> System.out.println(a + "===" + b));
	
	}
}
