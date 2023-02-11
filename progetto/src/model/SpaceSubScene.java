package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SpaceSubScene extends SubScene {           //classe SpaceRunnerSubScene che eredità tutti i metodi della classe SubScene

    private final static String BACKGROUND_IMAGE = "model/resources/yellow_panel.png";    //percorso dell'immagine di background

    private boolean isHidden=true;                            //variabile che ci permetterà di capire se la sottoscena è nascosta o meno

    public SpaceSubScene() {                            //costruttore SpaceRunnerSubScene
        super(new AnchorPane(), 600, 400);
        prefHeight(400);
        prefWidth(600);
                                                              //settiamo il Background
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,600,400,false,true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        AnchorPane root2 = (AnchorPane) this.getRoot();       //creiamo un altra variabile AnchorPane
        root2.setBackground(new Background(image));           //aggiungiamo il background a root2 (simil mainPane)

        setLayoutY(180);                                      //settiamo le coordinate della sottoscena
        setLayoutX(2348);                                     //settiamo le coordinate della sottoscena
    }

    public void moveSubScene(){                               //metodo moveSubScene
        TranslateTransition transition = new TranslateTransition();     //creiamo una variabile TranslateTransition che ci permetterà di cambiare le scene animandole
        transition.setDuration(Duration.seconds(0.3));        //settiamo la durata dell'animazione
        transition.setNode(this);                             //settiamo quale sottoscena deve essere animata
        if(isHidden) {                                        //se isHiden è true mostriamo la sottoscena
            transition.setToX(-2000);
            isHidden=false;
        }else{                                                //altrimenti non mostri la sottoscena
            transition.setToX(0);
            isHidden=true;
        }
        transition.play();                                    //facciamo partire l'animazione del cambio scena
    }

    public AnchorPane getPane(){                              //metodo getPane

        return (AnchorPane) this.getRoot();

    }

}
