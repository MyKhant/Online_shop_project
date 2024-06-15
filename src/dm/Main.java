package dm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage st) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../resource/login.fxml"));

		Scene sc = new Scene(root);
		st.setScene(sc);
		st.setTitle("OnlineShop Management");
		st.show();
	}

}
