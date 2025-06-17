package academius.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class DashboardController {

    @FXML
    private BorderPane mainPane;

    @FXML
    public void initialize() {
        // Load the initial view (Dashboard)
        showDashboardView();
    }

    @FXML
    private void showDashboardView() {
        // You would extract your original dashboard content into "DashboardContentView.fxml"
        loadView("/DashboardContentView.fxml");
    }

    @FXML
    private void showCursosView() {
        loadView("/CursosView.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            mainPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions, maybe show an error dialog
        }
    }
}