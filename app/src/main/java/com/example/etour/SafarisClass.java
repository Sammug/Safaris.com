package com.example.etour;

public class SafarisClass {
    private String safarisName;
    private String safarisDescription;

    public SafarisClass(String safarisName, String safarisDescription) {
        this.safarisName = safarisName;
        this.safarisDescription = safarisDescription;
    }

    public String getSafarisName() {
        return safarisName;
    }

    public void setSafarisName(String safarisName) {
        this.safarisName = safarisName;
    }

    public String getSafarisDescription() {
        return safarisDescription;
    }

    public void setSafarisDescription(String safarisDescription) {
        this.safarisDescription = safarisDescription;
    }
}
