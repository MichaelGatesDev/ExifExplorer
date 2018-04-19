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
import com.michaelgatesdev.ExifExplorer.gui.GuiManager;
import com.michaelgatesdev.ExifExplorer.util.FileUtil;
import com.michaelgatesdev.ExifExplorer.util.JFXUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsScreenController implements Initializable
{
    private final static Logger logger = Logger.getLogger(SettingsScreenController.class.getSimpleName());
    
    @FXML
    TextField defaultWorkspaceName;
    @FXML
    JFXButton workspaceNameSetButton;
    @FXML
    JFXButton importPathButton;
    @FXML
    JFXButton exportPathButton;
    @FXML
    TextField importPathField;
    @FXML
    TextField exportPathField;
    @FXML
    TextField rememberRecent;
    @FXML
    JFXButton rememberRecentSetButton;
    
    @FXML
    JFXButton titleButton;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        titleButton.setOnAction(event -> GuiManager.getInstance().showTitleScreen());
        
        importPathButton.setOnAction(event ->
        {
            logger.debug("Selecting import path...");
            File f = FileUtil.showDirectoryChooser("Select a folder to set as the default import path");
            if (f == null || !f.exists())
            {
                JFXUtil.showErrorDialog("Error", "Invalid import path!", "The path you selected is either invalid or non-existent.");
                logger.error("Selected an invalid or non-existent directory for the import path");
                return;
            }
            importPathField.setText(f.getPath());
            logger.info(String.format("Default import path was set to %s", f.getPath()));
            JFXUtil.showInfoDialog("Success", "Set default import path!", String.format("Default import path was set to %s", f.getPath()));
        });
        exportPathButton.setOnAction(event ->
        {
            logger.debug("Selecting export path...");
            File f = FileUtil.showDirectoryChooser("Select a folder to set as the default export path");
            if (f == null || !f.exists())
            {
                JFXUtil.showErrorDialog("Error", "Invalid export path!", "The path you selected is either invalid or non-existent.");
                logger.error("Selected an invalid or non-existent directory for the export path");
                return;
            }
            exportPathField.setText(f.getPath());
            logger.info(String.format("Default export path was set to %s", f.getPath()));
            JFXUtil.showInfoDialog("Success", "Set default export path!", String.format("Default export path was set to %s", f.getPath()));
        });
    }
    
}
