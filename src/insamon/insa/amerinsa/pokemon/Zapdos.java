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
public class Zapdos extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Zapdos() {
		super("Zapdos", 780, 70, 66, "Thunder",
				"Zapdos");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.pokemon.Pokemon#chargerImage(android.content.res.Resources)
	 */
	@Override
	public Bitmap chargerImage(Resources r) {
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.zapdos);
		return b;
	}

}
