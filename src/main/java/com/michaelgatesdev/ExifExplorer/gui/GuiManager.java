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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GuiManager
{
    // ============================================================================================================================================ \\
    
    private static final Logger logger = Logger.getLogger(GuiManager.class);
    
    private static GuiManager instance = new GuiManager();
    
    private static final int MAIN_WINDOW_WIDTH  = 900;
    private static final int MAIN_WINDOW_HEIGHT = 600;
    
    private static final String WINDOW_TITLE = "ExifExplorer";
    
    private Main  main;
    private Stage window;
    private Scene titleScene;
    private Scene importScene;
    private Scene settingsScene;
    private Scene mainScene;
    private Node  sacrifice;
    
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
        Platform.runLater(() ->
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
                    window.setScene(titleScene);
                }
                else
                {
                    window.getScene().setRoot(root);
                }
                logger.debug("Showing title screen");
            }
            catch (IOException e)
            {
                logger.error("An error occurred while switching to the title screen");
                e.printStackTrace();
            }
        });
    }
    
    
    public void showImportScreen()
    {
        Platform.runLater(() ->
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
                    window.setScene(importScene);
                }
                else
                {
                    window.getScene().setRoot(root);
                }
                logger.debug("Showing import screen");
            }
            catch (IOException e)
            {
                logger.error("An error occurred while switching to the import screen");
                e.printStackTrace();
            }
        });
    }
    
    
    public void showMainScreen()
    {
        Platform.runLater(() ->
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
                    window.setScene(mainScene);
                }
                else
                {
                    window.getScene().setRoot(root);
                }
                logger.debug("Showing main screen");
            }
            catch (IOException e)
            {
                logger.error("An error occurred while switching to the main screen");
                e.printStackTrace();
            }
        });
    }
    
    // ============================================================================================================================================ \\
    
    
    public void unlockFilters(Node n)
    {
        Platform.runLater(() ->
        {
            Scene scene = n.getScene();
            GridPane pane = (GridPane) scene.lookup("#filtersPane");
            pane.setDisable(false);
        });
    }
    
    
    public void unlockViews(Node n)
    {
        Platform.runLater(() ->
        {
            Scene scene = n.getScene();
            TabPane pane = (TabPane) scene.lookup("#viewsTabPane");
            pane.setDisable(false);
        });
    }
    
    
    public void populateTable(Node ref, List<Photo> photos)
    {
        Platform.runLater(() ->
        {
            Scene scene = ref.getScene();
            TableView table = (TableView) scene.lookup("#table");
            
            ObservableList<PhotoRow> data = FXCollections.observableArrayList();
            for (Photo p : photos)
            {
                PhotoRow row = new PhotoRow(p);
                data.add(row);
            }
            table.setItems(data);
        });
    }
    
    
    public void updateWorkspaceInfo(Node sacrifice, File importDir, File exportDir)
    {
        if (importDir != null)
        {
            Platform.runLater(() -> {
                TextField box = (TextField) sacrifice.getScene().lookup("#importPathBox");
                box.setText(importDir.getPath());
            });
        }
        if (exportDir != null)
        {
            Platform.runLater(() -> {
                TextField box = (TextField) sacrifice.getScene().lookup("#exportPathBox");
                box.setText(exportDir.getPath());
            });
        }
    }
    
    // ============================================================================================================================================ \\
    
    
    public void setWindow(Stage window)
    {
        this.window = window;
    }
    
    
    public static GuiManager getInstance()
    {
        return instance;
    }
    
    
    // ============================================================================================================================================ \\
}
