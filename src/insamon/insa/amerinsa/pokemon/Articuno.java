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
public class Articuno extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Articuno() {
		super("Articuno", 900, 66, 75, "Blizzard",
				"Articuno");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.pokemon.Pokemon#chargerImage(android.content.res.Resources)
	 */
	@Override
	public Bitmap chargerImage(Resources r) {
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.articuno);
		return b;
	}

}
