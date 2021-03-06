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
import com.michaelgatesdev.ExifExplorer.gui.StageManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class TitleSceneController implements Initializable
{
    private final static Logger logger = Logger.getLogger(TitleSceneController.class.getSimpleName());
    
    private final StageManager stageManager;
    
    @FXML
    AnchorPane rootPane;
    
    @FXML
    JFXButton importButton;
    @FXML
    JFXButton settingsButton;
    @FXML
    JFXButton aboutButton;
    @FXML
    JFXButton helpButton;
    @FXML
    JFXButton exitButton;
    
    
    public TitleSceneController(StageManager stageManager)
    {
        this.stageManager = stageManager;
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        importButton.setOnAction(event -> stageManager.switchToScene("fxml/ImportScene.fxml", new ImportSceneController(stageManager)));
        settingsButton.setOnAction(event -> stageManager.switchToScene("fxml/SettingsScene.fxml", new SettingsSceneController(stageManager)));
        aboutButton.setOnAction(event -> stageManager.switchToScene("fxml/AboutScene.fxml", new AboutSceneController(stageManager)));
        helpButton.setOnAction(event -> stageManager.switchToScene("fxml/HelpScene.fxml", new HelpSceneController(stageManager)));
        exitButton.setOnAction(event -> stageManager.showQuitDialog());
    }
}
