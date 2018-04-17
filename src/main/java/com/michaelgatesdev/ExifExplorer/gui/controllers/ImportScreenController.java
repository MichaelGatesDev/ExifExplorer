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


package com.michaelgatesdev.ExifExplorer.gui.controllers;

import com.jfoenix.controls.JFXButton;
import com.michaelgatesdev.ExifExplorer.Main;
import com.michaelgatesdev.ExifExplorer.gui.GuiManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class ImportScreenController implements Initializable
{
    private final static Logger logger = Logger.getLogger(ImportScreenController.class.getSimpleName());
    
    @FXML
    JFXButton continueButton;
    @FXML
    JFXButton titleButton;
    @FXML
    JFXButton importPathButton;
    @FXML
    JFXButton exportPathButton;
    @FXML
    TextField importPathField;
    @FXML
    TextField exportPathField;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        titleButton.setOnAction(event -> GuiManager.getInstance().showTitleScreen());
        continueButton.setOnAction(event ->
        {
            boolean pathsGood = true;
            if (!checkImportPath())
            {
                pathsGood = false;
            }
            if (!checkExportPath())
            {
                pathsGood = false;
            }
            
            if (pathsGood)
            {
                GuiManager.getInstance().showMainScreen();
            }
        });
        
        importPathButton.setOnAction(event -> {
        });
        exportPathButton.setOnAction(event -> {
        });
    }
    
    
    private void showInvalidPathMessage(TextField field)
    {
        String original = field.getText();
        
        field.setText("Invalid path!");
        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                Platform.runLater(() -> field.setText(original));
            }
        }, 1500);
    }
    
    
    private boolean checkImportPath()
    {
        String rawImportPath = importPathField.getText();
        File importPath = new File(rawImportPath);
        if (importPath.exists())
        {
            logger.info("Set import path to " + rawImportPath);
            Main.getInstance().setImportPath(importPath);
            return true;
        }
        else
        {
            logger.error("Invalid import path");
            showInvalidPathMessage(importPathField);
        }
        return false;
    }
    
    
    private boolean checkExportPath()
    {
        String rawExportPath = exportPathField.getText();
        File exportPath = new File(rawExportPath);
        if (exportPath.exists())
        {
            logger.info("Set export path to " + rawExportPath);
            Main.getInstance().setExportPath(exportPath);
            return true;
        }
        else
        {
            logger.error("Invalid export path");
            showInvalidPathMessage(exportPathField);
        }
        return false;
    }
}
