package comic.app.karan.comic;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Karan on 9/12/2015.
 */
// Progress Dialog
public class FetchDetails extends ListActivity {
private ProgressDialog pDialog;
    ListView listview;
    ListViewAdapter adapter;
        JSONParser jParser = new JSONParser();

        ArrayList<HashMap<String, String>> productsList;

// url to get all products list
private static String url_all_products;
    static String COMIC_NAME="comic_name";
    static String PATH="path";
   // static String MESSAGE="message";

// JSON Node names
private static final String TAG_SUCCESS = "success";
private static final String TAG_PRODUCTS = "products";
private static final String TAG_PID = "pid";
private static final String TAG_NAME = "name";
    ArrayList<HashMap<String, String>> arraylist;
    static String TITLE = "title";
    JSONObject jsonobject;
    JSONArray jsonarray;

        // products JSONArray

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.details_activity);
    if(isOnline()) {
        Bundle b = getIntent().getExtras();
        String value = b.getString("title");
        System.out.println("VALUE+++" + value);

        url_all_products = "http://www.engyin.com/comic/data.php?title=" + value;
        // Hashmap for ListView
        productsList = new ArrayList<HashMap<String, String>>();

        // Loading products in Background Thread
        new LoadAllProducts().execute();

    }


}
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

            class LoadAllProducts extends AsyncTask<Void,Void,Void> {

                /**
                 * Before starting background thread Show Progress Dialog
                 */
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    pDialog = new ProgressDialog(FetchDetails.this);
                    pDialog.setMessage("Loading Comics...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(false);
                    pDialog.show();
                }

                /**
                 * getting All products from url
                 */
                @Override
                protected Void doInBackground(Void... params) {
                    // Create an arrayS
                    arraylist = new ArrayList<HashMap<String, String>>();
                    // Retrieve JSON Objects from the given URL address
                    jsonobject = JSONParser
                            .getJSONfromURL(url_all_products);

                    try {
                        // Locate the array name in JSON
                        jsonarray = jsonobject.getJSONArray("superhero");



                        for (int i = 0; i < jsonarray.length(); i++) {
                            HashMap<String, String> map = new HashMap<String, String>();
                            jsonobject = jsonarray.getJSONObject(i);
                            // Retrieve JSON Objects
                            map.put("comic_name", jsonobject.getString("comic_name"));
                            map.put("path", jsonobject.getString("path"));

                            // Set the JSON Objects into the array
                            arraylist.add(map);
                        }
                    } catch (JSONException e) {
                        Log.e("Error", e.getMessage());
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Void args) {
                    // Locate the listview in listview_main.xml
                    listview = (ListView) findViewById(android.R.id.list);
                    // Pass the results into ListViewAdapter.java
                    adapter = new ListViewAdapter(FetchDetails.this, arraylist);

                    // Set the adapter to the ListView
                    listview.setAdapter(adapter);

                    // Close the progressdialog
                    TextView empty = (TextView)findViewById(android.R.id.empty);
                    empty.setText("No comic available");
                    empty.setVisibility((adapter.isEmpty())?View.VISIBLE:View.GONE);
                    //listview.setEmptyView(empty);

                    //listview.setEmptyView((TextView) findViewById(android.R.id.empty));

                    pDialog.dismiss();
                }
            }


}