/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv7;

import com.sun.javafx.scene.control.skin.CustomColorDialog;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author Radek
 */
public class Sachovnice extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane bp = new BorderPane();

        Spinner<Integer> spinRow = new Spinner<>(1, 20, 8);
        Spinner<Integer> spinCol = new Spinner<>(1, 20, 8);
        HBox hbox = new HBox(new Label("Rows:"), spinRow, new Label("Collumns:"), spinCol);
        hbox.setSpacing(5);
        hbox.setBorder(new Border(new BorderStroke(Paint.valueOf("black"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        Pane pane = new Pane();
        pane.setPrefSize(800, 600);

        ChangeListener<Number> changeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                pane.getChildren().clear();

                for (int i = 0; i < spinCol.getValue(); i++) {
                    for (int j = 0; j < spinRow.getValue(); j++) {
                        Paint paint;
                        if (i % 2 == 0 && j % 2 == 0) {
                            paint = Paint.valueOf("black");
                        } else if(i % 2 != 0 && j % 2 != 0) {
                            paint = Paint.valueOf("black");
                        }else{
                            paint = Paint.valueOf("white");
                        }
                        Rectangle rec = new Rectangle();
                        rec.setFill(paint);
                        rec.setStroke(null);

                        rec.setX(i * (pane.getWidth() / spinCol.getValue()));
                        rec.setY(j * (pane.getHeight() / spinRow.getValue()));
                        rec.setWidth(pane.getWidth() / spinCol.getValue());
                        rec.setHeight(pane.getHeight() / spinRow.getValue());
                        pane.getChildren().add(rec);
                        
                        
                        rec.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>(){
                            @Override
                            public void handle(MouseEvent event) {
                                if(event.getButton() == MouseButton.PRIMARY){
                                    if(rec.getFill() ==  Paint.valueOf("black")){
                                        rec.setFill(Paint.valueOf("white"));
                                    }else{
                                        rec.setFill(Paint.valueOf("black"));
                                    }
                                }
                            }
                            
                        });
                    }
                }

                for (double i = (pane.getHeight() / spinRow.getValue()); i < pane.getHeight(); i = i + (pane.getHeight() / spinRow.getValue())) {
                    Line line = new Line(0, i, pane.getWidth(), i);
                    line.setVisible(true);
                    line.setStroke(Paint.valueOf("red"));
                    line.setStrokeWidth(1);
                    pane.getChildren().add(line);
                }
                for (double i = (pane.getWidth() / spinCol.getValue()); i < pane.getWidth(); i = i + (pane.getWidth() / spinCol.getValue())) {
                    Line line = new Line(i, 0, i, pane.getHeight());
                    line.setVisible(true);
                    line.setStroke(Paint.valueOf("red"));
                    line.setStrokeWidth(1);
                    pane.getChildren().add(line);
                }

            }
        };

        pane.widthProperty().addListener(changeListener);
        pane.heightProperty().addListener(changeListener);
        spinCol.valueProperty().addListener(changeListener);
        spinRow.valueProperty().addListener(changeListener);

        bp.setBottom(hbox);
        bp.setCenter(pane);

        primaryStage.setScene(new Scene(bp));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
