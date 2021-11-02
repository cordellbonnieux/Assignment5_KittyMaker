import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
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
		root.getChildren().addAll(new Background(300, 300), new Ground(600, 400, 500), new Kitty(Color.PURPLE));
		
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
			this.backdrop = new Rectangle((double)WIDTH, (double)HEIGHT);
			this.backdrop.setFill(Color.DARKBLUE);
			this.backdrop.setY(0);
			this.backdrop.setX(0);
			
			this.moon = new Ellipse((double)WIDTH-250, 250.00, 125.00, 125.00);
			this.moon.setFill(Color.ORANGE);
			
			this.pyramidLeft = new Polygon();
			this.pyramidLeft.setFill(Color.DEEPPINK);
			this.pyramidLeft.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px-400, (double)HEIGHT,
			});
			
			this.pyramidRight = new Polygon();
			this.pyramidRight.setFill(Color.MEDIUMPURPLE);
			this.pyramidRight.getPoints().addAll(new Double[] {
					px, py,
					px+75, (double)HEIGHT,
					px+400, (double)HEIGHT,
			});
			
			this.getChildren().addAll(backdrop, moon, pyramidLeft, pyramidRight);
		}
	}
	
	private class Ground extends Group {
		private Polygon groundLeft;
		private Polygon groundRight;
		
		public Ground(double leftY, double intersection, double RightY) {
			this.groundLeft = new Polygon();
			this.groundLeft.setFill(Color.TAN);
			this.groundLeft.getPoints().addAll(new Double[] {
					0.00, leftY,
					0.00, (double)HEIGHT,
					intersection, (double)HEIGHT,
			});

			this.groundRight = new Polygon();
			this.groundRight.setFill(Color.TAN);
			this.groundRight.getPoints().addAll(new Double[] {
					intersection, (double)HEIGHT,
					(double)WIDTH, RightY,
					(double)WIDTH, (double)HEIGHT,
			});
			
			this.getChildren().addAll(groundLeft, groundRight);
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
		private Polygon earLeft;
		private Polygon earRight;
		private Ellipse eyeLeft;
		private Ellipse eyeRight;
		
		public Kitty(Color mainColor) {
			// centerX, centerY, radiusX, radiusY
			body = new Ellipse(470,570,90,40);
			paw1 = new Ellipse(400,600,15,10);
			paw3 = new Ellipse(495,605,15,10);
			tail = new Ellipse(550,530,12,52);
			head = new Ellipse(410,550,40,30);
			paw2 = new Ellipse(440,605,15,10);
			paw4 = new Ellipse(520,605,15,10);
			earLeft = new Polygon();
			earRight = new Polygon();
			eyeLeft = new Ellipse(390,550,15,10);
			eyeRight = new Ellipse(425,550,15,10);
			
			earLeft.getPoints().addAll(new Double[] {
					365.00, 512.00,
					395.00, 522.00,
					370.00, 542.00
			});
			
			earRight.getPoints().addAll(new Double[] {
					455.00, 512.00,
					425.00, 522.00,
					450.00, 542.00
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
			eyeLeft.setFill(Color.YELLOW);
			eyeRight.setFill(Color.YELLOW);
			
			getChildren().addAll(paw1,paw3,tail,body,head,paw2,paw4,earLeft,earRight,eyeLeft,eyeRight);			
		}
	}
}
