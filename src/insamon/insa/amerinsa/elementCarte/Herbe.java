/**
 * 
 */
package insamon.insa.amerinsa.elementCarte;


import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import insamon.insa.amerinsa.pokemon.Metapod;
import insamon.insa.amerinsa.pokemon.Pidgey;
import insamon.insa.amerinsa.pokemon.Pokemon;
import insamon.insa.amerinsa.pokemon.Rattata;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * @author Felipe
 *
 */
public class Herbe extends Elements implements Traversable {
		

	public Herbe() {
		super("Herbe","Herbe");
		
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.herbe);
		return b;
	}

	public boolean action(Personnage p) {
		boolean res=false;
		int random = (int) (Math.random()*20);
		System.out.println("random_action");
		
		if(random<4){
			System.out.println("random_rattata");
			Rattata rattata= new Rattata();
			this.setPokemonAttaquant(rattata);
			res=true;
		}
		else if(random==8||random==10){
			System.out.println("random_metapod");
			Metapod metapod = new Metapod();
			this.setPokemonAttaquant(metapod);
			res=true;
		}
		else if(random>17){
			Pidgey pidgey= new Pidgey();
			System.out.println("random_pidgey");
			this.setPokemonAttaquant(pidgey);
			res=true;
		}
		System.out.println("pas de pokemon");
		return res;
	}

}
