package it.r.rubrica.utils;

import org.apache.commons.beanutils.BeanUtils;

public class RubricaBeanUtils {

	public static void copy(Object from, Object dest) {
		try {
			BeanUtils.copyProperties(dest, from);
		} catch (Exception e) {
			throw new RuntimeException("Errore durante il copy properties from:" + from + " dest:" + dest, e);
		}
	}
}
