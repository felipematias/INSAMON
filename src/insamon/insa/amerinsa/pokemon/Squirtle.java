/**
 * 
 */
package insamon.insa.amerinsa.pokemon;

import insamon.insa.amerinsa.PeutEvoluer;
import insamon.insa.amerinsa.PokemonInit;
import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *Squirtle est un pokemon initial
 */
public class Squirtle extends Pokemon implements PokemonInit, PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Squirtle() {
		super("Squirtle", 200, 20, 23,
				"Hydroqueue", "squirtle");
		// TODO Auto-generated constructor stub
	}

	public Pokemon evoluer(Pokemon p) {
		if (this.getpExperience()>=1800){
			p=new Wartortle();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.squirtle);
		return b;
	}

}
