package insamon.insa.amerinsa;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class MapLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);   
	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, //pour faire l'ecran fullscreen
	                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_map_location);
		
		
		ImageView mImageView = (ImageView) this.findViewById(R.id.img);
		
		//On charge l'image de la map et on la met dans l'image view
	    Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.map_insamon_new);
	    mImageView.setImageBitmap(image);
	}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.map_location, menu);
		return true;
	}

}
