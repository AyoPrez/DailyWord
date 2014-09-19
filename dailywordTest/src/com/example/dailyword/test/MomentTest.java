package com.example.dailyword.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MomentTest {

	private String Id;
	private String AppLanguage;
	private String Language;
	private String Level;
	private String Time;
	
	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getAppLanguage() {
		return AppLanguage;
	}

	public void setAppLanguage(String appLanguage) {
		AppLanguage = appLanguage;
	}

	public String getLanguage() {
		return Language;
	}

	public void setLanguage(String language) {
		Language = language;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	@Test
	public void test() {
		setLevel("Basic");
		assertEquals(getLevel(), "Basic");
	}

}