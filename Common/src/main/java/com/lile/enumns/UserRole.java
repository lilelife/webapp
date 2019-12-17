package com.lile.enumns;

public enum UserRole {

    User("user"),Admin("admin");

    private String role;

     UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return  role;
    }



}
