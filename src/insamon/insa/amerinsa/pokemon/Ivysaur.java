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
public class Ivysaur extends Pokemon implements PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Ivysaur() {
		super("Ivysaur", 260, 37, 39,
				"Leaf Storm", "Ivysaur");
		// TODO Auto-generated constructor stub
	}

	
	public Pokemon evoluer(Pokemon p) {
	if (this.getpExperience()>=7500){
			
		p=new Venusaur();
		p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.ivysaur);
		return b;
	}

}
