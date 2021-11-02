import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * 
 * @author Cordell Bonnieux
 *
 */
public class GeometricScene extends Application {
	private final int HEIGHT = 800;
	private final int WIDTH = 1200;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		// add program title
		stage.setTitle("Cordell's Geometric Scene");
		
		//create pane and add scene components
		Pane root = new Pane();
		root.getChildren().addAll(new Background(300, 300), new Ground(), new Kitty(Color.rgb(139, 197, 82), Color.rgb(238, 111, 158), 200, 800));
		
		// add root pane to a scene
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		// set the stage with the scene
		stage.setScene(scene);
		
		// display the scene on the stage
		stage.show();
		
	}
	
	private class Background extends Group{
		private Rectangle backdrop;
		private Ellipse moon;
		private Polygon pyramidLeft;
		private Polygon pyramidRight;
		
		public Background(double px, double py) {
			backdrop = new Rectangle((double)WIDTH, (double)HEIGHT);
			backdrop.setFill(Color.rgb(0, 20, 64));
			backdrop.setY(0);
			backdrop.setX(0);
			
			moon = new Ellipse((double)WIDTH-250, 250.00, 125.00, 125.00);
			moon.setFill(Color.ORANGE);
			
			pyramidLeft = new Polygon();
			pyramidLeft.setStroke(Color.rgb(19, 82, 171));
			pyramidLeft.setStrokeWidth(2);
			pyramidLeft.setFill(Color.rgb(150, 111, 214));
			pyramidLeft.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px-400, (double)HEIGHT,
			});
			
			pyramidRight = new Polygon();
			pyramidRight.setStroke(Color.rgb(19, 82, 171));
			pyramidRight.setStrokeWidth(2);
			pyramidRight.setFill(Color.rgb(183, 165, 217));
			pyramidRight.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px+400, (double)HEIGHT,
			});
			
			getChildren().addAll(backdrop, moon, pyramidLeft, pyramidRight);
		}
	}
	
	private class Ground extends Group {
		private Polygon groundLeft;
		private Polygon groundRight;
		private final double heightLeft = 600.00;
		private final double heightRight = 500.00;
		
		public Ground() {
			groundLeft = new Polygon();
			groundLeft.setFill(Color.rgb(255, 243, 222));
			groundLeft.getPoints().addAll(new Double[] {
					0.00, heightLeft,
					0.00, (double)HEIGHT,
					400.00, (double)HEIGHT,
			});

			groundRight = new Polygon();
			groundRight.setFill(Color.rgb(255, 243, 222));
			groundRight.getPoints().addAll(new Double[] {
					400.00, (double)HEIGHT,
					(double)WIDTH, heightRight,
					(double)WIDTH, (double)HEIGHT,
			});
			
			getChildren().addAll(groundLeft, groundRight);
		}
		
		public double getHeight() {
			return (this.heightLeft > this.heightRight) ? this.heightRight : this.heightLeft;
		}
	}
	
	private class Kitty extends Group {
		private Ellipse paw1;
		private Ellipse paw3;
		private Ellipse tail;
		private Ellipse body;
		private Ellipse head;
		private Ellipse paw2;
		private Ellipse paw4;
		private Ellipse eyeLeft;
		private Ellipse eyeRight;
		private Polygon earLeft;
		private Polygon earRight;
		private Line innerEyeRight;
		private Line innerEyeLeft;

		
		public Kitty(Color mainColor, Color eyeColor, double centerX, double centerY) {
			body = new Ellipse(centerX,centerY,90,40);
			paw1 = new Ellipse(centerX-70,centerY+30,15,10);
			paw3 = new Ellipse(centerX+25,centerY+35,15,10);
			tail = new Ellipse(centerX+80,centerY-40,12,52);
			head = new Ellipse(centerX-60,centerY-20,40,30);
			paw2 = new Ellipse(centerX-30,centerY+35,15,10);
			paw4 = new Ellipse(centerX+50,centerY+35,15,10);
			earLeft = new Polygon();
			earRight = new Polygon();
			eyeLeft = new Ellipse(centerX-80,centerY-20,15,10);
			eyeRight = new Ellipse(centerX-45,centerY-20,15,10);
			innerEyeLeft = new Line(centerX-80,centerY-28,centerX-80,centerY-12);
			innerEyeRight = new Line(centerX-45,centerY-28,centerX-45,centerY-12);
			
			innerEyeRight.setStrokeWidth(4);
			innerEyeLeft.setStrokeWidth(4);
			
			earLeft.getPoints().addAll(new Double[] {
					centerX-105, centerY-58,
					centerX-75, centerY-48,
					centerX-95, centerY-28
			});
			
			earRight.getPoints().addAll(new Double[] {
					centerX-15, centerY-58,
					centerX-45, centerY-48,
					centerX-20, centerY-28
			});
			
			paw1.setFill(mainColor);
			paw3.setFill(mainColor);
			tail.setFill(mainColor);
			body.setFill(mainColor);
			head.setFill(mainColor);
			paw2.setFill(mainColor);
			paw4.setFill(mainColor);
			earLeft.setFill(mainColor);
			earRight.setFill(mainColor);
			eyeLeft.setFill(eyeColor);
			eyeRight.setFill(eyeColor);
			
			getChildren().addAll(paw1,paw3,tail,body,head,paw2,paw4,earLeft,earRight,eyeLeft,eyeRight,innerEyeLeft,innerEyeRight);			
		}
	}
}
