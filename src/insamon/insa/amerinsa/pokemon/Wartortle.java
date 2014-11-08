/**
 * 
 */
package insamon.insa.amerinsa.pokemon;

import insamon.insa.amerinsa.PeutEvoluer;
import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *
 */
public class Wartortle extends Pokemon implements PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Wartortle() {
		super("Wartortle", 250, 35, 45,
				"Aqua Jet", "wartortle");
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.PeutEvoluer#evoluer(insamon.insa.amerinsa.Personnage)
	 */
	public Pokemon evoluer(Pokemon p) {
	if (this.getpExperience()>=7500){
		p=new Blastoise();
		p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.wartortle);
		return b;
	}
}
