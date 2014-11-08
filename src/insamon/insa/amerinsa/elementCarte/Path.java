package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class Path extends Elements implements Traversable {
		

	public Path() {
		super("Path","Path");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.path);
		return b;
	}

	public boolean action(Personnage p) {
		return false; 
		
	}

}

