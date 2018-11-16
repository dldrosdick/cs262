package com.example.danadrosdick.homework02;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

/* this is the main activity that handles the pressed button, opens the connection to the database and
pulls information from it, and parses through the data to return the proper data
 */


public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String>{

    private boolean containsPlayer;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void fetchBtnPressed (View view){
        EditText mPlayer = findViewById(R.id.edit_text);
        String input = mPlayer.getText().toString();

        if (input.isEmpty()) {
            input = "players/";
            this.containsPlayer = true;
        } else if ( Integer.parseInt(input) >= 0) {
            input = "player/" + input;
            this.containsPlayer = false;
        } else {
            //TODO: THROW ERROR
        }

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() && input.length() != 0) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", input);
            getSupportLoaderManager().restartLoader(0, queryBundle,this);
        }

    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return new DataLoader(this, args.getString("queryString"));
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String s) {
        Player[] display;
        boolean error = false;

        if( this.containsPlayer ) {
            display = parsePlayersArray(s);
        } else {
            display = parseSinglePlayer(s);
        }
        for (Player p : display) {
            if (p.id == -1) {
                error = true;
            }
        }

        if ( !error ) {
            ListView listView;
            CustomListAdapter listAdapter = new CustomListAdapter(this, display);
            listView = findViewById(R.id.listView);
            listView.setAdapter(listAdapter);
        } else {
            Toast.makeText(this, R.string.error,
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
    private Player[] parsePlayersArray(String JsonString) {
        Player[] playersArray = new Player[3];

        try {
            JSONObject jObject = new JSONObject(JsonString);
            JSONArray jArray = jObject.getJSONArray("items");
            for( int i = 0; i<jArray.length(); i++ ){
                JSONObject player = jArray.getJSONObject(i);
                playersArray[i] = new Player();

                try {
                    if( player.has("id") ) {
                        playersArray[i].setId( player.getInt("id") );
                    }


                    if( player.has("emailAddress") ) {
                        playersArray[i].setEmail( player.getString("emailAddress") );
                    }


                    if( player.has("name") ) {
                        playersArray[i].setName( player.getString("name") );
                    } else {
                        playersArray[i].setName( "no name" );
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return playersArray;
    }

    private Player[] parseSinglePlayer(String JsonString) {
        Player[] playersArray = new Player[1];
        playersArray[0] = new Player();

        try {
            JSONObject player = new JSONObject(JsonString);

            if( player.has("id") ) {
                playersArray[0].setId( Integer.parseInt( player.getString("id")) );
            }


            if( player.has("emailAddress") ) {
                playersArray[0].setEmail( player.getString("emailAddress") );
            }


            if( player.has("name") ) {
                playersArray[0].setName( player.getString("name") );
            } else {
                playersArray[0].setName( "no name" );
            }

        } catch (Exception e){
            e.printStackTrace();
        }

        return playersArray;
    }
}