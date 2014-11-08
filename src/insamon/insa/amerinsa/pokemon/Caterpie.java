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
public class Caterpie extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Caterpie() {
		super("Caterpie", 40, 7, 10, "Tackle",
				"Caterpie");
		this.setLvl(1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.pokemon.Pokemon#chargerImage(android.content.res.Resources)
	 */
	@Override
	public Bitmap chargerImage(Resources r) {
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.caterpie);
		return b;
	}

}
