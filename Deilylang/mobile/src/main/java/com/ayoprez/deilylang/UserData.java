package com.ayoprez.deilylang;

public class UserData {
    private String Level;
  	private String Language;
  	private String Time;
  	private String Id;
 
    public UserData(String Id, String Level, String Language, String Time) {
    	this.Id = Id;
        this.Level = Level;
        this.Language = Language;
        this.Time = Time;
    }
    
    public String getId(){
    	return this.Id;
    }
    
    public void setId(String id){
    	this.Id = id;
    }

	public String getLevel() {
        return this.Level;
    }
 
    public void setLevel(String level) {
		Level = level;
	}
    
    public String getLanguage() {
        return this.Language;
    }

	public void setLanguage(String lan) {
		Language = lan;
	}

	public String getTime() {
		return this.Time;
	}
	
	public void setTime(String time) {
		Time = time;
	}
}