package com.nextplugins.libs.data.converter.tests.model;

public class Administrator extends User {

    private final String role;

    public Administrator(int id, String name, String role) {
        super(id, name);

        this.role = role;
    }


    public String getRole() {
        return role;
    }

}
