package ConversorOne;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import org.json.JSONException;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import static java.awt.Desktop.getDesktop;
import static java.lang.Double.parseDouble;

@SuppressWarnings("CanBeFinal")
public class MainController extends ConversorAPI implements ConversorTemp, ConversorMedida {
    @FXML
    protected Label moedaResposta;
    ObservableList<String> escolherMoedaList = FXCollections.observableArrayList("dólar", "euro", "real", "libra esterlina", "peso argentino", "peso chileno");
    ObservableList<String> escolherUnidadeList = FXCollections.observableArrayList("quilômetro", "hectômetro", "decâmetro", "metro", "decímetro", "centímetro", "milímetro");
    ObservableList<String> escolherTemperaturaList = FXCollections.observableArrayList("celsius", "kelvin", "fahrenheit");
    @FXML
    private Pane temperatura;
    @FXML
    private Pane distancia;
    @FXML
    private Pane moeda;
    @FXML
    private ComboBox<String> escolherMoedaIn;
    @FXML
    private ComboBox<String> escolherMoedaOut;
    @FXML
    private ComboBox<String> escolherUnidadeIn;
    @FXML
    private ComboBox<String> escolherUnidadeOut;
    @FXML
    private ComboBox<String> escolherMedidaIn;
    @FXML
    private ComboBox<String> escolherMedidaOut;
    @FXML
    private ImageView fechar;
    @FXML
    private TextArea moedaArea;
    @FXML
    private TextArea kmArea;
    @FXML
    private TextArea medidaArea;
    @FXML
    private Label kmResposta;
    @FXML
    private Label temperaturaResposta;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnMoeda;
    @FXML
    private Button btnDist;
    @FXML
    private Button btnTemp;
    @FXML
    private void camadaHome() {
        temperatura.setVisible(false);
        distancia.setVisible(false);
        moeda.setVisible(false);
        btnHome.setStyle("-fx-background-color: #020202");
        btnMoeda.setStyle("-fx-background-color: #000");
        btnDist.setStyle("-fx-background-color: #000");
        btnTemp.setStyle("-fx-background-color: #000");
    }

    @FXML
    private void camadaTemperatura() {
        temperatura.setVisible(true);
        distancia.setVisible(false);
        moeda.setVisible(false);
        btnHome.setStyle("-fx-background-color: #000");
        btnMoeda.setStyle("-fx-background-color: #000");
        btnDist.setStyle("-fx-background-color: #000");
        btnTemp.setStyle("-fx-background-color: #440d0b");
    }

    @FXML
    private void camadaDistancia() {
        distancia.setVisible(true);
        temperatura.setVisible(false);
        moeda.setVisible(false);
        btnHome.setStyle("-fx-background-color: #000");
        btnMoeda.setStyle("-fx-background-color: #000");
        btnDist.setStyle("-fx-background-color: #0a0f10");
        btnTemp.setStyle("-fx-background-color: #000");
    }

    @FXML
    private void camadaMoeda() {
        moeda.setVisible(true);
        temperatura.setVisible(false);
        distancia.setVisible(false);
        btnHome.setStyle("-fx-background-color: #000");
        btnMoeda.setStyle("-fx-background-color: #3c2713");
        btnDist.setStyle("-fx-background-color: #000");
        btnTemp.setStyle("-fx-background-color: #000");
    }

    @FXML
    private void initialize() {
        escolherMoedaIn.setItems(escolherMoedaList);
        escolherMoedaOut.setItems(escolherMoedaList);
        escolherUnidadeIn.setItems(escolherUnidadeList);
        escolherUnidadeOut.setItems(escolherUnidadeList);
        escolherMedidaIn.setItems(escolherTemperaturaList);
        escolherMedidaOut.setItems(escolherTemperaturaList);
        fechar.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
    }

    @FXML
    private void openWebpage(String url) {
        try {
            getDesktop().browse(new URI(url));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void gitHub() {
        openWebpage("https://github.com/Medeiros000");
    }

    @FXML
    private void linkedin() {
        openWebpage("https://www.linkedin.com/in/j%C3%BAnior-medeiros/");
    }

    @FXML
    private void converterMoeda() {
        try {
            double valor = parseDouble(moedaArea.getText().replaceAll(",", "."));
            String In = (escolherMoedaIn.getValue()).toString();
            String Out = (escolherMoedaOut.getValue()).toString();
            moedaResposta.setText(convert(valor, In, Out));
        } catch (NumberFormatException e) {
            moedaResposta.setText("Digite um valor válido");
        } catch (NullPointerException e) {
            moedaResposta.setText("Utilize os campos para escolher as moedas");
        } catch (JSONException e) {
            moedaResposta.setText("Falha na leitura do JSON");
        } catch (IOException e) {
            moedaResposta.setText("Falha na requisição do JSONObject");
        }
    }

    @FXML
    private void converterUnidade() {
        try {
            double valor = parseDouble(kmArea.getText().replaceAll(",", "."));
            String In = (escolherUnidadeIn.getValue()).toString();
            String Out = (escolherUnidadeOut.getValue()).toString();
            double Resposta = converteM(valor, In);
            String Final = converterResposta(Resposta, Out);
            kmResposta.setText(Final);
        } catch (NumberFormatException e) {
            kmResposta.setText("Digite um valor válido");
        } catch (NullPointerException e) {
            kmResposta.setText("Utilize os campos para escolher as unidades ");
        }
    }

    @FXML
    private void converterTemperatura() {
        try {
            double valor = parseDouble(medidaArea.getText().replaceAll(",", "."));
            String In = (escolherMedidaIn.getValue()).toString();
            String Out = (escolherMedidaOut.getValue()).toString();
            String Final = converteT(valor, In, Out);
            temperaturaResposta.setText(Final);
        } catch (NumberFormatException e) {
            temperaturaResposta.setText("Digite um valor válido");
        } catch (NullPointerException e) {
            temperaturaResposta.setText("Utilize os campos para escolher as medidas ");
        }
    }
    @FXML
    private void copiar(){
        String valor = moedaResposta.getText();
        System.out.println(valor);
        StringSelection valorCopiado = new StringSelection(valor);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(valorCopiado, null);
    }
}