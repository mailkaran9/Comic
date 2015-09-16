package comic.app.karan.comic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Karan on 9/12/2015.
 */
public class ListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    TextView comic_name;
    HashMap<String, String> resultp = new HashMap<String, String>();

    public ListViewAdapter(Context context,
                           ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        // Declare Variables

        final String googleDocsUrl = "http://docs.google.com/viewer?url=";
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        // Get the position
        resultp = data.get(position);

        // Locate the TextViews in listview_item.xml
        comic_name = (TextView) itemView.findViewById(R.id.name);


            // Capture position and set results to the TextViews
         comic_name.setText(resultp.get(FetchDetails.COMIC_NAME));


        // Capture ListView item click
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get the position
                resultp = data.get(position);

            String s=resultp.get(FetchDetails.PATH);

                System.out.println("===="+s);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                System.out.println(comic_name.getText().toString());
                intent.setDataAndType(Uri.parse(googleDocsUrl + "www.engyin.com/comic/pdf/"+s), "text/html");

                context.startActivity(intent);

            }
        });
        return itemView;
    }
}
