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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
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

public class GuiManager
{
    // ============================================================================================================================================ \\
    
    private static final Logger logger = Logger.getLogger(GuiManager.class);
    
    private static GuiManager instance = new GuiManager();
    
    private static final int MAIN_WINDOW_WIDTH  = 1100;
    private static final int MAIN_WINDOW_HEIGHT = 600;
    
    private static final String WINDOW_TITLE = "ExifExplorer";
    
    private Stage window;
    private Scene titleScene;
    private Scene importScene;
    private Scene settingsScene;
    private Scene aboutScene;
    private Scene helpScene;
    private Scene mainScene;
    
    // ============================================================================================================================================ \\
    
    
    public GuiManager()
    {
        // this kills the singleton
        Runtime.getRuntime().addShutdownHook(new Thread(() -> instance = null));
    }
    
    // ============================================================================================================================================ \\
    
    
    public void setupWindow()
    {
        // Package window
        logger.debug("Creating window..");
        
        window.getIcons().add(new Image("img/logo.png"));
        logger.debug("Set window icon");
        
        window.setTitle(WINDOW_TITLE);
        logger.debug(String.format("Set window title to %s", WINDOW_TITLE));
        
        window.setMinWidth(MAIN_WINDOW_WIDTH);
        window.setWidth(MAIN_WINDOW_WIDTH);
        window.setMinHeight(MAIN_WINDOW_HEIGHT);
        window.setWidth(MAIN_WINDOW_HEIGHT);
        logger.debug(String.format("Set window dimensions to %d x %d", MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT));
        
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        window.setX((screenBounds.getWidth() - window.getWidth()) / 2);
        window.setY((screenBounds.getHeight() - window.getHeight()) / 2);
        logger.debug(String.format("Centered window at coordinates X: %f Y: %f", window.getX(), window.getY()));
        
        window.show();
        logger.debug("Showing window");
    }
    
    
    public void showTitleScreen()
    {
        logger.debug("Switching to title screen...");
        
        URL res = Main.class.getClassLoader().getResource("fxml/TitleScreen.fxml");
        
        if (res == null)
        {
            logger.error("TitleScreen.fxml could not be found");
            return;
        }
        
        try
        {
            FXMLLoader loader = new FXMLLoader(res);
            Parent root = loader.load();
            
            if (window.getScene() == null)
            {
                this.titleScene = new Scene(root, MAIN_WINDOW_WIDTH + 5.0, MAIN_WINDOW_HEIGHT + 5.0);
                Platform.runLater(() -> window.setScene(titleScene));
                logger.debug("Creating title scene for the first time");
            }
            else
            {
                Platform.runLater(() -> window.getScene().setRoot(root));
                logger.debug("Showing title scene again");
            }
            logger.debug("Showing title screen");
        }
        catch (IOException e)
        {
            logger.error("An error occurred while switching to the title screen");
            e.printStackTrace();
        }
    }
    
    
    public void showImportScreen()
    {
        logger.debug("Switching to import screen...");
        
        URL res = Main.class.getClassLoader().getResource("fxml/ImportScreen.fxml");
        
        if (res == null)
        {
            logger.error("ImportScreen.fxml could not be found");
            return;
        }
        
        try
        {
            FXMLLoader loader = new FXMLLoader(res);
            Parent root = loader.load();
            
            if (window.getScene() == null)
            {
                this.importScene = new Scene(root, MAIN_WINDOW_WIDTH + 5.0, MAIN_WINDOW_HEIGHT + 5.0);
                Platform.runLater(() -> window.setScene(importScene));
                logger.debug("Creating import scene for the first time");
            }
            else
            {
                Platform.runLater(() -> window.getScene().setRoot(root));
                logger.debug("Showing import scene again");
            }
            logger.debug("Showing import screen");
        }
        catch (IOException e)
        {
            logger.error("An error occurred while switching to the import screen");
            e.printStackTrace();
        }
    }
    
    
    public void showMainScreen()
    {
        logger.debug("Switching to main screen...");
        
        URL res = Main.class.getClassLoader().getResource("fxml/MainScreen.fxml");
        
        if (res == null)
        {
            logger.error("MainScreen.fxml could not be found");
            return;
        }
        
        try
        {
            FXMLLoader loader = new FXMLLoader(res);
            Parent root = loader.load();
            
            if (window.getScene() == null)
            {
                this.mainScene = new Scene(root, MAIN_WINDOW_WIDTH + 5.0, MAIN_WINDOW_HEIGHT + 5.0);
                Platform.runLater(() -> window.setScene(mainScene));
                logger.debug("Creating main scene for the first time");
            }
            else
            {
                Platform.runLater(() -> window.getScene().setRoot(root));
                logger.debug("Showing main screen again");
            }
            logger.debug("Showing main screen");
//            Main.getInstance().doImport();
        }
        catch (IOException e)
        {
            logger.error("An error occurred while switching to the main screen");
            e.printStackTrace();
        }
    }
    
    
    public void showSettingsScreen()
    {
        logger.debug("Switching to settings screen...");
        
        URL res = Main.class.getClassLoader().getResource("fxml/SettingsScreen.fxml");
        
        if (res == null)
        {
            logger.error("SettingsScreen.fxml could not be found");
            return;
        }
        
        try
        {
            FXMLLoader loader = new FXMLLoader(res);
            Parent root = loader.load();
            
            if (window.getScene() == null)
            {
                this.settingsScene = new Scene(root, MAIN_WINDOW_WIDTH + 5.0, MAIN_WINDOW_HEIGHT + 5.0);
                Platform.runLater(() -> window.setScene(settingsScene));
                logger.debug("Creating settings scene for the first time");
            }
            else
            {
                Platform.runLater(() -> window.getScene().setRoot(root));
                logger.debug("Showing settings scene again");
            }
            logger.debug("Showing settings screen");
        }
        catch (IOException e)
        {
            logger.error("An error occurred while switching to the settings screen");
            e.printStackTrace();
        }
    }
    
    
    public void showAboutScreen()
    {
        logger.debug("Switching to about screen...");
        
        URL res = Main.class.getClassLoader().getResource("fxml/AboutScreen.fxml");
        
        if (res == null)
        {
            logger.error("AboutScreen.fxml could not be found");
            return;
        }
        
        try
        {
            FXMLLoader loader = new FXMLLoader(res);
            Parent root = loader.load();
            
            if (window.getScene() == null)
            {
                this.aboutScene = new Scene(root, MAIN_WINDOW_WIDTH + 5.0, MAIN_WINDOW_HEIGHT + 5.0);
                Platform.runLater(() -> window.setScene(aboutScene));
                logger.debug("Creating about scene for the first time");
            }
            else
            {
                Platform.runLater(() -> window.getScene().setRoot(root));
                logger.debug("Showing about scene again");
            }
            logger.debug("Showing about screen");
        }
        catch (IOException e)
        {
            logger.error("An error occurred while switching to the about screen");
            e.printStackTrace();
        }
    }
    
    
    public void showHelpScreen()
    {
        logger.debug("Switching to help screen...");
        
        URL res = Main.class.getClassLoader().getResource("fxml/HelpScreen.fxml");
        
        if (res == null)
        {
            logger.error("HelpScreen.fxml could not be found");
            return;
        }
        
        try
        {
            FXMLLoader loader = new FXMLLoader(res);
            Parent root = loader.load();
            
            if (window.getScene() == null)
            {
                this.helpScene = new Scene(root, MAIN_WINDOW_WIDTH + 5.0, MAIN_WINDOW_HEIGHT + 5.0);
                Platform.runLater(() -> window.setScene(helpScene));
                logger.debug("Creating help scene for the first time");
            }
            else
            {
                Platform.runLater(() -> window.getScene().setRoot(root));
                logger.debug("Showing help scene again");
            }
            logger.debug("Showing help screen");
        }
        catch (IOException e)
        {
            logger.error("An error occurred while switching to the help screen");
            e.printStackTrace();
        }
    }
    
    
    public void showQuitDialog()
    {
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
            }
        });
    }
    
    
    public void populateTable(TableView<PhotoRow> table, List<Photo> photos)
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
    
    
    public void setWindow(Stage window)
    {
        this.window = window;
    }
    
    // ============================================================================================================================================ \\
    
    
    public static GuiManager getInstance()
    {
        return instance;
    }
    
    
    // ============================================================================================================================================ \\
}
