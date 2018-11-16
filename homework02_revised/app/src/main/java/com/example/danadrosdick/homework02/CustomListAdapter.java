package com.example.danadrosdick.homework02;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*this is an extension from https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc by Dana Drosdick.
The purpose of this class is to reference the activity, store the list of players, and put it into a row view.
 */

class CustomListAdapter extends ArrayAdapter {
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

        TextView idTextField = rowView.findViewById(R.id.id);
        TextView emailAddressTextField = rowView.findViewById(R.id.emailAddress);
        TextView nameTextField = rowView.findViewById(R.id.name);


        if ( rowView != null ) {
            idTextField.setText(String.valueOf(playerArray[position].id)+"@string/comma");
            emailAddressTextField.setText(playerArray[position].email+"@string/comma");
            nameTextField.setText(playerArray[position].name);
        }

        return rowView;

    }

}