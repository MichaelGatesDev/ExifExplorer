/*
 * Copyright (C) Michael Gates (MichaelGatesDev@gmail.com) 2018
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.michaelgatesdev.ExifExplorer.gui;

import com.michaelgatesdev.ExifExplorer.Main;
import com.michaelgatesdev.ExifExplorer.gui.components.PhotoRow;
import com.michaelgatesdev.ExifExplorer.photo.Photo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class StageManager
{
    // ============================================================================================================================================ \\
    
    private static final Logger logger = Logger.getLogger(StageManager.class);
    
    private static final String WINDOW_TITLE = "ExifExplorer";
    
    private static final int MAIN_WINDOW_WIDTH  = 1000;
    private static final int MAIN_WINDOW_HEIGHT = 700;
    
    
    private Stage primaryStage;
    
    // ============================================================================================================================================ \\
    
    
    public StageManager(Stage primaryStage)
    {
        logger.debug("Initializing stage manager");
        this.primaryStage = primaryStage;
    }
    
    // ============================================================================================================================================ \\
    
    
    private void setupWindow()
    {
        // Package window
        logger.debug("Creating window..");
        
        primaryStage.getIcons().clear();
        primaryStage.getIcons().add(new Image("img/logo.png"));
        logger.debug("Set window icon");
        
        primaryStage.setTitle(WINDOW_TITLE);
        logger.debug(String.format("Set window title to %s", WINDOW_TITLE));
        
        primaryStage.setMinWidth(MAIN_WINDOW_WIDTH);
        primaryStage.setWidth(MAIN_WINDOW_WIDTH);
        primaryStage.setMinHeight(MAIN_WINDOW_HEIGHT);
        primaryStage.setWidth(MAIN_WINDOW_HEIGHT);
        logger.debug(String.format("Set window dimensions to %d x %d", MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((screenBounds.getHeight() - primaryStage.getHeight()) / 2);
        logger.debug(String.format("Centered window at coordinates X: %f Y: %f", primaryStage.getX(), primaryStage.getY()));
        
        primaryStage.setOnCloseRequest(event ->
        {
            if (!this.showQuitDialog())
            {
                event.consume();
            }
        });
        logger.debug("Added 'close request' event handler to window");
        
        primaryStage.show();
        logger.debug("Showing window");
    }
    
    
    public void switchToScene(String pathToFxml, Initializable controller)
    {
        // Window hasn't been set up
        if (!primaryStage.isShowing() || !primaryStage.getTitle().equals(WINDOW_TITLE))
        {
            this.setupWindow();
        }
        
        try
        {
            logger.debug(String.format("Switching scenes to %s", pathToFxml));
            URL url = Main.getInstance().getClass().getClassLoader().getResource(pathToFxml);
            FXMLLoader loader = new FXMLLoader(url);
            if (controller != null)
            {
                loader.setController(controller);
                logger.debug("Set scene controller to " + controller.getClass().getName());
            }
            Parent newView = loader.load();
            Scene newScene = new Scene(newView, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
            primaryStage.setScene(newScene);
            logger.debug("Finished switching scenes!");
        }
        catch (IOException e)
        {
            logger.error("An error occurred while switching scenes!");
            e.printStackTrace();
        }
    }
    
    
    public boolean showQuitDialog()
    {
        AtomicBoolean b = new AtomicBoolean(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Application");
        alert.setHeaderText("Are you sure you want to exit?");
        alert.setContentText("Any unsaved work/changes will be discarded.");
        
        Optional<ButtonType> result = alert.showAndWait();
        result.ifPresent(buttonType ->
        {
            if (ButtonType.OK.equals(result.get()))
            {
                Main.getInstance().quit();
                b.set(true);
            }
            b.set(false);
        });
        return b.get();
    }
    
    
    public void repopulateTable(TableView<PhotoRow> table, List<Photo> photos)
    {
        ObservableList<PhotoRow> data = FXCollections.observableArrayList();
        for (Photo p : photos)
        {
            PhotoRow row = new PhotoRow(p);
            data.add(row);
            logger.debug("Added row to table: " + row.toString());
        }
        table.setItems(data);
    }
    
    // ============================================================================================================================================ \\
}
