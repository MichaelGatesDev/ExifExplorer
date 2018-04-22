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

import com.michaelgatesdev.ExifExplorer.exceptions.InvalidApertureException;
import com.michaelgatesdev.ExifExplorer.exceptions.InvalidShutterSpeedException;
import com.michaelgatesdev.ExifExplorer.gui.StageManager;
import com.michaelgatesdev.ExifExplorer.gui.controllers.TitleSceneController;
import com.michaelgatesdev.ExifExplorer.locale.UTF8Control;
import com.michaelgatesdev.ExifExplorer.photo.Photo;
import com.michaelgatesdev.ExifExplorer.util.ToolTipDefaultsFixer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Main extends Application
{
    // ============================================================================================================================================ \\
    
    private final static Logger logger = Logger.getLogger(Main.class.getSimpleName());
    
    private static Main instance;
    
    private StageManager stageManager;
    
    private ResourceBundle locale;
    
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
        ToolTipDefaultsFixer.setTooltipTimers(250, 2500, 50);
        
        this.stageManager = new StageManager(stage);
        this.stageManager.switchToScene("fxml/TitleScene.fxml", new TitleSceneController(stageManager));
    }
    
    
    // ============================================================================================================================================ \\
    
    
    public void loadImportedPhotos()
    {
        if (importDir == null || !importDir.exists())
        {
            logger.error(locale.getString("Import.Error.DoesNotExist"));
            return;
        }
        
        this.photos = new ArrayList<>();
        File[] files = importDir.listFiles();
        
        
        if (files == null || files.length == 0)
        {
            logger.error("There are no photos to load.");
            return;
        }
        
        for (File f : files)
        {
            Photo photo;
            try
            {
                photo = new Photo(f);
                photos.add(photo);
                logger.debug("Loaded " + f.getName());
            }
            catch (InvalidApertureException | InvalidShutterSpeedException e)
            {
                logger.error("There was an error loading " + f.getName());
                e.printStackTrace();
            }
        }
    }
    
    
    public void quit()
    {
        logger.info("Exiting the application..");
        logger.debug("Performing platform exit");
        Platform.exit();
        logger.debug("Performing system exit");
        System.exit(0);
    }
    
    
    // ============================================================================================================================================ \\
    
    
    public static Main getInstance()
    {
        return instance;
    }
    
    
    public ResourceBundle getLocale()
    {
        return locale;
    }
    
    
    public void setImportPath(File f)
    {
        this.importDir = f;
    }
    
    
    public void setExportPath(File f)
    {
        this.exportDir = f;
    }
    
    
    public List<Photo> getPhotos()
    {
        return photos;
    }
    
    
    // ============================================================================================================================================ \\
}
