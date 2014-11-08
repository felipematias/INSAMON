package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class D33 extends Elements{
		
	
	public D33() {
		super("Donjon","Donjon");
		
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.d33);
		return b;
	}


}
