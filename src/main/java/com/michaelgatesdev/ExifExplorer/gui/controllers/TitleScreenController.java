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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class TitleScreenController implements Initializable
{
    private final static Logger logger = Logger.getLogger(TitleScreenController.class.getSimpleName());
    
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
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        importButton.setOnAction(event -> GuiManager.getInstance().showImportScreen());
    }
}