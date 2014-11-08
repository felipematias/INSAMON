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
public class Mewtwo extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Mewtwo() {
		super("Mewtwo", 750, 120, 80,
				"Psystrike", "Mewtwo");
		this.setLvl(10);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.mewtwo);
		return b;
	}

}
