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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;

import java.util.List;

public class GuiManager
{
    // ============================================================================================================================================ \\
    
    private static final Logger logger = Logger.getLogger(GuiManager.class);
    
    private Main main;
    
    // ============================================================================================================================================ \\
    
    
    public GuiManager(Main main)
    {
        this.main = main;
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
    
    // ============================================================================================================================================ \\
}
