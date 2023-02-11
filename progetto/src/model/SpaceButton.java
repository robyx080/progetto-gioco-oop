package model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceButton extends Button {        //classe SpaceRunnerButton che eredità tutti i metodi della classe Button

    private final String FONT_PATH = "src/model/resources/font.ttf";        //percorso del font che utilizzeremo per la scrittura all'interno del gioco
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/yellow_button01.png'); ";    //percorso dell'immagine del bottone pressato
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/model/resources/yellow_button00.png'); ";       //percorso dell'immagine del bottone non pressato

    public SpaceButton(String text){             //costruttore SpaceRunnerButton
        setText(text);                                 //settiamo il testo che comparirà all'interno del bottone
        setButtonFont();                               //settiamo il font da utilizzare all'interno del bottone
        setPrefHeight(49);                             //settiamo l'altezza del bottone
        setPrefWidth(190);                             //settiamo la larghezza del bottone
        setStyle(BUTTON_FREE_STYLE);                   //settiamo l'immagine del bottone non pressato
        initializeButtonListeners();                   //richiamiamo questo metodo per far funzionare il bottone quando lo clicchiamo ccon il mouse
    }

    private void setButtonFont(){                      //metodo setButtonFont
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),20));    //settiamo il font
        } catch (FileNotFoundException e){             //se ci sono errori li rivela
            setFont(Font.font("Verdana",20));    //settiamo un altro font nel caso di errori
        }
    }

    private void setButtonPressedStyle(){              //metodo setButtonPressedStyle
        setStyle(BUTTON_PRESSED_STYLE);                //settiamo il bottone pressato
        setPrefHeight(45);                             //settiamo l'altezza del bottone
        setLayoutY(getLayoutY() + 4);                  //settiamo la coordinata y
    }

    private void setButtonReleasedStyle(){             //metodo setButtonReleasedStyle
        setStyle(BUTTON_FREE_STYLE);                   //settiamo il bottone non pressato
        setPrefHeight(49);                             //settiamo l'altezza del bottone
        setLayoutY(getLayoutY() - 4);                  //settiamo la coordinata y
    }

    private void initializeButtonListeners(){          //metodo initializeButtonListeners
        setOnMousePressed(new EventHandler<MouseEvent>() {          //se clicchiamo con il mouse ci cambierà lo stile grafico del bottone
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();                        //chiamiamo il metodo setButtonPressedStyle per cambiare stile del bottone
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {         //se non clicchiamo con il mouse ci cambierà lo stile grafico del bottone
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();                       //chiamiamo il metodo setButtonReleasedStyle per cambiare stile del bottone
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {         //se entriamo con il cursone all'interno del bottone ci metterà un effetto di ombra nel bottone
            @Override
            public void handle(MouseEvent Event) {
                setEffect(new DropShadow());

            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {         //se usciamo dal bottone con il mouse ci leva l'effetto di ombra dal bottone
            @Override
            public void handle(MouseEvent Event) {
                setEffect(null);

            }
        });

    }

}
