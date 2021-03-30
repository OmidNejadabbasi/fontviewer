package net.omidn.fontviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SceneController {

    @FXML
    private ListView<?> fontsListView;

    @FXML
    private Button addItemsBtn;

    @FXML
    private TextArea previewTextField;

    @FXML
    private Slider fontSizeSlider;

    @FXML
    private ComboBox<?> fontSizeComboBox;

    @FXML
    private ScrollPane previewScrollPane;

    @FXML
    private Text previewText;

}
