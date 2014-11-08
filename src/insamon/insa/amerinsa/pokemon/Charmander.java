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
 *Charmander est un pokemon initial
 */
public class Charmander extends Pokemon implements PokemonInit, PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Charmander() {
		super("Charmander", 200, 22, 15,
				"Lance-Flamme", "charmander");
		// TODO Auto-generated constructor stub
	}
	
	
	public Pokemon evoluer(Pokemon p){
		if (p.getpExperience()>=1800){
			
			p=new Charmeleon();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.charmander);
		return b;
	}


}
