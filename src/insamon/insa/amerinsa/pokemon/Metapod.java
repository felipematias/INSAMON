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
 *Metapod n'a pas d'attaque physique, mais il peut augmenter sa defense
 */
public class Metapod extends Pokemon {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Metapod() {
		super("Metapod", 300, 0, 20,
				"harden", "");
		this.setLvl(2);
		// TODO Auto-generated constructor stub
	}
	
	 public void attaquer(Pokemon p ) {
	    	harden();
	    
	    }

	private void harden() {
		this.setpDefense(getPDefense()+1);
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.metapod);
		return b;
	}

}
