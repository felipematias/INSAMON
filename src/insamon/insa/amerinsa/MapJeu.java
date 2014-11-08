/**
 * 
 */
package insamon.insa.amerinsa;


import insamon.insa.amerinsa.elementCarte.Bridge;
import insamon.insa.amerinsa.elementCarte.D11;
import insamon.insa.amerinsa.elementCarte.D12;
import insamon.insa.amerinsa.elementCarte.D13;
import insamon.insa.amerinsa.elementCarte.D14;
import insamon.insa.amerinsa.elementCarte.D21;
import insamon.insa.amerinsa.elementCarte.D22;
import insamon.insa.amerinsa.elementCarte.D23;
import insamon.insa.amerinsa.elementCarte.D24;
import insamon.insa.amerinsa.elementCarte.D31;
import insamon.insa.amerinsa.elementCarte.D32;
import insamon.insa.amerinsa.elementCarte.D33;
import insamon.insa.amerinsa.elementCarte.D34;
import insamon.insa.amerinsa.elementCarte.D41;
import insamon.insa.amerinsa.elementCarte.D42;
import insamon.insa.amerinsa.elementCarte.D43;
import insamon.insa.amerinsa.elementCarte.D44;
import insamon.insa.amerinsa.elementCarte.Eau;
import insamon.insa.amerinsa.elementCarte.Elements;
import insamon.insa.amerinsa.elementCarte.ForestGround;
import insamon.insa.amerinsa.elementCarte.GrassBorder;
import insamon.insa.amerinsa.elementCarte.Herbe;
import insamon.insa.amerinsa.elementCarte.HerbeLeg;
import insamon.insa.amerinsa.elementCarte.Maison;
import insamon.insa.amerinsa.elementCarte.MountainGround;
import insamon.insa.amerinsa.elementCarte.MountainSide;
import insamon.insa.amerinsa.elementCarte.Path;
import insamon.insa.amerinsa.elementCarte.Soin;
import insamon.insa.amerinsa.elementCarte.SolHerbeux;
import insamon.insa.amerinsa.elementCarte.Traversable;
import insamon.insa.amerinsa.elementCarte.Tree;
import insamon.insa.amerinsa.pokemon.Pokemon;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


/**
 * @author Felipe Guillaume
 *
 */


public class MapJeu extends View{
	
	/*
	 * déclaration et initialisation des attributs
	 */
	
	private Personnage personnage; // le personnage est initialisé via le oncreate avec le personnage gï¿½nï¿½rï¿½ dans l'activité
	private Pokemon pokemonAttaquant; 
	private Pokemon pokemonDresseur;
	private Enigme enigmeChargee;
	private Bitmap b;
	private Paint crayon=new Paint();
	Canvas canvas=null;
	private int x=47; // si on modifie les coord de dé½part dans la map, il faut le faire aussi dans personnage!
	private int y=29;
	private int MAPX;
	private int MAPY;
	private int posP=0; // variable pour dïécrire l'orientation du personnage.
	private boolean combat=false;
	private boolean enigme=false;	
	private boolean estSoin=false;
	private boolean donjon=false; // est-ce que c'est le donjon?
	
	// attributs spécifiquement relatifs au remplissage de la carte
	private SolHerbeux sol= new SolHerbeux();
	private Herbe herbe= new Herbe();
	private GrassBorder gB= new GrassBorder();
	private ForestGround fG= new ForestGround();
	Tree t= new Tree();
	private MountainGround mG= new MountainGround();
	private MountainSide mS= new MountainSide();
	private Path p= new Path();
	private Eau e= new Eau();
	private Bridge br= new Bridge();
	private Soin so= new Soin();
	private Maison m= new Maison();
		
	Elements mapElements[][]= new Elements[84][68]; // tableau qui représente toute la carte en élément
	
	 /* création du tableau qui est en fait la fenetre visible
	  * ce tableau s'actualise en fonction des mouvements du personnage et ses bornes sont plus petites 
	  * que les bornes de la carte totale.
	  * Par ex, au début, la ligne i=0 de l'écran correspond àla ligne 3 de la carte totale.
	  * Lorsque le perso veut monter, la ligne 0 de l'écran correspond alors à la ligne 2 de la carte globale.
	  */
	Elements ecran [][] = new Elements[19][11]; // tableau qui ne représente que les éléments qui seront affichés sur téléphone (map réduite).
	
	
	// dÃ©claration du tableau qui va contenir la map
	
	private int [][] mapNb= 
	   {{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
	    {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,11,11,11,11,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,5,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,6,6,6,6,6,6,6,6,6,6,6,2,2,2,2,2,2,2,3,2,2,2,2,2,2,3,11,11,11,11,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,5,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,2,2,2,2,2,2,2,3,3,3,2,2,2,2,3,11,11,11,11,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,5,6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,6,2,2,2,2,2,2,2,2,2,3,2,3,2,2,3,11,11,11,11,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,5,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,5,6,6,2,2,2,2,2,2,2,2,2,3,3,3,2,2,3,3,3,6,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,5,5,5,5,5,5,5,5,5,5,5,5,5,5,4,4,4,4,4,5,4,4,4,4,4,4,5,6,6,6,6,6,6,6,6,6,2,2,2,2,3,2,2,2,2,2,6,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,6,5,4,4,4,4,4,4,6,6,6,6,6,6,5,4,4,4,4,4,5,4,4,4,4,4,4,5,6,6,6,6,6,6,6,6,6,2,2,2,3,3,2,2,2,2,2,6,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,6,4,4,4,4,4,4,4,6,5,4,4,4,6,5,5,5,5,5,5,5,4,4,4,4,4,4,5,2,2,2,2,2,2,2,6,6,2,2,2,3,2,2,2,6,6,6,6,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,5,4,4,4,6,4,5,5,6,6,6,6,6,6,6,6,6,6,5,2,2,2,2,2,2,2,8,8,3,3,3,3,3,3,3,6,6,2,2,3,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,4,4,4,4,4,4,4,4,5,4,4,4,6,6,5,5,6,4,4,4,4,4,4,4,4,5,5,2,7,7,7,7,7,7,8,8,7,7,7,7,7,7,7,6,6,7,7,7,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,4,4,4,4,4,4,4,4,5,5,5,5,5,6,5,5,6,5,5,5,5,5,5,5,5,5,2,2,7,7,7,7,7,7,8,8,7,7,7,7,7,7,7,6,6,7,7,7,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,4,4,5,5,4,4,4,4,4,4,4,4,5,6,5,5,6,5,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,1,8,8,1,1,1,1,1,1,1,6,6,1,1,1,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,4,4,5,5,4,4,4,4,4,4,4,4,5,4,4,6,6,5,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0,6,6,0,0,0,0,0,0,0,6,6,0,0,1,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,4,4,5,5,4,4,4,4,4,4,4,4,5,4,4,6,6,5,7,7,7,7,7,7,7,7,7,7,1,1,1,1,1,0,6,6,0,0,0,0,0,0,0,6,6,0,0,1,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,4,4,4,4,4,4,4,4,4,4,4,4,4,5,4,4,6,6,5,7,7,7,7,7,7,7,7,7,7,1,0,0,0,0,0,6,6,0,0,0,0,0,0,0,6,6,0,0,1,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,5,7,7,7,7,7,7,7,7,7,7,1,0,0,0,0,0,6,6,0,0,0,0,0,0,10,0,0,10,0,1,3,3,3,3,3,3,3,3,3},
		{5,5,5,5,5,5,5,5,5,5,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,0,0,0,0,0,6,6,0,0,0,0,0,0,0,9,0,10,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,5,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,1,6,6,6,6,6,6,6,6,6,6,6,6,6,0,10,0,10,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,6,6,3,3,3,3,3,3,3,7,3,3,3,3,3,3,3,1,1,1,1,1,1,1,1,1,1,1,1,1,6,6,6,6,6,6,6,6,6,6,6,6,6,0,0,0,10,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,6,6,6,6,6,6,6,6,6,7,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,6,6,6,6,6,6,6,6,6,7,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,6,6,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,2,2,2,2,2,7,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,0,0,1,1,1,1,1,1,1,0,0,0,0,6,6,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,2,2,2,2,2,7,2,2,2,2,2,2,2,0,0,0,0,0,0,0,0,6,6,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0,0,6,6,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,2,2,2,2,2,7,2,2,2,2,2,2,2,0,0,0,0,0,0,0,1,6,6,0,0,0,0,0,0,0,1,0,1,1,1,1,1,0,0,0,0,6,6,0,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,2,2,2,2,2,7,2,2,3,3,3,2,2,0,0,0,0,0,0,0,1,6,6,0,0,0,0,1,1,1,1,0,1,0,0,1,1,0,0,0,0,6,6,6,1,3,3,3,3,3,3,3,3,3},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,2,2,2,2,2,7,2,2,3,2,3,2,2,0,0,0,0,0,0,0,1,6,6,0,0,0,0,0,0,0,1,0,1,0,0,1,1,0,0,0,0,0,6,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,2,2,2,2,2,7,2,2,3,2,3,2,2,1,1,1,1,1,1,1,1,6,6,0,0,0,0,0,0,0,1,0,1,0,0,1,1,0,0,0,0,0,0,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,2,2,2,2,2,7,2,2,2,2,2,2,2,1,6,6,6,6,6,6,6,6,6,0,0,0,0,0,0,0,1,0,1,0,0,1,1,0,0,0,0,0,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,6,6,6,6,8,8,8,2,2,2,2,2,2,1,6,6,6,6,6,6,6,6,6,0,0,0,0,0,0,0,1,0,0,0,0,1,1,0,0,0,0,0,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,6,6,6,6,6,6,2,7,2,2,2,2,2,2,2,1,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,1,1,0,0,0,0,0,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,6,6,2,7,2,2,2,2,2,2,2,1,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,5,5,5,5,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,6,6,2,7,7,7,7,7,7,2,2,3,3,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,5,4,4,4,4,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,6,6,2,7,7,7,7,7,7,2,2,3,3,2,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,5,4,4,4,4,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,6,6,2,2,2,2,2,7,7,2,2,3,3,2,2,2,2,2,2,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,5,4,4,4,4,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,0,0,0,0,6,6,0,0,0,0,0,7,7,7,2,3,3,2,2,2,2,2,2,0,0,0,0,0,0,0,1,7,7,7,7,7,7,7,7,7,7,7,7,4,6,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,0,0,0,0,6,6,0,0,0,0,0,7,7,7,7,3,3,2,2,2,2,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,4,6,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,0,0,0,0,6,6,0,0,0,0,0,0,0,1,7,3,3,2,2,2,2,7,0,0,0,0,0,0,0,0,0,7,7,7,7,7,5,5,5,5,5,5,5,4,6,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,0,0,0,0,6,0,0,0,0,0,0,0,0,1,7,3,3,6,6,2,2,7,0,0,0,0,0,0,0,0,0,7,7,7,7,7,5,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,0,0,0,0,6,0,0,0,0,0,0,0,0,1,7,3,3,3,8,2,2,7,0,0,0,0,0,0,0,0,0,7,7,7,7,7,5,6,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,1,0,0,0,0,0,0,6,0,0,0,0,0,0,0,0,1,7,7,7,7,8,7,7,7,0,0,0,0,0,0,0,0,0,7,7,7,7,7,5,6,5,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,7,1,0,0,8,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,5,6,5,4,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,7,1,0,0,6,0,0,0,0,0,0,0,0,0,0,0,0,7,7,7,7,7,5,6,5,4,6,5,5,5,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,1,0,8,0,0,0,0,0,0,0,0,0,0,0,0,0,1,7,1,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,7,7,7,5,5,5,6,5,4,6,5,6,6,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,1,7,8,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,5,4,4,6,5,4,6,5,6,6,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,1,7,8,7,7,7,7,7,7,7,7,7,7,3,3,3,3,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,5,4,5,6,5,4,6,5,4,6,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,8,2,2,2,2,2,2,2,2,2,2,3,2,9,3,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,5,5,5,5,4,5,6,5,4,6,6,6,6,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,2,3,3,3,3,2,2,2,2,2,3,2,2,3,2,2,2,3,3,3,2,2,2,2,2,2,2,2,2,4,4,4,4,4,4,4,5,6,5,5,5,5,5,5,5,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,3,3,3,3,3,3,3,2,2,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,4,4,4,4,4,4,4,5,6,6,6,6,6,6,6,6,6,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,5,4,4,4,4,5,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
		{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5}};
		

		
	
	/*surcharge des constructeurs
	 * 
	 */
		public MapJeu(Context context) {
			super(context);	
			// TODO Auto-generated constructor stub
		}
		
		public MapJeu(Context context, Personnage personnage) {
			super(context);		
			this.personnage=personnage;	
			// TODO Auto-generated constructor stub
		}

		public MapJeu(Context context, AttributeSet attrs) {
			super(context, attrs);
			// TODO Auto-generated constructor stub
		}

		public MapJeu(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			// TODO Auto-generated constructor stub
		}

		
		
		
	/*accesseurs
	 * 
	 */

	public void setEnigme(boolean enigme) {
		this.enigme = enigme;
	}
	public Enigme getEnigme() {
		return enigmeChargee;
	}
	public void setDonjon(boolean donjon) {
		this.donjon = donjon;
	}
	public void setEstSoin(boolean estSoin) {
		this.estSoin = estSoin;
	}
	public boolean isEstSoin() {
		return estSoin;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Pokemon getPokemonDresseur() {
		return pokemonDresseur;
	}
	public void setPokemonDresseur(Pokemon pokemonDresseur) {
		this.pokemonDresseur = pokemonDresseur;
	}
	public Pokemon getPokemonAttaquant() {
		return pokemonAttaquant;
	}
	public boolean isCombat() {
		return combat;
	}
	public boolean isEnigme() {
		return enigme;
	}
	public Personnage getPersonnage() {
		return personnage;
	}
	public void setPersonnage(Personnage personnage) {
		this.personnage=personnage;				
	}
	
	
		 
	 

		/*Cett méthode sert à recuperer la taille de l'ecran pour que l'application soit compatible 
		 * quelque soit la taille de l'ecran, comme on fait un app pour des androides 2.2 j'ai du 
		 * utiliser display.getWidth() et display.getHeight()...
		 */
		
		public void setScreenSize(){
			WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			MAPX = display.getWidth();  // deprecated
			MAPY= display.getHeight();  // deprecated
		}
		
		
		
		
		
/**
 * boucle qui sert à transformer le tableau d'entier en tableau d'élément.
 * On y ajoute à des positions définies, certains éléments spécifiques: énigmes, et le donjon.
 */
	 public void initialiserTableaux(){
		 for(int i=0;i<84;i++){
			  	for(int j=0;j<68;j++){
			  		if(mapNb[i][j]==0){
						mapElements[i][j]=sol;}
			  		else if(mapNb[i][j]==1){
						mapElements[i][j]=gB;}		  		
			  		else if(mapNb[i][j]==2){
						mapElements[i][j]=fG;}
					else if(mapNb[i][j]==3){
						mapElements[i][j]=t;}
					else if(mapNb[i][j]==4){
						mapElements[i][j]=mG;}
					else if(mapNb[i][j]==5){
						mapElements[i][j]=mS;}
					else if(mapNb[i][j]==6){
						mapElements[i][j]=p;}
					else if(mapNb[i][j]==7){
						mapElements[i][j]=e;}
					else if(mapNb[i][j]==8){
						mapElements[i][j]=br;}
					else if(mapNb[i][j]==9){ // pour le moment car je n'ai pas toutes les images.
						mapElements[i][j]=so;}
					else if(mapNb[i][j]==10){
						mapElements[i][j]=m;}
			  	}
			  }
		 	remplirDonjon();
		 	remplirEnigme();
		 	
	 }
	 
	 /**
	  * permet de rajouter les cases du donjon sur la carte. Le donjon a été ajouté après, d'ou le fait que le donjon soit dessus.
	  */
	 public void remplirDonjon(){
			mapElements[17][55]= new D11();
			mapElements[18][55]= new D21();
			mapElements[19][55]= new D31();
			mapElements[20][55]= new D41();
			mapElements[17][56]= new D12();
			mapElements[18][56]= new D22();
			mapElements[19][56]= new D32();
			mapElements[20][56]= new D42();
			mapElements[17][57]= new D13();
			mapElements[18][57]= new D23();
			mapElements[19][57]= new D33();
			mapElements[20][57]= new D43();
			mapElements[17][58]= new D14();
			mapElements[18][58]= new D24();
			mapElements[19][58]= new D34();
			mapElements[20][58]= new D44();
		}
		
	/**
	 * permet de rajouter les cases du donjon sur la carte. Le donjon a été ajouté après, d'ou le fait que le donjon soit dessus.
	 */
	public void remplirEnigme(){
  
         mapElements[18][10]=new Enigme("Ce passage est en restauration ! Un ouvrier reconstruit un mur en une heure. Combien faut-il d’ouvriers pour reconstruire 6 murs en 6 heures ?","6","3","2","1");
         mapElements[21][57]=new Enigme("Attention ! Quelqu’un a lancé un gaz dangereux près de vous ! Pour que nous puissions le retirer pour vous, répondez à la question suivante : quelle grandeur thermodynamique est liée au fait que le gaz se diffuse dans tout l’espace ?","l’enthalpie, dont la tendance est augmenter (plus grande désordre du système)"," l’enthalpie, dont la tendance est diminuer (plus petite désordre du système)"," l’entropie, dont la tendance est diminuer (plus petite désordre du système)","l’entropie, dont la tendance est augmenter (plus grande désordre du système)");
         mapElements[44][57]=new Enigme("Oups ! Il y a plein d’acide corrosif par terre ! Vous et votre pokemon êtes en danger! Vous disposez par contre de différentes substances pour pouvoir neutraliser l’acide et continuer votre mission. Qu’est-ce que vous utilisez et quelle est la réaction mise en jeu ?","CuSO4, une base ; H2SO4 (aq) +CuSO4 (aq)  → CuO(s)  + H2O (l)","CuO,un sel ; H2SO4 (aq) + CuO(s) → CuSO4 (aq) + H2O (l)","CuSO4, un sel; H2SO4 (aq) +CuSO4 (aq)  → CuO(s)  + H2O (l)","CuO, une base ;  H2SO4 (aq) + CuO(s) → CuSO4 (aq) + H2O (l)");
         mapElements[39][48]=new Enigme("Vous êtes perdu ! La commande utilisée en informatique et permettant d'afficher le nom du répertoire courant sur la sortie standard  va aussi pouvoir vous localiser !!! Quel est cette commande ?"," cd (“change directory”)","pwd (“print working documents”)","cd (“choose directory”)"," pwd (“print working directory”)");
         mapElements[18][29]=new Enigme("Vous venez d’arriver à une arène de combat. Un tournoi entre n joueurs est organisé. Le principe est l'élimination directe : un joueur qui a perdu le combat ne peut participer à d'autres combats. Quel est le nombre de parties jouées (finale comprise) en fonction du nombre de joueurs?","n","2n-1","n+1","n-1");
         mapElements[19][29]=new Enigme("L'endroit où vous arrivez fut jadis habité par des romains. Pour pouvoir le traverser, il faut donc compléter le code qui a été partiellement effacé. Il semble ne manquer qu'un nombre. Rappelez vous vos cours d'histoire des pokemons et remplacez le ? par le chiffre manquant: ? XXIV 12 II - XIV 2 VII - XXXII 4 VIII - XXI ? III","8","15","3","7");
         mapElements[28][45]=new Enigme("Votre pokemon a grand faim ! Vous arrivez au point où vous pouvez le nourrir. Cependant, trop ou trop peu serait fatal à sa santé. Vous devez donc essayer de comprendre le besoin de votre pokemon. Combien de fruits a-t-il besoin? 1 ; 0 ; 2 ; 1 ; 3 ; 2 ; 4 ; ?","6","2","1","3");
         mapElements[28][46]=new Enigme("Ce passage vous fournira un important instrument pour la suite de votre parcours. Sachant que, parmi plusieurs instruments disponibles, vous devez choisir celui avec une intensité maximale de contrainte prédéterminée, quelle technique utilisez-vous pour choisir l’instrument correcte ? Quelle information cette technique peut aussi vous fournir ?","Photoélasticimétrie. Elle fournit aussi la température de fusion d’un matériel","Photoplasticimétrie. Elle fournit aussi le point d’application d’une contrainte","Photoplasticimétrie. Elle fournit aussi la température de fusion d’un matériel","Photoélasticimétrie. Elle fournit aussi le point d’application d’une contrainte");
         mapElements[60][54]=new Enigme("Le chemin que êtes sur le point d'emprûnter est décrit par un rayon de convergence R. Comme le rayon de convergence d’une série entière en Mathématiques, qu’est ce qui se passe à l’intérieur de ce chemin (lorsque r<R), à la frontière de ce chemin (r=R) et à l’extérieur du chemin (r>R), respectivement ?","Divergence, divergence, on ne sait rien dire","Divergence, on ne sait rien dire, convergence","Convergence, convergence, divergence","d)Convergence, on ne sait rien dire, divergence");
         mapElements[60][48]=new Enigme("Une liaison pivot présente dans la porte d’entrée de ce passage a un défaut, ce qui vous empêche de traverser. Pour vous débarrassez de cela, il faut que vous fassiez tout d’abord une étude des actions mécaniques appliquées sur la porte, et puis proposer une solution pour la régler. Dans le cas où une liaison pivot est appliquée sur un solide isolé, quel théorème devrons-nous utiliser ?","Théorème de la résultante dynamique au point d’application de la pivot","Théorème du moment dynamique à un point quelconque","Théorème de la résultante dynamique suivant l’axe de rotation de la pivot","Théorème du moment dynamique suivant l’axe de rotation de la pivot");
         mapElements[32][27]=new Enigme("Attention ! En vous engageant sur ce chemin, vous entendez le bruit de sauvages cannibales brésiliens qui s'apprêtent à faire un festin des prochains bizuths en tongues! Cependant, si vous choisissez le chemin où ces créatures sont moins nombreuses, vous pourriez peut-être les saouler avec de la cachaça. Vous entendez la conversation suivante entre 2 créatures, chacune dans son chemin : « Si un de vous du chemin B nous rejoint, nous serons alors 2 fois plus que vous, par contre si un de nous du chemin A vous rejoint nous serons alors à l’égalité ». Combien y a-t-il de cannibales dans les chemins A et B, respectivement ?","5 et 3","7 et 5","3 et 5","5 et 7");
         mapElements[35][10]=new Enigme("Vous arrivez à un endroit très dangereux (comme à Sao Paolo), habité par des étranges créatures. Vous devez donc être très attentif à tous les bruits inconnus ! D’ailleurs, en physique acoustique, que représente la variable p(x,t)?","amplitude de l’onde","surpression globale instantanée","nombre d'onde","surpression locale instantanée");
         mapElements[35][11]=new Enigme("Zut ! Vous avez perdu la clef qui ouvre ce passage ! Par contre, en tapant le bon code sur la porte, vous pouvez quand même la traverser. Il vous faut taper le chiffre x de la séquence ci-dessous :2+3=10, 6+5=66, 3+4=21, 7+2=63, 9+7=X ","106","88","79","144");
         mapElements[35][11]=new Enigme("Zut ! Vous avez perdu la clef qui ouvre ce passage ! Par contre, en tapant le bon code sur la porte, vous pouvez quand même la traverser. Il vous faut taper le chiffre x de la séquence ci-dessous :2+3=10, 6+5=66, 3+4=21, 7+2=63, 9+7=X ","106","88","79","144");
         mapElements[42][23]=new Enigme("Dans ce passage, vous récupérez des potions magiques qui seront très utiles à vos pokemons.  Soient deux verres 1 et 2, remplis respectivement des potions A et B. Les volumes sont identiques. Vous prenez une cuillère du liquide  B que vous versez dans le verre 1. Après avoir remué, vous versez dans le verre 2 une cuillère du mélange. Y a-t-il alors plus de B dans le verre 1 ou de A dans le verre 2 ?","La quantité de A dans le verre 1 est supérieure à celle de B dans 2, et inversement","La quantité de A dans le verre 1 est inférieure à celle de B dans 2, et inversement","Il n’y a pas assez d’info pour répondre à la question","La quantité de A dans le verre 1 est égale à celle de B dans 2, et inversement");
         mapElements[45][19]=new Enigme("Le pont est cassé! Vous disposez, par contre, des 3 différents morceaux de bois (B1, B2, B3) pour pouvoir traverser la rivière. Sachant que les volumes de  B1, B2 et B3 font, respectivement, 28 cm3, 13 cm3, 35 cm3 et que le bois ne peut être qu'à 20% immergé dans l'eau, quel sera le bois adéquat à choisir pour traverser la rivière? Est-ce qu'on a besoin de la valeur de la g (gravité en m/s2) pour ce calcul?  Données: masse volumique de l'eau=1000kg/m3; masse volumique du bois=500kg/m3;  votre masse = 70kg","b","c","d","f"); //incomplet?
         mapElements[56][30]=new Enigme("Sur ce passage, à l'aller, vous mettez 1h20 en allant à 10km/h. Par contre, pour traverser ce même passage, mais au retour, vous mettez 80 min en allant toujours à 10km/h. Qu’est-ce qui se passe ?  ","A l'aller, en découvrant encore le chemin, c’est plus dur","Vous ne faites pas de chemin droit à l’allée, contrairement au retour","Vous faites une petite pause de 7 minutes à l’aller","rien");
         mapElements[63][23]=new Enigme("Des pokemons en colère arrivent vers vous. Vous êtes au total 15 personnes au bord du lac et vous devez fuir d’ici vingt minutes, mais le pont est cassé. Il n'y a qu'un canot de sauvetage qui ne peut contenir que 5 personnes à la fois. L'eau est infestée de requinmons et l’aller-retour de ce passage nécessite 9 minutes. Combien de personnes vont être sauvées ?","11","14","10","13");
         mapElements[63][24]=new Enigme("Vous arrivez dans une région du monde où le soleil n'est plus, où l'obscurité règne ! Vous rencontrez un magicien qui habite dans la forêt et il vous demande de lui rendre un service. Pour pouvoir traverser ce passage, vous devez donc trouver deux pommes de la même couleur pour qu’il fasse sa potion magique. Sachant qu’il y en a 10 pommes rouges et 10 vertes par terre, combien de pomm es devez-vous prendre pour être sûr d’en avoir 2 de la même couleur ?","11","10","2","3");

		}
	
	
	
	
	/**
	 *  boucle qui remplit l'écran et qui dessine au fur et à mesure pour aller un peu plus vite 
	 * @param x coordonnée x de l'écran 
	 * @param y coordonnée y de l'écran
	 */
	public void remplirEcran(int x, int y){
		for(int j=x;j<x+16;j++){
			for(int i=y;i<y+9; i++){
				/*
				 * on utilise deux variables:
				 * i et j représentent le système de coordonnées de la map alors que x et y se rapportent eux au système de l'écran.
				 * On fait en sorte que les coordonnées de l'écran correspondent à (0,0), d'où les j-x et i-y
				 */
				ecran[j-x][i-y]=mapElements[i][j];  // on récupère les éléments de la map
				b=ecran[j-x][i-y].chargerImage(this.getResources());// on les dessine
				setScreenSize();
				canvas.drawBitmap(b, (MAPX/15)*(j-x), (MAPY/9)*(i-y), crayon);
			}
		}
	}
	

	
	/**
	 * On souhaite tester si l'élement est traversable.
	 * Le pb est qu'on a un tableau d'éléments donc je ne peux pas faire instanceof traversable, car l'élément 
	 * est trop général, il faut avoir le type particulier (dynamique) de chaque élément.
	 * En effet, tous les éléments ne sont pas traversables.
	 * Cette méthode est donc une parade, mais du coup, on n'exploite pas le fait que ça implémente traversable..
	 * et on perd en facilité d'ajout d'éléments traversables ou en modification.
	 * On aurait aussi pu passer par une ArrayList, dans laquelle il aurait suffit de tester si l'élément était présent.
	 * @param e élement que l'on souhaite traverser et donc qu'il faut tester
	 * @return true ou false suivant si l'élement est effectivement traversable.
	 */
	public boolean estTraversable(Elements e){
		boolean res= false;
		if(e instanceof Bridge || e instanceof ForestGround || e instanceof Herbe || 
		e instanceof MountainGround || e instanceof Path || e instanceof Soin ||
		e instanceof SolHerbeux ||e instanceof Enigme ||e instanceof HerbeLeg){
			res=true;
		}
		return res;
	}
	
	/**
	 * permet de dire si on se trouve à l'entrée du donjon ou non. 
	 * L'entrée est donnée par une coordonnée fixée
	 * @return est-on dans le donjon?
	 */
	public boolean isDonjon(){
		if(x==50 && y==18){ donjon=true;}
		else{donjon=false;}
		return donjon;
	}
	
	/* 
	 * méthode OnDraw, c'est un peu la méthode main d'une View.
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.canvas=canvas;
		
		/* il faut initialiser le tableau de départ (la carte initiale lorsqu'on arrive sur le jeu.
		 * Par défaut, on centrera sur le village, et après chargement du fichier, on reprendra là où on était.
		 */
		x=personnage.getX();
		y=personnage.getY();
		this.remplirEcran(x,y); // coordonnées actualisées en fonction des mouvements du personnage> on actualise l'écran par rapport à la map.
		this.updatePerso(posP);// on met à jour l'orientation du personnage suivant son déplacement.
		canvas.drawBitmap(b, (MAPX/15)*7, (MAPY/9)*4, null);  
 /*
  * perso en case x=8, y =5/ mais attention, 
  * les éléments sont numérotés en partant de 0			
  * d'où le décalage de 1.
  */
	}
	
	
	/**
	 * Change l'image du personnage en fonction de sa direction.
	 * @param posP variable qui dépend du bouton sur lequel on a appuyé > direction dans laquelle va le personnage.
	 */
		public void updatePerso(int posP){
			if (posP==0){b=personnage.chargerImageFront(this.getResources());}
			else if(posP==1){b=personnage.chargerImageBack(this.getResources());}
			else if(posP==2){b=personnage.chargerImageRight(this.getResources());}
			else if(posP==3){b=personnage.chargerImageLeft(this.getResources());}
		}
	
		
		/*
		 * deplacement de la carte
		 */
		public void deplacerGauche(){
			if(this.estTraversable(ecran[6][4])){ // on regarde la case ï¿½ gauche
				posP=3;
				personnage.deplacerGauche();
				y=personnage.getY();// on actualise la coordonnï¿½es qui a bougï¿½
				deplacer(6,4);
			}else {System.out.println("mur");}		
		}
		
		
		
		
		public void deplacerDroite(){
			if(this.estTraversable(ecran[8][4])){ // on regarde la case ï¿½ droite
				posP=2;
				personnage.deplacerDroite();	
				y=personnage.getY();
				deplacer(8,4);	
			}else {System.out.println("mur");}	
		}
		
		
		
		
		public void deplacerHaut(){
			System.out.println("MapJeu avant deplacement :coord x "+x+" coord y:"+y);
			if(this.estTraversable(ecran[7][3])){ // on regarde la case en haut
				personnage.deplacerHaut();
				x=personnage.getX();
				posP=1;
				deplacer(7,3);
			}else {System.out.println("mur");}
			System.out.println("MapJeu aprÃ¨s deplacement :coord x "+x+" coord y:"+y);
		}
		
		
		
		
		public void deplacerBas(){
			if(this.estTraversable(ecran[7][5])){ // on regarde la case en bas
				posP=0;
				personnage.deplacerBas();
				x=personnage.getX();
				deplacer(7,5);
			}else {System.out.println("mur");}	
			
			
		}
		
		
		
		/**
		 * permet de déplacer la carte si c'est possible (traversable) en testant le type de case sur lequel on arrive.
		 * @param x coordonnée x
		 * @param y coordonnée y
		 */
		public void deplacer(int x, int y){
			
			//arrive-t-on sur une énigme?
			if((ecran[x][y]) instanceof Enigme){
				enigme=true;
				isDonjon();
				enigmeChargee=(Enigme)ecran[x][y];
			}
			// sur une case soin?
			if(ecran[x][y] instanceof Soin){
			
				((Soin)ecran[x][y]).action(pokemonDresseur);
				estSoin=true;
			}
			// sur une case où il peut y avoir un combat?
			if(((Traversable)ecran[x][y]).action(personnage)){
				combat=true;
			}
			pokemonAttaquant=ecran[x][y].getPokemonAttaquant();	
			invalidate();		
			
		}
				
}
