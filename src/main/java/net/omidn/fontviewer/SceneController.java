package net.omidn.fontviewer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.Collectors;

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
    private ComboBox<String> fontSizeComboBox;

    @FXML
    private ScrollPane previewScrollPane;

    @FXML
    private Text previewText;

    @FXML
    private void initialize() {
        previewText.wrappingWidthProperty().bind(previewScrollPane.widthProperty().subtract(5.0));

        fontSizeSlider.setMin(4);
        fontSizeSlider.setMax(96);
        fontSizeSlider.setBlockIncrement(1);

        ObservableList<String> comboBoxValues = FXCollections.observableArrayList(
                List.of("8", "12", "14", "20", "24", "32", "40", "64", "96")
                        .stream().map(s -> s + "px").collect(Collectors.toList()));
        fontSizeComboBox.setItems(comboBoxValues);
    }


}
