package paint;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class FXMLDocumentController implements Initializable
{

    @FXML
    private TextField brushSize;
    @FXML
    private CheckBox eraser;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Canvas canvas;
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(brushSize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            
            if(eraser.isSelected())
                {
                    g.clearRect(x, y, size, size);
                }
            else
                {
                    g.setFill(colorPicker.getValue());
                    g.fillRect(x, y, size, size);
                }
        });
    }    

    @FXML
    private void onSave(ActionEvent event)
    {
        try
        {
            Image snapshot = canvas.snapshot(null, null);
            
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File("paint.png"));
        } catch (Exception e)
        {
            System.out.println("Failed to save image" + e);
        }
    }

    @FXML
    private void onExit(ActionEvent event)
    {
        Platform.exit();
    }
    
}
