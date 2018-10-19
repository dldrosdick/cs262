package com.example.danadrosdick.homework02;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//code extended from https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

public class CustomListAdapter extends ArrayAdapter {
    //to reference the Activity
    private final Activity context;

    //to store the list of players
    private final Player[] playerArray;


    public CustomListAdapter( Activity context, Player[] playerArrayParam ) {
        super(context, R.layout.list_view, playerArrayParam);
        this.context = context;
        this.playerArray = playerArrayParam;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_view, null,true);

        TextView idTextField = (TextView) rowView.findViewById(R.id.id);
        TextView emailAddressTextField = (TextView) rowView.findViewById(R.id.emailAddress);
        TextView nameTextField = (TextView) rowView.findViewById(R.id.name);


        if ( rowView != null ) {
            idTextField.setText(String.valueOf(playerArray[position].id)+", ");
            emailAddressTextField.setText(playerArray[position].emailAddress+", ");
            nameTextField.setText(playerArray[position].name);
        }

        return rowView;

    };

}