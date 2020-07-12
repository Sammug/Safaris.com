package com.example.etour;

import android.widget.ImageView;

public class SafarisClass {
    private ImageView safarisImage;
    private String safarisName;
    private String safarisDescription;

    public SafarisClass(){}

    public SafarisClass(String safarisName, String safarisDescription) {
        this.safarisName = safarisName;
        this.safarisDescription = safarisDescription;
    }

    public ImageView getSafarisImage() {
        return safarisImage;
    }

    public String getSafarisName() {
        return safarisName;
    }

    public String getSafarisDescription() {
        return safarisDescription;
    }

}
