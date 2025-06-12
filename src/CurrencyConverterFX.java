import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class CurrencyConverterFX extends Application {
    private static final String API_KEY = "756fce3be38ec603b6edfa0b";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("🌐 Advanced Currency Converter");

        Label titleLabel = new Label("Currency Converter");
        titleLabel.setFont(new Font("Arial", 24));

        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        ComboBox<String> fromCurrency = new ComboBox<>();
        fromCurrency.getItems().addAll("USD", "INR", "EUR", "GBP", "JPY");
        fromCurrency.setValue("USD");

        ComboBox<String> toCurrency = new ComboBox<>();
        toCurrency.getItems().addAll("USD", "INR", "EUR", "GBP", "JPY");
        toCurrency.setValue("INR");

        Button convertBtn = new Button("Convert");
        convertBtn.setStyle("-fx-background-color: #007ACC; -fx-text-fill: white;");

        Text resultText = new Text();
        resultText.setFill(Color.DARKGREEN);
        resultText.setFont(Font.font("Arial", 16));

        Text errorText = new Text();
        errorText.setFill(Color.RED);

        convertBtn.setOnAction(e -> {
            errorText.setText("");
            resultText.setText("");

            try {
                double amount = Double.parseDouble(amountField.getText());
                String from = fromCurrency.getValue();
                String to = toCurrency.getValue();
                double rate = getExchangeRate(from, to);
                double converted = amount * rate;

                resultText.setText(String.format("%.2f %s = %.2f %s", amount, from, converted, to));
            } catch (NumberFormatException ex) {
                errorText.setText("Please enter a valid numeric amount.");
            } catch (Exception ex) {
                errorText.setText("Failed to fetch exchange rate.");
            }
        });

        VBox vbox = new VBox(10, titleLabel, amountField, fromCurrency, toCurrency, convertBtn, resultText, errorText);
        vbox.setPadding(new Insets(30));
        vbox.setStyle("-fx-background-color: #f4f4f4;");
        vbox.setPrefWidth(400);

        primaryStage.setScene(new Scene(vbox));
        primaryStage.show();
    }

    private double getExchangeRate(String from, String to) throws Exception {
        String urlStr = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + from + "/" + to;
        HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200)
            throw new RuntimeException("HTTP Error: " + conn.getResponseCode());

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = in.readLine()) != null)
            jsonBuilder.append(line);
        in.close();

        JSONObject json = new JSONObject(jsonBuilder.toString());
        return json.getDouble("conversion_rate");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
