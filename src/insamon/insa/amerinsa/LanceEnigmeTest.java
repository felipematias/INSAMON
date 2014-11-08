




package insamon.insa.amerinsa;

import insamon.insa.amerinsa.pokemon.Arcanine;
import insamon.insa.amerinsa.pokemon.Dewgong;
import insamon.insa.amerinsa.pokemon.Gengar;
import insamon.insa.amerinsa.pokemon.Mewtwo;
import insamon.insa.amerinsa.pokemon.Pidgeot;
import insamon.insa.amerinsa.pokemon.Pidgey;
import insamon.insa.amerinsa.pokemon.Pokemon;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LanceEnigmeTest extends Activity {
	
	/*attributs*/
	private int random;
	private int x;
	private int y;
	private int compteur=0;
	private ArrayList<Integer> coord;	
	private Button b1;
	private Button b2;
	private Button b3;
	private Button b4;
	private Button boutonJuste;
	private Button boutonFaux1;
	private Button boutonFaux2;
	private Button boutonFaux3;
	private String question;
	private String r1;
	private String r2;
	private String r3;
	private String rc;
	private String direction;
	private Enigme enigme;
	private Pokemon pokemonDresseur;
	private Pokemon pokeDonjon =new Pidgey();
	

	
	
	@Override
	/**
	 * main de la classe
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lance_enigme_test);
		
		// récupération des boutons
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		
		recupererIntent();
		
		/*
		 * initialisation des strings pour l'énigme
		 */
		r1=enigme.getMauvaiseReponse1();
		r2=enigme.getMauvaiseReponse2();
		r3=enigme.getMauvaiseReponse3();
		rc=enigme.getReponseCorrecte();
		question=enigme.getQuestion();
		
	/*
	 * on récupère les éléments du xml que l'on voudra modifier (boutons et textes)
	 */
		Resources res = getResources();
		String chaineQuestion = res.getString(R.string.text_question, question);
		TextView stringPok = (TextView)findViewById(R.id.textView2);
		stringPok.setText(chaineQuestion);
		String chaineR1 = res.getString(R.string.text_r1, r1);
		b1 = (Button)findViewById(R.id.button1);
		String chaineR2 = res.getString(R.string.text_r2, r2);
		b2 = (Button)findViewById(R.id.button2);
		String chaineR3 = res.getString(R.string.text_r3, r3);
		b3 = (Button)findViewById(R.id.button3);
		String chaineRC = res.getString(R.string.text_rc, rc);
		b4 = (Button)findViewById(R.id.button4);	
	
		ordreReponse(chaineRC, chaineR1, chaineR2, chaineR3);
		
	
		/*

		 * A partir d'ici, on a les écouteurs suivant quel bouton est appuyé.

		 */
//>			
		boutonJuste.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
 //>>           	// l'énigme est résolue dans le cas ou l'on parcourt la map
            	if(!pokemonDresseur.isDonjonEntre()){
            		enigme.setSolved(true);
	            	//pokemonDresseur.gagnerNiveau();
            	//	System.out.println("l'énigme est résolue");
	            //	System.out.println("^p:coord x "+x+" coord y:"+y);

	                Intent myintent2 = new Intent(LanceEnigmeTest.this,MapActivity.class);
	                
	                remplirIntentBon(myintent2);
	                pokemonGagnerVie(pokemonDresseur);
	                myintent2.putExtra("direction", direction);
	                startActivity(myintent2);
            	}
            	
//>>            	// si on est dans le donjon et qu'on a une énigme résolue, on passe dans un combat avec un pokemon défini
            	else if(pokemonDresseur.isDonjonEntre()){
            		//	System.out.println("L'énigme a été résolue > mtn combat");
	            	//pokemonDresseur.gagnerNiveau();
	                Intent myintent2 = new Intent(LanceEnigmeTest.this,CombatActivity.class);

	                remplirIntentBon(myintent2);
	                pokemonDresseur.setPdv(pokemonDresseur.getPointsDeVieMax());
	                compteur++; // on indique qu'on a passé l'étape.

	                
	                // suivant l'étape dans laquelle on se trouve, on attaque un pokemon déterminé ici
	                if(compteur==1){pokeDonjon=new Pidgeot();}
	                else if(compteur==3){pokeDonjon=new Dewgong();}
	                else if(compteur==5){pokeDonjon=new Gengar();}
	                else if(compteur==7){pokeDonjon=new Arcanine();}
	                else if(compteur==9){pokeDonjon=new Mewtwo();}
	         //       System.out.println("compteur enigme "+compteur);
	               
	                // dans le cas du donjon, il faut ajouter qqs attributs spécifiques pour la suite.
	                myintent2.putExtra("pokAtt", pokeDonjon); 
	                myintent2.putExtra("compteur", compteur);
	             //   System.out.println("compteur enigme après put extra "+compteur);
	                startActivity(myintent2); 
	                Toast.makeText(getApplicationContext(), "L'énigme a été résolue, mais ce lieu lugubre semble réserver de nombreuses surprises...", Toast.LENGTH_LONG).show();
        		}
            }
        });
		
		
//>
		boutonFaux1.setOnClickListener(new View.OnClickListener() {
			Intent myintent2 = new Intent(LanceEnigmeTest.this,MapActivity.class);
            public void onClick(View v) {
            	raterEnigme(myintent2);//ici, ya trois erreurs non résolues par geany et encore moins par moi meme.
            }
        });
		
//>
		boutonFaux2.setOnClickListener(new View.OnClickListener() {
			Intent myintent2 = new Intent(LanceEnigmeTest.this,MapActivity.class);
            public void onClick(View v) { 	
            	raterEnigme(myintent2);
            }
        });
		
//>
		boutonFaux3.setOnClickListener(new View.OnClickListener() {
			Intent myintent2 = new Intent(LanceEnigmeTest.this,MapActivity.class);
            public void onClick(View v) {	
            	raterEnigme(myintent2);
            }
        });	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lance_enigme_test, menu);
		return true;
	}
	
	public void onClick(View view){
		
	}
	
	/**
	 * transférer les attributs à l'intent pour les transmettre à la prochaine activité
	 * @param myintent2 l'intent dans laquelle on va.
	 */
	public void remplirIntentBon(Intent myintent2){
		myintent2.putExtra("pokeDresseur", pokemonDresseur);
        myintent2.putExtra("x",x);
    	myintent2.putExtra("y",y);
        myintent2.putExtra("enigme", enigme); // on renvoie l'�nigme pour savoir si elle a �t� r�solue ou pas
        myintent2.putExtra("coord", coord);
	}
	
	/**
	 * transférer les attributs à l'intent pour les transmettre à la prochaine activité
	 * @param myintent2 l'intent dans laquelle on va.
	 */
	public void remplirIntentFaux(Intent myintent2){
        myintent2.putExtra("pokeDresseur", pokemonDresseur);
        
        // si l'énigme n'est pas résolue, il faut revenir avant l'énigme
        if(direction.equals("haut")){
        	y++;
        	myintent2.putExtra("x",x);
        	myintent2.putExtra("y",y);
        	System.out.println("fait pour le haut");
        } else if(direction.equals("bas")){
        	y--;
        	myintent2.putExtra("x",x);
        	myintent2.putExtra("y",y);
        	System.out.println("fait pour le bas");
        } else if(direction.equals("gauche")){
        	x++;
        	myintent2.putExtra("x",x);
        	myintent2.putExtra("y",y);
        	System.out.println("fait pour le gauche");
        }else if(direction.equals("droite")){
        	x--;
        	myintent2.putExtra("x",x);
        	myintent2.putExtra("y",y);
        	System.out.println("fait pour le droite");
        	
        	// si on a fini le donjon, alors on a cette direction, on retourne sur la case de départ.
        }else if(direction.equals("aucune")){
        	x=47;
        	y=29;
        	myintent2.putExtra("x",x);
        	myintent2.putExtra("y",y);
        	System.out.println("retour à la case départ à cause de perte dans le donjon");
        }
        
        myintent2.putExtra("enigme", enigme); // on renvoie l'énigme pour savoir si elle a été résolue ou pas
        myintent2.putExtra("coord", coord); 
	}
	
	
	
	
	/**
	 * ce qui se passe lorsque l'on rate une énigme
	 * dommage sur le pokemon et retour à la case d'ou l'on vient.
	 * @param myintent2 l'intent ou l'on va (la carte)
	 */
	public void raterEnigme(Intent myintent2){
		Toast.makeText(getApplicationContext(), "Tu as lamentablement échoué, ton pokemon en subit les conséquences!!!", Toast.LENGTH_SHORT).show();
//>   
		if (pokemonDresseur.getPdv()>pokemonDresseur.getPointsDeVieMax()/5){
    		pokemonDresseur.perdrePDV(pokemonDresseur.getPointsDeVieMax()/5); // mauvaise réponse fait perdre 1/5 de la vie
    		remplirIntentFaux(myintent2);
    	
//>    		
    	}else{ // si la vie du pokemon est trop faible > il meurt et retour à la case départ.
    		pokemonDresseur.setPdv(pokemonDresseur.getPointsDeVieMax());
    		//la mort fait perdre de l'expérience.
    		if(pokemonDresseur.getpExperience()>500){
        		pokemonDresseur.setpExperience(pokemonDresseur.getpExperience()-500);
        	}else{ pokemonDresseur.setpExperience(0);}
    		Toast.makeText(getApplicationContext(), "Ton pokemon a trop souffert de ton ignorance. Tu dois le faire soigner pour te faire pardonner", Toast.LENGTH_LONG).show();
    		remplirIntentFaux(myintent2);
    		myintent2.putExtra("x",47); // retour à la case départ
        	myintent2.putExtra("y",29);
    	}
    	pokemonDresseur.setDonjonEntre(false); // on sort du donjon si on est mort.
    	startActivity(myintent2);
	}
	

	/**
	 * permet de recuperer les attributs présents dans l'intent et d'initialiser les attributs de cette activity
	 */
	public void recupererIntent(){
		Intent i = getIntent();
		enigme=(Enigme)i.getSerializableExtra("enigme");
		coord=(ArrayList<Integer>)i.getSerializableExtra("coord");	
		// récupération des coordonn�es de la carte pour les rendre après
		x=(Integer) i.getSerializableExtra("x");
		y=(Integer) i.getSerializableExtra("y");	
		//si on a besoin des pokemon pour leur faire perdre de la vie.
		pokemonDresseur= (Pokemon)i.getSerializableExtra("pokeDresseur");	
		direction= (String)i.getSerializableExtra("direction");
		// on commence à traiter le cas ou on est rentré dans le donjon: on récupère le compteur
		if(pokemonDresseur.isDonjonEntre()){
			compteur=(Integer)i.getSerializableExtra("compteur"); 
		}
	}
       
	
	/**
	 *
	 * Random pour que, suivant la valeur, les réponses(correctes et bonnes)
	 * ne soient pas mises dans le m�me ordre. Suivant l'ordre, il faut dire à chaque fois quel 
	 * est le bon bouton ainsi que les mauvais pour pouvoir associer les actions correspondantes	 
	 * @param chaineRC réponse correcte
	 * @param chaineR1 réponse fausse 
	 * @param chaineR2 réponse fausse
	 * @param chaineR3 réponse fausse
	 */
	public void ordreReponse(String chaineRC, String chaineR1, String chaineR2, String chaineR3){
   	 random=(int)(Math.random()*10);
		if (random==0){
			b4.setText(chaineRC);
			b2.setText(chaineR2);
			b3.setText(chaineR3);
			b1.setText(chaineR1);
			boutonJuste=b4;
			boutonFaux1=b1;
			boutonFaux2=b2;
			boutonFaux3=b3;
		}
		else if (random==1){
			b4.setText(chaineR2);
			b2.setText(chaineR3);
			b3.setText(chaineRC);
			b1.setText(chaineR1);
			boutonJuste=b3;
			boutonFaux1=b1;
			boutonFaux2=b2;
			boutonFaux3=b4;
		}
		else if (random==2){
			b4.setText(chaineRC);
			b2.setText(chaineR2);
			b3.setText(chaineR1);
			b1.setText(chaineR3);
			boutonJuste=b4;
			boutonFaux1=b1;
			boutonFaux2=b2;
			boutonFaux3=b3;
			
		}
		else if (random==3){
			b4.setText(chaineR2);
			b2.setText(chaineR1);
			b3.setText(chaineR3);
			b1.setText(chaineRC);
			boutonJuste=b1;
			boutonFaux1=b4;
			boutonFaux2=b2;
			boutonFaux3=b3;
		}
		else if (random==4){
			b4.setText(chaineRC);
			b2.setText(chaineR3);
			b3.setText(chaineR1);
			b1.setText(chaineR2);
			boutonJuste=b4;
			boutonFaux1=b1;
			boutonFaux2=b2;
			boutonFaux3=b3;
			
		}
		else if (random==5){
			b4.setText(chaineR3);
			b2.setText(chaineRC);
			b3.setText(chaineR1);
			b1.setText(chaineR2);
			boutonJuste=b2;
			boutonFaux1=b1;
			boutonFaux2=b4;
			boutonFaux3=b3;
		}
		else if (random==6){
			b4.setText(chaineR1);
			b2.setText(chaineRC);
			b3.setText(chaineR2);
			b1.setText(chaineR3);
			boutonJuste=b2;
			boutonFaux1=b1;
			boutonFaux2=b4;
			boutonFaux3=b3;
			
		}
		else if (random==7){
			b4.setText(chaineR1);
			b2.setText(chaineR3);
			b3.setText(chaineR2);
			b1.setText(chaineRC);
			boutonJuste=b1;
			boutonFaux1=b4;
			boutonFaux2=b2;
			boutonFaux3=b3;
		}
		else if (random==8){
			b4.setText(chaineR3);
			b2.setText(chaineR1);
			b3.setText(chaineRC);
			b1.setText(chaineR2);
			boutonJuste=b3;
			boutonFaux1=b1;
			boutonFaux2=b2;
			boutonFaux3=b4;
		}
		else if (random==9){
			b4.setText(chaineR3);
			b2.setText(chaineR2);
			b3.setText(chaineR1);
			b1.setText(chaineRC);
			boutonJuste=b1;
			boutonFaux1=b4;
			boutonFaux2=b2;
			boutonFaux3=b3;
		}
    }

	
	@Override
	/**
	 * action performed du bouton back
	 */
	public void onBackPressed() {
		//super.onBackPressed();
	    AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Tu veux vraiment sortir?")
	            .setCancelable(false)
	            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                	final Intent intent = new Intent(getBaseContext(), InsamonActivity.class);
	                	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	                	startActivity(intent);
	                }
	            })
	            .setNegativeButton("Non", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface dialog, int id) {
	                    dialog.cancel();
	                }
	            });
	    AlertDialog alert = builder.create();
	    alert.show();

	}
	
	
	/**
	 * C'est une méthode pour que le pokemon puisse gagner de la vie quand on resoudre un enigme
	 * @param p
	 */
	public void pokemonGagnerVie(Pokemon p){
		if((p.getPdv()+100)<=p.getPointsDeVieMax()){
			p.setPdv(p.getPdv()+100);
		}else{
			p.setPdv(p.getPointsDeVieMax());
		}
		
	}
}
