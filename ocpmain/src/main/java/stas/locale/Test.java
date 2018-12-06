package stas.locale;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.ResourceBundle;

class MyResource extends ResourceBundle {

    private Hashtable res = null;

    public MyResource() {
        res = new Hashtable();
        res.put("TestKey", "English Variant");
    }

    public Enumeration getKeys() {
        return res.keys();
    }

    protected Object handleGetObject(String key) throws java.util.MissingResourceException {
        return res.get(key);
    }
}

class MyResource_ru_RU extends ResourceBundle {
    private Hashtable res = null;

    public MyResource_ru_RU() {
        res = new Hashtable();
        res.put("TestKey", "������� �������");
    }

    public Enumeration getKeys() {
        return res.keys();
    }

    protected Object handleGetObject(String key) throws java.util.MissingResourceException {
        return res.get(key);
    }
}

public class Test {
    public Test() {
    }

    public static void main(String[] args) {
        Test test = new Test();
        ResourceBundle rb = ResourceBundle.getBundle("stas.locale.MyResource", Locale.getDefault());
        System.out.println(rb.getString("TestKey"));
        rb = ResourceBundle.getBundle("stas.locale.MyResource", new Locale("ru", "RU"));
        System.out.println(rb.getString("TestKey"));
    }
}
