/**
 * 
 */
package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class Maison extends Elements{
		
	
	public Maison() {
		super("Maison","Maison");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.maison);
		return b;
	}

}

