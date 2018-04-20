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
import com.michaelgatesdev.ExifExplorer.gui.StageManager;
import com.michaelgatesdev.ExifExplorer.gui.components.PhotoRow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable
{
    private final static Logger logger = Logger.getLogger(MainSceneController.class.getSimpleName());
    
    private final StageManager stageManager;
    
    @FXML
    private TableView<PhotoRow> table;
    
    
    public MainSceneController(StageManager stageManager)
    {
        this.stageManager = stageManager;
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.refreshTable();
    }
    
    
    void refreshTable()
    {
        stageManager.populateTable(table, Main.getInstance().getPhotos());
    }
}
