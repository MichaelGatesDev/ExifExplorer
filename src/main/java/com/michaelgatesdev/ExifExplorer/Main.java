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


package com.michaelgatesdev.ExifExplorer;

import com.michaelgatesdev.ExifExplorer.gui.GuiManager;
import com.michaelgatesdev.ExifExplorer.locale.UTF8Control;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Main extends Application
{
    // ============================================================================================================================================ \\
    
    private final static Logger logger = Logger.getLogger(Main.class.getSimpleName());
    
    private static final int MAIN_WINDOW_WIDTH  = 1000;
    private static final int MAIN_WINDOW_HEIGHT = 650;
    
    private static Main       instance;
    private        GuiManager guiManager;
    
    private ResourceBundle locale; // TODO
    
    private File rootDir;
    private File resourcesDir;
    private File imageResDir;
    private File textResDir;
    private File backupsDir;
    
    private File importDir;
    private File exportDir;
    
    // ============================================================================================================================================ \\
    
    
    /**
     * The main method when the program starts
     *
     * @param args Arguments provided if used by command line
     */
    public static void main(String[] args)
    {
        instance = new Main();
        instance.initialize();
        // this kills the singleton
        Runtime.getRuntime().addShutdownHook(new Thread(() -> instance = null));
    }
    
    // ============================================================================================================================================ \\
    
    
    /**
     * Initializes the program in a non-static way
     */
    private void initialize()
    {
        this.guiManager = new GuiManager(this);
        
        // Grab locale bundle
        locale = ResourceBundle.getBundle("Locale", /*Locale.JAPAN,*/ new UTF8Control());
        
        // Create/Initialize all the directories */
        initializeDirectories();
        
        // Init GUI
        launch();
    }
    
    
    private void initializeDirectories()
    {
        rootDir = new File(System.getProperty("user.dir") + "/");
//
//            resourcesDir = FileUtil.createDirectory(rootDir, "_resources", true);
//            FileUtils.copyDirectoryToDirectory(FileUtils.toFile(this.getResourceURL("text/")), resourcesDir);
//            textResDir = new File(resourcesDir, "/text/");
//            FileUtils.copyDirectoryToDirectory(FileUtils.toFile(this.getResourceURL("img/")), resourcesDir);
//            imageResDir = new File(resourcesDir, "/img/");
//
//            backupsDir = FileUtil.createDirectory(rootDir, "_backups", true);
    }
    
    
    @Override
    public void start(Stage stage) throws Exception
    {
        URL res = Main.class.getClassLoader().getResource("fxml/Base.fxml");
        
        if (res == null)
        {
            logger.error("Main resource path does not exist");
            return;
        }
        
        FXMLLoader loader = new FXMLLoader(res);
        Parent root = loader.load();
        
        
        Scene scene = new Scene(root, MAIN_WINDOW_WIDTH, MAIN_WINDOW_HEIGHT);
        
        // Package window
        stage.getIcons().add(new Image("img/logo.png"));
        stage.setTitle("ExifExplorer");
        stage.setScene(scene);
        stage.setMinWidth(MAIN_WINDOW_WIDTH);
        stage.setMinHeight(MAIN_WINDOW_HEIGHT);
        stage.show();
    }
    
    
    // ============================================================================================================================================ \\
    
    
    public void doAskImport()
    {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle(locale.getString("Importing.FileChooser.Info.WindowTitle"));
        File defaultDirectory = new File("C:/");
        chooser.setInitialDirectory(defaultDirectory);
        
        File selectedDirectory = chooser.showDialog(null);
        if (selectedDirectory == null || !selectedDirectory.exists())
        {
            logger.warn(locale.getString("Importing.FileChooser.Error.InvalidLocation.Content").replace("[%s]", selectedDirectory != null ? selectedDirectory.getPath() : "[null]"));
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(locale.getString("Importing.FileChooser.Error.InvalidLocation.Title"));
            alert.setHeaderText(locale.getString("Importing.FileChooser.Error.InvalidLocation.Header"));
            alert.setContentText(locale.getString("Importing.FileChooser.Error.InvalidLocation.Content"));
            alert.showAndWait();
        }
        else
        {
            importDir = selectedDirectory;
            logger.info(locale.getString("Importing.FileChooser.Info.ValidLocation").replace("[%s]", selectedDirectory.getPath()));
        }
    }
    
    
    // ============================================================================================================================================ \\
    
    
    public URL getResourceURL(String path)
    {
        return Main.class.getClassLoader().getResource(path);
    }
    
    
    public static Main getInstance()
    {
        return instance;
    }
    
    
    public static Logger getLogger()
    {
        return logger;
    }
    
    
    public ResourceBundle getLocale()
    {
        return locale;
    }
    
    // ============================================================================================================================================ \\
}
