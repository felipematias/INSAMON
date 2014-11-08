package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class GrassBorder extends Elements {
		

	public GrassBorder() {
		super("GrassBorder","GrassBorder");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.grass_border);
		return b;
	}


}
