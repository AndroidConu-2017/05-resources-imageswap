package cs518.samples.imageswap;

/**
 * App uses various resources
 * drawables
 * colour
 * string       
 * raw     (sound)
 *
 * in xml:  
 *  use an alternate layout  landscape / portrait
 *  use dimensions in layout 
 *  @author tricia
 *
 *  TODO: fix the way I deal with the mediaplayer
 *
 */

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageButton bt01, bt02;
	TextView tv;
	MediaPlayer mp;
	int bt01image, bt01colour;
	public static final String TAG = "IMGSWP";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt01 = (ImageButton) findViewById(R.id.imageButton1);
		bt02 = (ImageButton) findViewById(R.id.imageButton2);
		
		tv = (TextView) findViewById(R.id.message);
		
		if ( (mp = MediaPlayer.create(this, R.raw.meow)) == null) {
			  ImageButton bt03 = (ImageButton) findViewById(R.id.imageButton3);
			  // if problem with media player then disable tard button
			  bt03.setEnabled(false);
	     }
		bt01image = R.drawable.cleanallboo;
		bt01colour = R.color.indigo;
		swapimage();
	}

    protected void onPause() {
		super.onPause();
    	// releasing the MediaPlayer resources
    	if (mp != null) {
			try {
				mp.release();
			} catch (Exception e) {
				Log.d(TAG, "Mediaplayer release "+e.toString());
			}
    	}
    }
    
	public void button1Clicked(View view) {
		Toast.makeText(this, "Button1 clicked", Toast.LENGTH_SHORT).show();
		swapimage();	
	}
	public void button2Clicked(View viewre) {
		Toast.makeText(this, "Button2 clicked", Toast.LENGTH_SHORT).show();
		swapimage();
	}
	public void button3Clicked(View view) {
		Toast.makeText(this, "Button3 clicked", Toast.LENGTH_LONG).show();
		try {
			mp.start();
		} catch (Exception e1) {
			Log.d(TAG, "Mediaplayer start failed ");
			try {
				mp.reset();
				e1.printStackTrace();
			} catch (Exception e2) {
				Log.d(TAG, "Mediaplayer reset  failed ");
				e2.printStackTrace();
				Toast.makeText(this, "Meow failed, rotate then try again", Toast.LENGTH_SHORT).show();
			}
		}

	}
	public void swapimage() {
		// swap the two images in place and changes the message text colour.
		if (bt01image == R.drawable.cleanallboo) {
			bt01.setImageResource(R.drawable.cleanallyay);
			bt02.setImageResource(R.drawable.cleanallboo);
			bt01image = R.drawable.cleanallyay;
			tv.setTextColor(getResources().getColor(R.color.lightcoral));
			 
		} else {
			bt02.setImageResource(R.drawable.cleanallyay);
			bt01.setImageResource(R.drawable.cleanallboo);
			bt01image = R.drawable.cleanallboo;
			tv.setTextColor(getResources().getColor(R.color.indigo));
		}
	}
	public void rotateScreen(View view) {
		if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
	       setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		else 
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}  //rotateScreen()
	
}
