/**
 * 
 */
package insamon.insa.amerinsa.elementCarte;

import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import insamon.insa.amerinsa.pokemon.Caterpie;
import insamon.insa.amerinsa.pokemon.Jigglypuff;
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
public class SolHerbeux extends Elements implements Traversable {

	/**
	 * @param type
	 * @param nomImage
	 */
	public SolHerbeux() {
		super("SolHerbeux","ground");
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.ground);
		return b;
	}

	public boolean action(Personnage p) {
		boolean res=false;
		int random = (int) (Math.random()*20);
		
		if(random==17){
			System.out.println("random_rattata");
			Rattata rattata= new Rattata();
			setLvl(rattata);
			rattata.setPdvEvolution();
			this.setPokemonAttaquant(rattata);
			res=true;
		}
		else if(random==4){
			System.out.println("random_rondoudou");
			Jigglypuff jigglypuff= new Jigglypuff();
			setLvl(jigglypuff);
			jigglypuff.setPdvEvolution();
			this.setPokemonAttaquant(jigglypuff);
			res=true;
		}
		else if(random==5){
			System.out.println("random_caterpie");
			Caterpie caterpie= new Caterpie();
			setLvl(caterpie);
			caterpie.setPdvEvolution();
			this.setPokemonAttaquant(caterpie);
			res=true;
		}
		else if(random==3){
			System.out.println("random_roucoul");
			Pidgey pidgey= new Pidgey();
			setLvl(pidgey);
			pidgey.setPdvEvolution();
			this.setPokemonAttaquant(pidgey);
			res=true;
		}
		System.out.println("random_rien");
		return res;
	}
	
	/**
	 * Cette méthode sert a mettre des niveaux aletoires dans les pokemons
	 * @param p
	 */
	public void setLvl(Pokemon p){
		int random = (int) (Math.random()*30);

		if(random<=10){
			p.setLvl(1);
		}
		else if(random>10 && random<=20){
			p.setLvl(2);
		}
		else if(random>20 && random<=30){
			p.setLvl(3);
		}
	}
	

}
