package comic.app.karan.comic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Karan on 9/14/2015.
 */public class Start extends Activity {

    @Override
    protected void onCreate(Bundle abc) {
        // TODO Auto-generated method stub
        super.onCreate(abc);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.start);
        Thread timer=new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(2000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    Intent openStartingPoint=new Intent("android.intent.action.MainActivity");
                    startActivity(openStartingPoint);

                }
            }


        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();

    }





}
