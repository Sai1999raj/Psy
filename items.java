package com.example.sai.aibill;

import java.io.Serializable;

public class items implements Serializable {
    public items(String name,boolean selected) {
        this.name = name;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    String name = null;
    boolean selected = false;
}
