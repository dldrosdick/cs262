package com.example.danadrosdick.homework02;

// code extended from https://stackoverflow.com/questions/17052691/java-player-array-from-class

public class Player {
    /** player id */
    public int id;
    /** player email */
    public String emailAddress;
    /** player name */
    public String name;

    /** Constructs a new Player */
    public Player() {
        this.id = -1;
        this.name = "no name";
        this.emailAddress = "no email";
    }

    /** Sets the id, email, and name of player using the given parameter value. */
    public void setID(int newID) {
        this.id = newID;
    }
    public void setEmail(String newEmail) {
        this.emailAddress = newEmail;
    }

    public void setName(String newName) {
        this.name = newName;
    }

}


