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
		root.getChildren().addAll(new Background(300, 300), new Ground());
		
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
		private Polygon foregroundLeft;
		private Polygon foregroundRight;
		
		public Ground() {
			this.foregroundLeft = new Polygon();
			this.foregroundLeft.setFill(Color.TAN);
			this.foregroundLeft.getPoints().addAll(new Double[] {
					0.00, 600.00,
					0.00, (double)HEIGHT,
					400.00, (double)HEIGHT,
			});

			this.foregroundRight = new Polygon();
			this.foregroundRight.setFill(Color.TAN);
			this.foregroundRight.getPoints().addAll(new Double[] {
					400.00, (double)HEIGHT,
					(double)WIDTH, 500.00,
					(double)WIDTH, (double)HEIGHT,
			});
			
			this.getChildren().addAll(foregroundLeft, foregroundRight);
		}
	}

}
