package insamon.insa.amerinsa.pokemon;

import insamon.insa.amerinsa.PeutEvoluer;
import insamon.insa.amerinsa.PokemonInit;
import insamon.insa.amerinsa.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * 
 * @author Felipe
 *Pikachu est un pokemon initial
 */

public class Pikachu extends Pokemon implements PokemonInit, PeutEvoluer {

	
	public Pikachu() {
		super("Pikachu", 200, 25, 13,
				"Thunderbolt", "pikachu");
		this.setLvl(3);
		// TODO Auto-generated constructor stub
	}
	

	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.pikachu);
		return b;
	}



	public Pokemon evoluer(Pokemon p) {
		if (p.getpExperience()>=1800){
			p=new Isachu();
			p.setpExperience(this.getpExperience());
		}
		return p;

	}
}
