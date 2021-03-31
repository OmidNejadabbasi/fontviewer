package net.omidn.fontviewer;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class SceneController {

    @FXML
    private ListView<File> fontsListView;

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


    private ObjectProperty<Font> currentFont;
    private File lastSeenDir;

    @FXML
    private void initialize() {

        currentFont = previewText.fontProperty();

        previewText.wrappingWidthProperty().bind(previewScrollPane.widthProperty().subtract(5.0));

        fontSizeSlider.setMin(4);
        fontSizeSlider.setMax(96);
        fontSizeSlider.setBlockIncrement(1);

        ObservableList<String> comboBoxValues = FXCollections.observableArrayList(
                List.of("8", "12", "14", "20", "24", "32", "40", "64", "96")
                        .stream().map(s -> s + "px").collect(Collectors.toList()));
        fontSizeComboBox.setItems(comboBoxValues);

        fontSizeComboBox.setValue("8px");
        fontSizeSlider.setValue(8);
        // value change action handling
        fontSizeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            int newV = Integer.parseInt(newValue.split("px")[0]);
            fontSizeSlider.setValue(newV);
        });

        fontSizeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            String value = newValue.intValue() + "px";
            fontSizeComboBox.setValue(value);

            previewText.setStyle("-fx-font-size: " + fontSizeComboBox.getValue() + ";");
        });

        addItemsBtn.setOnAction(event -> {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a directory or a bunch of files : ");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Font files(*.ttf, *.otf)", "*.ttf", "*.otf"));
            fileChooser.setInitialDirectory(lastSeenDir != null ? lastSeenDir : new File(System.getProperty("user.home")));
            List<File> files = fileChooser.showOpenMultipleDialog(Main.mainStage);
            if (files == null)
                return;
            ObservableList<File> fontItems = fontsListView.getItems();
            lastSeenDir = files.get(0).getParentFile();
            for (File file : files) {
                if (!fontItems.contains(file)) {
                    fontItems.add(file);
                }
            }

        });

        fontsListView.setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
            @Override
            public ListCell<File> call(ListView<File> param) {
                ListCell<File> cell = new ListCell<>(){
                    @Override
                    protected void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item!=null) {
                            setText(item.getName());
                            setFont(Font.loadFont("file:///"+item.getAbsolutePath(), 20));
                        } else {
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        });

    }


}
