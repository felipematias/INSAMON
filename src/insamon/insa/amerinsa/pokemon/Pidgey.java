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
 *Un pokemon faible qu'on pourra trouver dans le debut du jeu
 */
public class Pidgey extends Pokemon implements PeutEvoluer {

	/**
	 * @param nomPokemon
	 * @param pointsDeVieMax
	 * @param pAttaque
	 * @param pDefense
	 * @param rayonAction
	 * @param nomAttaque
	 * @param nomImage
	 */
	public Pidgey() {
		super("Pidgey", 50, 8, 10,
				"Tornade", "Pidgey.ico");
		this.setLvl(1);
		// TODO Auto-generated constructor stub
	}

	public Pokemon evoluer(Pokemon p){
		if (this.getpExperience()>=1000){
			
			p=new Pidgeotto();
			p.setpExperience(this.getpExperience());
		}
		return p;
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.pidgey);
		return b;
	}

}
