package com.example.danadrosdick.homework02;

/*this is an extension from https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc by Dana Drosdick.
The purpose of this class is to reference the activity, store the list of players, and put it into a row view.
 */


class Player {
    public int id;
    public String email;
    public String name;

    public Player() {
        this.id = -1;
        this.email = "";
        this.name = "";
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}