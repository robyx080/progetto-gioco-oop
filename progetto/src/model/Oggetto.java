package model;

import javafx.scene.image.ImageView;

import java.util.Random;

public class Oggetto extends ImageView{

    public Random randomPositionGenerator = new Random();  //variabile random che utilizziamo per posizionare in maniera random gli elemnti del gioco

    public Oggetto(){                                      //costruttore oggetto
    }

    public void move(){                                    //metodo move
    }

    public void checkIfElementsAreBehindTheShipAndRelocate(ImageView oggetto){   //metodo checkIfElementAreBehindTheShipAndRelocate
        if(oggetto.getLayoutY()>900){                            //se l'oggetto supera la coordinata y 900 lo riposizioniamo
            setNewElementPosition(oggetto);                      //richiamiamo il metodo setNewElementPosition
        }
    }

    public void setNewElementPosition(ImageView oggetto){                        //metodo setNewElementPosition
        oggetto.setLayoutX(randomPositionGenerator.nextInt(550));         //settiamo la nuova coordinata x dell'oggetto
        oggetto.setLayoutY(-(randomPositionGenerator.nextInt(370)+600));  //settiamo la nuova coordinata y dell'oggetto
    }

    public ImageView getOggetto(){     //metodo getOggetto
        return null;                   //ritorniamo null
    }

    public double getPositionX(ImageView oggetto){      //metodo getPositionX
        return oggetto.getLayoutX();                    //ritorniamo la coordinata x dell'oggetto
    }

    public double getPositionY(ImageView oggetto){      //metodo getPositionY
        return oggetto.getLayoutY();                    //ritorniamo la coordinata y dell'oggetto
    }

    public int getRadius() {           //metodo getRadius
        return 0;                      //ritorniamo 0
    }

}
