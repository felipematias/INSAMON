/**
 * 
 */
package insamon.insa.amerinsa.pokemon;

import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


/**
 * @author Felipe
 *
 */
public class Blastoise extends Pokemon{

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Blastoise() {
		super("Blastoise", 650, 70, 120,
				"Hydro Pump", "blastoise");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.blastoise);
		return b;
	}
}
