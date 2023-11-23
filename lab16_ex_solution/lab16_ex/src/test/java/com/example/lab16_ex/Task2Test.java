package com.example.lab16_ex;


import org.junit.jupiter.api.Test;



import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class Task2Test {

	public Class<?> loadClass(String name) {
		ClassLoader classLoader = this.getClass().getClassLoader();

		try {
			Class<?> aClass = classLoader.loadClass(name);
			return aClass;
		} catch (ClassNotFoundException e) {
		}
		return null;
	}

	@Test
	public void classesAndFieldsCreated() throws Exception {

		Class<?> parent = loadClass("com.example.lab16_ex.domain.Agent");
		assertNotNull(parent);

		// fields
		assertField(parent, "private", "int", "id");
		assertField(parent, "private", "java.lang.String", "name");
		assertField(parent, "private", "java.lang.String", "country");

	}

	private void assertField(Class<?> parent, String modifier, String type, String name) throws Exception {
		Field f = parent.getDeclaredField(name);
		assertNotNull(f);
		assertEquals(type, f.getType().getName());
		assertEquals(Modifier.PRIVATE, f.getModifiers());
	}

}
