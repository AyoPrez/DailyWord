package deilyword;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 

/**
 * Entity mapped to table USER_MOMENTS.
 */
public class UserMoments {

    private Long id;
    private String language;
    private String level;
    private String time;
    private String applanguage;

    public UserMoments() {
    }

    public UserMoments(Long id) {
        this.id = id;
    }

    public UserMoments(Long id, String language, String level, String time) {
        this.id = id;
        this.language = language;
        this.level = level;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
