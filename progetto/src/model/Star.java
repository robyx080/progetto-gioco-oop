package model;

import javafx.scene.image.ImageView;


public class Star extends Oggetto{                  //classe Star che eredità tutti i metodi della classe Oggetto

    private final String GOLD_STAR_IMAGE= "model/resources/star_gold.png";  //variabile che conterrà il percorso dell'immagine della stella
    private ImageView star;                         //variabile che conterrà l'immagine della stella
    private final int STAR_RADIUS=12;               //sorta di hitbox per la stella


    public Star(){                                  //costruttore star
        star=new ImageView(GOLD_STAR_IMAGE);        //settiamo l'immagine della stella
    }

    @Override
    public void move() {                            //metodo move
        star.setLayoutY(star.getLayoutY()+5);       //settiamo la coordinata y della stella
    }

    @Override
    public ImageView getOggetto() {                 //metodo getOggetto
        return star;                                //ritorniamo l'immagine della stella
    }

    @Override
    public int getRadius() {                        //metodo getRadius
        return STAR_RADIUS;                         //ritorniamo la dimensione dell'hitbox
    }

}
