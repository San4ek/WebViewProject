import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField urlField;

    @FXML
    private WebView webView;

    private WebEngine engine;

    private double zoom=1;

    private WebHistory history;

    @FXML
    void history() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        for (WebHistory.Entry entry: entries) {
            System.out.println(entry.getUrl()+" "+entry.getLastVisitedDate());
        }
    }

    @FXML
    void load() {
        engine.load("https://" +urlField.getText());
    }


    @FXML
    void JS() {
        engine.executeScript("window.location = \"https://www.youtube.com\";");
    }


    @FXML
    void next() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(1);
        urlField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    @FXML
    void previous() {
        history = engine.getHistory();
        ObservableList<WebHistory.Entry> entries = history.getEntries();
        history.go(-1);
        urlField.setText(entries.get(history.getCurrentIndex()).getUrl());
    }

    @FXML
    void zoomIn() {
        zoom+=0.1;
        webView.setZoom(zoom);
    }

    @FXML
    void zoomOut() {
        zoom-=0.1;
        webView.setZoom(zoom);
    }

    @FXML
    void refresh() {
        engine.reload();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        engine = webView.getEngine();
        String homePage = "www.google.com";
        urlField.setText(homePage);
        load();
    }
}
