package academius;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL; // This import is now essential

/**
 * Main application class to launch the JavaFX dashboard.
 */
public class DashboardApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // 1. Get the resource URL using a classpath-relative path.
        // The leading "/" means the path is relative to the root of your classpath (i.e., the 'resources' folder).
        URL fxmlLocation = getClass().getResource("/MainView.fxml");

        // 2. Add a proper null check to give a clear error message.
        if (fxmlLocation == null) {
            System.err.println("Cannot find FXML file. Make sure 'MainView.fxml' is in 'src/main/resources'.");
            System.exit(1);
        }

        // 3. Load the FXML using the correct location.
        // Using an instance of FXMLLoader is generally preferred over the static method.
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();

        Scene scene = new Scene(root, 1000, 700);

        // Set the title of the window and display it.
        primaryStage.setTitle("Sistema Acadêmico Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}