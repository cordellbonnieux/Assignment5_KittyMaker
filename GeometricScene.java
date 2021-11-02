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
 * Gemometric Scene
 * A simple gemotric scene with scale-able randomly placed kitties.
 * @author Cordell Bonnieux
 * 
 */
public class GeometricScene extends Application {
	private final int HEIGHT = 800;
	private final int WIDTH = 1200;
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	/**
	 * Start
	 * JavaFX program logic
	 * @param stage
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		// add program title
		stage.setTitle("Cordell's Geometric Scene");
		
		//create pane and scene components
		Pane root = new Pane();
		
		// add some components to display
		Ground foreground = new Ground();
		
		// create random coordinates for kitty
		Kitty cat = new Kitty(Color.rgb(139, 197, 82), Color.rgb(238, 111, 158), randomX(), randomY(foreground), randomSize());
		Kitty moro = new Kitty(Color.BLACK, Color.YELLOW, randomX(), randomY(foreground), randomSize());
		
		// add components to pane
		root.getChildren().addAll(new Background(300, 300), foreground, cat, moro);
		
		// add root pane to a scene
		Scene scene = new Scene(root, WIDTH, HEIGHT);
		
		// set the stage with the scene
		stage.setScene(scene);
		
		// display the scene on the stage
		stage.show();
		
	}
	
	/**
	 * RandomX
	 * @return double - a random valid X coordinate
	 */
	private double randomX() {
		return 50 + Math.random() * ((WIDTH-75) - 50 + 1);
	}
	
	/**
	 * RandomY
	 * @param g - Ground object
	 * @return double - a random valid Y coordinate
	 */
	private double randomY(Ground g) {
		return g.getHeight() + Math.random() * ((HEIGHT-75) - g.getHeight() + 1);
	}
	
	/**
	 * Random Size
	 * @return double - a random valid size scale
	 */
	private int randomSize() {
		return (int)(0.5 + Math.random() * (3 - 0.5 + 1));
	}
	
	/**
	 * Background
	 * Used to create background group of shapes
	 */
	private class Background extends Group{
		private Rectangle backdrop;
		private Ellipse moon;
		private Polygon pyramidLeft;
		private Polygon pyramidRight;
		 
		/**
		 * Class Constructor
		 * @param px double - Y-intersect of the top triangle points
		 * @param py double - X-intercept of the top triangle points
		 */
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
	
	/**
	 * Ground
	 * Used to create a foreground
	 */
	private class Ground extends Group {
		private Polygon groundLeft;
		private Polygon groundRight;
		private final double heightLeft = 600.00;
		private final double heightRight = 500.00;
		
		/**
		 * Ground
		 * Class Constructor
		 */
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
					00.00, (double)HEIGHT,
					(double)WIDTH, heightRight,
					(double)WIDTH, (double)HEIGHT,
			});
			
			getChildren().addAll(groundLeft, groundRight);
		}
		
		public double getHeight() {
			return (this.heightLeft > this.heightRight) ? this.heightRight : this.heightLeft;
		}
	}
	
	/**
	 * Kitty
	 * Used to create foreground objects
	 * @author cordell
	 *
	 */
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

		/**
		 * Kitty
		 * Class Constructor
		 * @param mainColor Color - main color
		 * @param eyeColor Color - eye color
		 * @param centerX double - center X position for the kitty
		 * @param centerY double - center Y position for the kitty
		 * @param scale int - scale of the kitty 
		 */
		public Kitty(Color mainColor, Color eyeColor, double centerX, double centerY, int scale) {
			double radiusX = 90 * scale;
			double radiusY = 40 * scale;
			double[] range = new double[] {centerX-radiusX, centerX+radiusX, centerY-radiusY, centerY+radiusY};
			
			body = new Ellipse(centerX, centerY, radiusX, radiusY);
			paw1 = new Ellipse(range[0] + radiusX/4, range[3] - radiusY/4, scale*15, scale*10);
			paw3 = new Ellipse(range[1] - radiusX, range[3] - radiusY/10, scale*15, scale*10);
			tail = new Ellipse(range[1] - radiusX/8, range[2], scale*12, scale*52);
			head = new Ellipse(range[0] + radiusX/3, range[2] + radiusY/2, scale*40, scale*30);
			paw2 = new Ellipse(range[0] + radiusX/1.75, range[3] - radiusY/8, scale*15, scale*10);
			paw4 = new Ellipse(range[1] - radiusX/3, range[3] - radiusY/6, scale*15, scale*10);
			eyeLeft = new Ellipse(range[0] + radiusX/8, range[2] + radiusY/2, scale*15, scale*10);
			eyeRight = new Ellipse(range[0] + radiusX/2, range[2] + radiusY/2, scale*15, scale*10);
			innerEyeLeft = new Line(range[0] + radiusX/8, range[2] + radiusY/3.5, range[0] + radiusX/8, range[2] + radiusY/1.5);
			innerEyeRight = new Line(range[0] + radiusX/2, range[2] + radiusY/3.5, range[0] + radiusX/2, range[2] + radiusY/1.5);
			earLeft = new Polygon();
			earRight = new Polygon();
			
			innerEyeRight.setStrokeWidth(scale*4);
			innerEyeLeft.setStrokeWidth(scale*4);
			
			earLeft.getPoints().addAll(new Double[] {
					centerX-(105*scale), centerY-(58*scale),
					centerX-(75*scale), centerY-(48*scale),
					centerX-(95*scale), centerY-(28*scale)
			});
			
			earRight.getPoints().addAll(new Double[] {
					centerX-(15*scale), centerY-(58*scale),
					centerX-(45*scale), centerY-(48*scale),
					centerX-(20*scale), centerY-(28*scale)
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
