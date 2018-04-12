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

import com.michaelgatesdev.ExifExplorer.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class TopMenuBarController implements Initializable
{
    @FXML
    MenuItem menuItemImport;
    @FXML
    MenuItem menuItemExport;
    @FXML
    MenuItem menuItemQuit;
    
    @FXML
    MenuItem menuItemUndo;
    @FXML
    MenuItem menuItemRedo;
    @FXML
    MenuItem menuItemCut;
    @FXML
    MenuItem menuItemCopy;
    @FXML
    MenuItem menuItemPaste;
    
    @FXML
    MenuItem menuItemPrefs;
    @FXML
    MenuItem menuItemStyles;
    
    @FXML
    MenuItem menuItemAbout;
    @FXML
    MenuItem menuItemCredits;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        menuItemImport.setOnAction(event -> Main.getInstance().doAskImport(menuItemImport.getGraphic()));
        menuItemExport.setOnAction(event -> Main.getInstance().doAskExport(menuItemExport.getGraphic()));
        menuItemQuit.setOnAction(event -> Main.getInstance().doAskQuit());
        
        
    }
}
