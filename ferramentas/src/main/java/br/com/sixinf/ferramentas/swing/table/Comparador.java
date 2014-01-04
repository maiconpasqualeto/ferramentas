package br.com.sixinf.ferramentas.swing.table;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Vector;


public class Comparador<T> implements Comparator<T> {
	
    boolean ascending;
    Method metodoGet;

	public Comparador(Vector<T> dados, Method metodoGet, boolean ascending) {
		this.metodoGet = metodoGet;
		this.ascending = ascending;		
	}

	@SuppressWarnings("unchecked")
	public int compare(Object a, Object b) {
        Object o1 = null;
        Object o2 = null;
		try {
			o1 = metodoGet.invoke(a, new Object[0]);
			o2 = metodoGet.invoke(b, new Object[0]);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
        

        // Treat empty strains like nulls
        if (o1 instanceof String && ((String)o1).length() == 0) {
            o1 = null;
        }
        if (o2 instanceof String && ((String)o2).length() == 0) {
            o2 = null;
        }

        // Sort nulls so they appear last, regardless
        // of sort order
        if (o1 == null && o2 == null) {
            return 0;
        } else if (o1 == null) {
            return 1;
        } else if (o2 == null) {
            return -1;
        } else if (o1 instanceof Comparable) {
            if (ascending) {
                return o1.toString().compareToIgnoreCase(o2.toString());
            } else {
                return o2.toString().compareToIgnoreCase(o1.toString());
            }
        } else {
            if (ascending) {
                return o1.toString().compareToIgnoreCase(o2.toString());
            } else {
                return o2.toString().compareToIgnoreCase(o1.toString());
            }
        }
	}

}
