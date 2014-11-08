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
 *Bulbasaur est un pokemon initial qui peut evouluer
 */
public class Bulbasaur extends Pokemon implements PokemonInit, PeutEvoluer {
	
	public Bulbasaur() {
		super("Bulbasaur", 220, 22, 16,
				"Fouet Lianes", "bulbasaur");
		// TODO Auto-generated constructor stub
	}

	public Pokemon evoluer(Pokemon p) {
	if (this.getpExperience()>=1800){	
		p=new Ivysaur();
		p.setpExperience(this.getpExperience());
		}
	return p;
	}
	
	public void lanceFeuille(){	
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.bulbasaur);
		return b;
	}

}
