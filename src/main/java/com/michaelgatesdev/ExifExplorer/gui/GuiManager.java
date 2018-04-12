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
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import org.apache.log4j.Logger;

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
    
    // ============================================================================================================================================ \\
}
