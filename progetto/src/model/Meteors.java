package model;

import javafx.scene.image.ImageView;

public class Meteors extends Oggetto{           //classe Meteors che eredità tutti i metodi della classe Oggetto

    private ImageView meteor;                   //variabile che conterrà l'immagine della meteora
    private final int METEOR_RADIUS=20;  //sorta di hitbox per la stella
    private final String METEOR_BROWN_IMAGE = "model/resources/meteor_brown.png";
    private final String METEOR_GREY_IMAGE = "model/resources/meteor_grey.png";

    public Meteors(int scelta){              //costruttore Meteors
        if(scelta==0)
            this.meteor = new ImageView(METEOR_BROWN_IMAGE);    //settiamo l'immagine del meteorite
        else
            this.meteor = new ImageView(METEOR_GREY_IMAGE);
    }

    @Override
    public void move() {                            //metodo move
        meteor.setLayoutY(meteor.getLayoutY()+7);   //settiamo la coordinata y del meteorite
        meteor.setRotate(meteor.getRotate()+4);     //settiamo il grado di rotazione del meteorite
    }

    @Override
    public ImageView getOggetto() {                    //metodo getOggetto
        return meteor;                                 //ritorniamo l'immagine del meteorite
    }

    @Override
    public int getRadius() {                            //metodo getRadius
        return METEOR_RADIUS;                           //ritorniamo la dimensione dell'hitbox
    }

}
