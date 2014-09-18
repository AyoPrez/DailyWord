package com.example.dailyword.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DatabaseLanguagesTest {

	private String[] Languages = {"Español", "Inglés", "Alemán", "Italiano"};
		
	public String[] getLanguages(){
		return Languages;		
	}
	
	@Test
	public void DatabaseLanguage() {
		String[] Lang = getLanguages();
		
		assertEquals(Lang[0], "Español");
		assertEquals(Lang[1], "Inglés");
		assertEquals(Lang[2], "Alemán");
		assertEquals(Lang[3], "Italiano");
	}
	
}