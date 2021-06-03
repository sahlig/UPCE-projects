/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv7;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseButton;
import static javafx.scene.input.MouseButton.PRIMARY;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

/**
 *
 * @author Radek
 */
public class Cv7 extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox v = new VBox();
        HBox flow = new HBox();
        ObservableList<String> sh = FXCollections.observableArrayList("CIRCLE", "POLYGON", "RECTANGLE");
        Slider size = new Slider(10, 100, 30);
        Pane pane = new Pane();
        Pane space = new Pane();
        ComboBox shape = new ComboBox(sh);
        shape.getSelectionModel().select(0);
        ColorPicker clp = new ColorPicker();
        Label labX = new Label("X = ");
        Label labY = new Label("Y = ");
        Label x = new Label("0");
        Label y = new Label("0");

        flow.getChildren().addAll(clp, size, shape, labX, x, labY, y);
        flow.setSpacing(5);
        v.getChildren().addAll(space, flow);

        pane.getChildren().addAll(v);

        Scene scene = new Scene(pane, 800, 600);

        pane.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x.setText(Double.toString(event.getX()));
                y.setText(Double.toString(event.getY()));
            }

        });

        pane.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch (event.getButton()) {
                    case PRIMARY:
                        switch (shape.getSelectionModel().getSelectedIndex()) {
                            case 0:
                                Circle circ = new Circle(event.getX(), event.getY(), size.getValue(), clp.getValue());
                                circ.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (event.getButton() == MouseButton.MIDDLE) {
                                            circ.centerXProperty().set(event.getX());
                                            circ.centerYProperty().set(event.getY());
                                        }
                                    }

                                });
                                circ.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(event.getButton() == MouseButton.SECONDARY){
                                            pane.getChildren().remove(circ);
                                        }
                                    }

                                });
                                pane.getChildren().add(circ);
                                break;
                            case 1:
                                Polygon pol = new Polygon();
                                pol.setFill(clp.getValue());
                                double angleStep = Math.toRadians(60);
                                double angle = 0;
                                for (int i = 0; i < 6; i++, angle += angleStep) {
                                    pol.getPoints().addAll(Math.sin(angle) * (size.getValue() / 2) + event.getX(), Math.cos(angle) * (size.getValue() / 2) + event.getY());
                                }
                                pol.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (event.getButton() == MouseButton.MIDDLE) {
                                            pol.resizeRelocate(event.getX(), event.getY(), 0, 0);
                                        }
                                    }
                                });
                                pol.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(event.getButton() == MouseButton.SECONDARY){
                                            pane.getChildren().remove(pol);
                                        }
                                    }

                                });
                                pane.getChildren().add(pol);
                                break;
                            case 2:
                                Rectangle rect = new Rectangle((event.getX() - (size.getValue() / 2)), (event.getY() - (size.getValue() / 2)), size.getValue(), size.getValue());
                                rect.setFill(clp.getValue());
                                rect.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if (event.getButton() == MouseButton.MIDDLE) {
                                            rect.setX(event.getX() - (rect.getWidth() / 2));
                                            rect.setY(event.getY() - (rect.getHeight() / 2));
                                        }
                                    }
                                });
                                rect.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent event) {
                                        if(event.getButton() == MouseButton.SECONDARY){
                                            pane.getChildren().remove(rect);
                                        }
                                    }

                                });
                                pane.getChildren().add(rect);
                                break;
                            default:
                                break;
                        }

                        break;
                }
            }

        });

        /*ChangeListener<Number> changeListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double xx = pane.getWidth();
                double xy = pane.getHeight();
                
                Ellipse ell = new Ellipse(xx, xy, xx, xy);
                ell.setFill(null);
                ell.setStroke(Color.MAGENTA);
                pane.getChildren().addAll(ell);
            }
        };
        
        pane.widthProperty().addListener(changeListener);
        pane.heightProperty().addListener(changeListener);*/
 /*Rectangle rect = new Rectangle(0, 0, 70, 100);
        rect.setFill(Color.YELLOW);
        rect.setStroke(Color.BLUE);
        rect.setStrokeWidth(3);
        pane.getChildren().addAll(rect);
        
        Line line = new Line(90, 40, 230, 40);
        line.setStroke(Color.BLACK);
        pane.getChildren().addAll(line);
        
        Circle circle = new Circle(70, 100, 50);
        circle.setFill(Color.color(1.0, 0.0, 0.0, 0.75));
        pane.getChildren().addAll(circle);
        
        CubicCurve curve = new CubicCurve(100, 200, 150, 250, 200, 120, 350, 180);
        curve.setStroke(Color.AQUA);
        curve.setStrokeWidth(5);
        curve.setFill(null);
        pane.getChildren().addAll(curve);*/
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
