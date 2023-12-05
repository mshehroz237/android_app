package rupizza.ru_pizza;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    private CurrentOrderController currentOrderController;
    private Order currentOrder = new Order();
    public void setCurrentOrderController(CurrentOrderController currentOrderController) {
        this.currentOrderController = currentOrderController;
    }
    @FXML
    private Label welcomeText;

    @FXML
    protected void openSpecialtyPizza() {

        try {
            Stage stage = new Stage();
            stage.setTitle("Specialty Pizza");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SpecialityPizza.fxml"));
            Parent root = loader.load();

            // Set the current order in the opened form
            SpecialityPizzaController specialityPizzaController = loader.getController();
            specialityPizzaController.setCurrentOrderController(currentOrderController);
            specialityPizzaController.setCurrentOrder(currentOrder);

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void openBuildYourOwn() {
        openForm("BuildYourOwn.fxml", "Build Your Own Pizza");
    }

    @FXML
    protected void openCurrentOrder() {
        openForm("CurrentOrder.fxml", "Current Order");
    }

    @FXML
    protected void openStoreOrder() {
        openForm("StoreOrder.fxml", "Store Orders");
    }

    private void openForm(String fxmlFileName, String title) {
        try {
            Stage stage = new Stage();
            stage.setTitle(title);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFileName));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
