package com.example.etour;

public class SafarisClass {
    private String safarisImageUri;
    private String name;
    private String description;

    public SafarisClass(){}

    public SafarisClass(String safarisImageUri, String name, String description) {
        this.safarisImageUri = safarisImageUri;
        this.name = name;
        this.description = description;
    }

    public String getSafarisImageUri() {
        return safarisImageUri;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
