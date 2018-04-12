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

import com.michaelgatesdev.ExifExplorer.gui.components.PhotoRow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewContainerController implements Initializable
{
    @FXML
    private TableView<PhotoRow> table;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        final ObservableList<PhotoRow> data = FXCollections.observableArrayList(
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM"),
//                new PhotoRow("04/02/1984", "1920 x 1080", "50.0 MB", "Canon", "Canon 6D", "iso-100", "F2.8", "1/125", "50 MM")
//        );
//
//        Platform.runLater(() -> table.setItems(data));
    }
}
