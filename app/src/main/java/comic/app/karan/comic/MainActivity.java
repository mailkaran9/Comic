package comic.app.karan.comic;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private GridView gridView;
    private Adapter gridAdapter;
    ImageButton b1;
    String[] hero=new String[] {"Spiderman","Hulk","Avengers","Storm","Ironman","Blackwidow","CaptainAmerica","FantasticFour","GuardiansGalaxy",
                    "Shield","Thor","Xmen"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


            b1 = (ImageButton) findViewById(R.id.imageButton2);
            gridView = (GridView) findViewById(R.id.gridView);
            gridAdapter = new Adapter(this, R.layout.grid_layout, getData());
            gridView.setAdapter(gridAdapter);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Search.class);
                    startActivity(intent);
                }
            });

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                    //Create intent
                    Intent intent = new Intent(MainActivity.this, FetchDetails.class);
                    intent.putExtra("title", item.getTitle());
                    System.out.println("item===" + item.getTitle());
                    // intent.putExtra("image", item.getImage());

                    //Start details activity
                    startActivity(intent);
                }
            });


    }

    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, hero[i]));
        }
        return imageItems;



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
