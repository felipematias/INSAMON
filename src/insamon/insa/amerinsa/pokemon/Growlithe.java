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
public class Growlithe extends Pokemon implements PeutEvoluer{

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Growlithe() {
		super("Growlithe", 105, 20, 18,
				"Flame Charge", "Growlithe");
		this.setLvl(2);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see insamon.insa.amerinsa.PeutEvoluer#evoluer(insamon.insa.amerinsa.Personnage)
	 */
	public Pokemon evoluer(Pokemon p) {
		if (this.getpExperience()>=2000){
			
			p=new Arcanine();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.growlithe);
		return b;
	}
}


