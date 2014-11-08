package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class D31 extends Elements{
		
	
	public D31() {
		super("Donjon","Donjon");
		
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.d31);
		return b;
	}


}
