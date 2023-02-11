package model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.image.ImageView;

public class ShipPicker extends VBox {             //classe ShipPicker che eredità tutti i metodi della classe Vbox

    private ImageView circleImage;                 //variabile che conterrà l'immagine del bottone non selezionato per la scelta delle navi
    private ImageView shipImage;                   //variabile che conterrà l'immagine della nave

    private String circleNotChoosen = "model/resources/shipchooser/grey_circle.png";    //percorso del bottone vuoto per la scelta delle navi
    private String circleChoosen = "model/resources/shipchooser/red_choosen.png";       //percorso del bottono selezionato per la scelta delle navi

    private SHIP ship;                             //variabile che conterra la singola nave

    private boolean isCircleChoosen;               //variabile che ci permetterà di capire se il bottone è stato cliccato o meno


    public ShipPicker(SHIP ship){                  //costruttore ShipPicker
        circleImage=new ImageView(circleNotChoosen);  //creazione immagine del bottone non selezionato
        shipImage = new ImageView(ship.getUrl());  //creazione immagine della nave
        this.ship = ship;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);             //settiamo la posizione del singolo Vbox
        this.setSpacing(20);                       //settiamo lo spazio laterale che ci deve essere tra due vbox
        this.getChildren().add(circleImage);       //aggiungiamo alla Vbox l'immagine del bottone
        this.getChildren().add(shipImage);         //aggiungiamo alla Vbox l'immagine della nave
    }

    public SHIP getShip(){                         //metodo getShip

        return ship;                                //ritorniamo il valore della nave

    }

    public void setCircleChoosen(boolean isCircleChoosen){    //metodo setCircleChoosen
        this.isCircleChoosen = isCircleChoosen;               //settiamo isCircleChoosen
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;    //segliamo l'immagine del bottone tra quella non selezionato e quella selezionato
        circleImage.setImage(new Image(imageToSet));          //settiamo l'immagine nuova
    }

}
