/**
 * 
 */
package insamon.insa.amerinsa.elementCarte;

import insamon.insa.amerinsa.Personnage;
import insamon.insa.amerinsa.R;
import insamon.insa.amerinsa.pokemon.Pokemon;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;



/**
 * @author Felipe
 *Quand on passe sur le centre de soin on recupere la vie du pokemon qui appartient au notre personnage
 */
public class Soin extends Elements implements Traversable {


	public Soin() {
		super("Soin","Centre de Soin");
		// TODO Auto-generated constructor stub
	}
	
	
	
    public void recupererPDV(Pokemon p) {
    	p.setPdv(p.getPointsDeVieMax());
    	System.out.println("la vie du pokemon a été récuperé");
    }

    @Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.red_cross);
		return b;
	}


    
	public void action(Pokemon p) {
		recupererPDV(p);
	
	}



	public boolean action(Personnage p) {
		// TODO Auto-generated method stub
		return false;
	}



}
