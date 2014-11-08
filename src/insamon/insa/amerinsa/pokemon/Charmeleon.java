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
 *Charmeleon est l'evolution de Charmander
 */
public class Charmeleon extends Pokemon implements PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Charmeleon() {
		super("Charmeleon", 250, 40, 35,
				"Flamethrower", "charmeleon");
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.charmeleon);
		return b;
	}


	public Pokemon evoluer(Pokemon p) {
		if (this.getpExperience()>=7500){

			p=new Charizard();
			p.setpExperience(this.getpExperience());
			}
		return p;
		}
	}

