package com.example.danadrosdick.homework02;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String>{

    boolean multiplePlayers;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void fetchBtnPressed (View view){
        EditText mPlayerID = findViewById(R.id.edit_text);
        String input = mPlayerID.getText().toString();

            if (input.isEmpty()) {
                input = "players/";
                this.multiplePlayers = true;
            } else if (Integer.valueOf(input) >= 0 && Integer.valueOf(input) <= 3) {
                input = "player/" + input;
                this.multiplePlayers = false;
            } else {
                Toast.makeText(this, R.string.error,
                        Toast.LENGTH_LONG).show();
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
    public void onLoadFinished(Loader<String> loader, String input) {
        Player[] array;

        if(this.multiplePlayers) {
            array = multiplayerArray(input);
        } else {
            array = singlePlayer(input);
        }

        for (Player p  : array) {
            if (p.id != -1) {
                ListView listView;
                CustomListAdapter listAdapter = new CustomListAdapter(this, array);
                listView = (ListView) findViewById(R.id.listView);
                listView.setAdapter(listAdapter);
            }
            else {
                Toast.makeText(this, R.string.error,
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {
    }

    private Player[] multiplayerArray(String JsonString) {
        Player[] playerArray = new Player[3];

        try {
            JSONObject jObject = new JSONObject(JsonString);
            JSONArray jArray = jObject.getJSONArray("items");

            for( int i = 0; i<jArray.length(); i++ ){
                JSONObject player = jArray.getJSONObject(i);
                playerArray[i] = new Player();

                try {
                    if( player.has("id") ) {
                        playerArray[i].setID( player.getInt("id") );
                    }
                    if( player.has("emailAddress") ) {
                        playerArray[i].setEmail( player.getString("emailAddress") );
                    }
                    if( player.has("name") ) {
                        playerArray[i].setName( player.getString("name") );
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return playerArray;
    }

    private Player[] singlePlayer(String JsonString) {
        Player[] playerArray = new Player[1];
        playerArray[0] = new Player();

        try {
            JSONObject player = new JSONObject(JsonString);

            if( player.has("id") ) {
                playerArray[0].setID( Integer.parseInt( player.getString("id")) );
            }
            if( player.has("emailAddress") ) {
                playerArray[0].setEmail( player.getString("emailAddress") );
            }
            if( player.has("name") ) {
                playerArray[0].setName( player.getString("name") );
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return playerArray;
    }
}
