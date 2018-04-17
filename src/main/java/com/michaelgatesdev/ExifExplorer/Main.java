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
import com.michaelgatesdev.ExifExplorer.photo.Photo;
import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

public class Main extends Application
{
    // ============================================================================================================================================ \\
    
    private final static Logger logger = Logger.getLogger(Main.class.getSimpleName());
    
    private static Main instance;
    
    private ResourceBundle locale; // TODO
    
    private File rootDir;
    private File resourcesDir;
    private File imageResDir;
    private File textResDir;
    private File backupsDir;
    
    private File importDir;
    private File exportDir;
    
    private List<Photo> photos;
    
    
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
        // Create/Initialize all the directories */
        initializeDirectories();
        
        // Grab locale bundle
        locale = ResourceBundle.getBundle("Locale", /*Locale.JAPAN,*/ new UTF8Control());
        
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
    public void start(Stage stage)
    {
        GuiManager.getInstance().setWindow(stage);
        GuiManager.getInstance().setupWindow();
        GuiManager.getInstance().showTitleScreen();
    }
    
    
    // ============================================================================================================================================ \\


//    public void doAskImport()
//    {
//        DirectoryChooser chooser = new DirectoryChooser();
//        chooser.setTitle(locale.getString("Importing.FileChooser.Info.WindowTitle"));
//        File defaultDirectory = new File("C:/");
//        chooser.setInitialDirectory(defaultDirectory);
//
//        File selectedDirectory = chooser.showDialog(null);
//        if (selectedDirectory == null || !selectedDirectory.exists())
//        {
//            logger.warn(String.format(locale.getString("Importing.FileChooser.Error.InvalidLocation.Content"), selectedDirectory != null ? selectedDirectory.getPath() : "[null]"));
//
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle(locale.getString("Importing.FileChooser.Error.InvalidLocation.Title"));
//            alert.setHeaderText(locale.getString("Importing.FileChooser.Error.InvalidLocation.Header"));
//            alert.setContentText(locale.getString("Importing.FileChooser.Error.InvalidLocation.Content"));
//            alert.showAndWait();
//        }
//        else
//        {
//            importDir = selectedDirectory;
//            logger.info(String.format(locale.getString("Importing.FileChooser.Info.ValidLocation"), selectedDirectory.getPath()));
//
//            doImport();
//        }
//    }
//
//
//    public void doImport()
//    {
//        if (importDir == null || !importDir.exists())
//        {
//            logger.error(locale.getString("Import.Error.DoesNotExist"));
//            return;
//        }
//
//
//        loadPhotos();
//
//
//        if (sacrifice == null)
//        {
//            logger.error("Can not do mess with UI because the sacrifice is not sufficient.");
//            return;
//        }
//
//        // unlock views
//        guiManager.unlockFilters(sacrifice);
//        guiManager.unlockViews(sacrifice);
//    }
//
//
//    private void loadPhotos()
//    {
//        this.photos = new ArrayList<>();
//        File[] files = importDir.listFiles();
//
//        for (File f : files)
//        {
//            Photo photo = new Photo(f);
//            photos.add(photo);
//        }
//
//        if (sacrifice == null)
//        {
//            logger.error("Can not do mess with UI because the sacrifice is not sufficient.");
//            return;
//        }
//
//        guiManager.populateTable(sacrifice, this.photos);
//        guiManager.updateWorkspaceInfo(sacrifice, importDir, exportDir);
//    }
//
//
//    public void doAskExport()
//    {
//        DirectoryChooser chooser = new DirectoryChooser();
//        chooser.setTitle(locale.getString("Exporting.FileChooser.Info.WindowTitle"));
//        File defaultDirectory = new File(importDir.toURI());
//        chooser.setInitialDirectory(defaultDirectory);
//
//        File selectedDirectory = chooser.showDialog(null);
//        if (selectedDirectory == null || !selectedDirectory.exists())
//        {
//            logger.warn(String.format(locale.getString("Exporting.FileChooser.Error.InvalidLocation.Content"), selectedDirectory != null ? selectedDirectory.getPath() : "[null]"));
//
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle(locale.getString("Exporting.FileChooser.Error.InvalidLocation.Title"));
//            alert.setHeaderText(locale.getString("Exporting.FileChooser.Error.InvalidLocation.Header"));
//            alert.setContentText(locale.getString("Exporting.FileChooser.Error.InvalidLocation.Content"));
//            alert.showAndWait();
//        }
//        else
//        {
//            //TODO see if they selected the import dir and ask if they want to create a new folder there
//            exportDir = selectedDirectory;
//            logger.info(locale.getString("Exporting.FileChooser.Info.ValidLocation").replace("[%s]", selectedDirectory.getPath()));
//        }
//    }
//
//
//    public void doAskQuit()
//    {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle(locale.getString("Main.AskQuit"));
//        alert.setHeaderText(locale.getString("Main.AskQuit"));
//        alert.setContentText(locale.getString("Main.AskQuitLong"));
//        Optional<ButtonType> result = alert.showAndWait();
//        result.ifPresent(buttonType ->
//        {
//            if (ButtonType.OK.equals(result.get()))
//            {
//                Platform.exit();
//                System.exit(0);
//            }
//        });
//    }
    
    
    // ============================================================================================================================================ \\
    
    
    public static Main getInstance()
    {
        return instance;
    }
    
    
    public ResourceBundle getLocale()
    {
        return locale;
    }
    
    
    // ============================================================================================================================================ \\
}
