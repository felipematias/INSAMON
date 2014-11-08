package insamon.insa.amerinsa;

import java.io.Serializable;

import insamon.insa.amerinsa.elementCarte.Elements;
import insamon.insa.amerinsa.elementCarte.Traversable;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Enigme extends Elements implements Traversable,Serializable {
	
	/**
	 * 
	 */

	private String question;
	private String reponseCorrecte;
	private String mauvaiseReponse1;
	private String mauvaiseReponse2;
	private String mauvaiseReponse3;
	private boolean solved=false;
	
	/*
	 * il faudra pe rajout� un string pour avoir le num�ro de l'�nigme.
	 * 
	 */
	
	//private double rayonInfluence=5.0;   // on oublie les rayons d'action. Un �l�ment est associ� � une case.
	
	public String getQuestion() {
		return question;
	}

	public String getReponseCorrecte() {
		return reponseCorrecte;
	}

	public String getMauvaiseReponse1() {
		return mauvaiseReponse1;
	}

	public String getMauvaiseReponse2() {
		return mauvaiseReponse2;
	}

	public String getMauvaiseReponse3() {
		return mauvaiseReponse3;
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}

	public Enigme(String question, String mauvaiseReponse1, String mauvaiseReponse2, String mauvaiseReponse3,String reponseCorrecte ) {
		super("Enigme", "Enigme"); // j'ai mis une taille fixe pour les envelopes qui contiennent des énigmes
			this.mauvaiseReponse1=mauvaiseReponse1;
			this.mauvaiseReponse2=mauvaiseReponse2; 
			this.mauvaiseReponse3=mauvaiseReponse3;
			this.reponseCorrecte=reponseCorrecte;
			this.question=question;
			
	}

	@Override
	public Bitmap chargerImage(Resources r){	
		Bitmap b=BitmapFactory.decodeResource(r, R.drawable.enigme);
		return b;
	}

	public boolean action(Personnage personnage) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*public double distance (Personnage p) { 
		
        int deltaX= this.getPosX()-p.PosX;
        int deltaY= this.getPosY()-p.PosY;
        
        return Math.sqrt(deltaX*deltaX+deltaY*deltaY);
    }
    
    public String voisinDe (Personnage p){
    	String s="";
    	if(distance (p) < this.rayonInfluence){
    		s= "Vous avez un énigme à résoudre!";
    	}
    	return s;
    }*/
    
    
    
    

}
