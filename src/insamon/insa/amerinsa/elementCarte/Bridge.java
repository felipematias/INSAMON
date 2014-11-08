package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import insamon.insa.amerinsa.pokemon.Pokemon;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Guillaume
 *
 */
public class Bridge extends Elements implements Traversable {
		

	public Bridge() {
		super("Bridge","Bridge");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.bridge);
		return b;
	}

	public boolean action(Personnage p) { 
		
		return false;
	}




}
