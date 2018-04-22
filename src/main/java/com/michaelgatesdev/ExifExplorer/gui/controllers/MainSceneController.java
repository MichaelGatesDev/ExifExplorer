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

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.michaelgatesdev.ExifExplorer.Main;
import com.michaelgatesdev.ExifExplorer.gui.StageManager;
import com.michaelgatesdev.ExifExplorer.gui.components.PhotoRow;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
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
    
    @FXML
    private
    JFXRadioButton beforeDateTimeRadioBtn, afterDateTimeRadioBtn, betweenDateTimeRadioBtn;
    @FXML
    private
    JFXDatePicker datePickerA, datePickerB;
    @FXML
    private
    JFXTimePicker timePickerA, timePickerB;
    
    @FXML
    private JFXRadioButton lessThanSizeBtn, greaterThanSizeBtn, betweenSizeBtn;
    @FXML
    private JFXTextField widthFieldA, heightFieldA, sizeFieldA, widthFieldB, heightFieldB, sizeFieldB;
    
    
    /**
     * @param stageManager
     */
    public MainSceneController(StageManager stageManager)
    {
        this.stageManager = stageManager;
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // DATE & TIME
        beforeDateTimeRadioBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleTrifectaRadioButtons(beforeDateTimeRadioBtn, newValue, afterDateTimeRadioBtn, false, betweenDateTimeRadioBtn, false, datePickerB, timePickerB));
        afterDateTimeRadioBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleTrifectaRadioButtons(beforeDateTimeRadioBtn, false, afterDateTimeRadioBtn, newValue, betweenDateTimeRadioBtn, false, datePickerB, timePickerB));
        betweenDateTimeRadioBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleTrifectaRadioButtons(beforeDateTimeRadioBtn, false, afterDateTimeRadioBtn, false, betweenDateTimeRadioBtn, newValue, datePickerB, timePickerB));
        
        // SIZE & DIMENSIONS
        lessThanSizeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleTrifectaRadioButtons(lessThanSizeBtn, newValue, greaterThanSizeBtn, false, betweenSizeBtn, false, widthFieldB, heightFieldB, sizeFieldB));
        greaterThanSizeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleTrifectaRadioButtons(lessThanSizeBtn, false, greaterThanSizeBtn, newValue, betweenSizeBtn, false, widthFieldB, heightFieldB, sizeFieldB));
        betweenSizeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleTrifectaRadioButtons(lessThanSizeBtn, false, greaterThanSizeBtn, false, betweenSizeBtn, newValue, widthFieldB, heightFieldB, sizeFieldB));
        
        
        // Update table with imported photo data
        this.refreshTable();
    }
    
    
    private void toggleTrifectaRadioButtons(JFXRadioButton buttonA, boolean a, JFXRadioButton buttonB, boolean b, JFXRadioButton buttonC, boolean c, Control... controlsToEnableDisable)
    {
        if (a)
        {
            buttonB.setSelected(false);
            buttonC.setSelected(false);
        }
        if (b)
        {
            buttonA.setSelected(false);
            buttonC.setSelected(false);
        }
        if (c)
        {
            buttonA.setSelected(false);
            buttonB.setSelected(false);
        }
        for (Control control : controlsToEnableDisable)
        {
            control.setDisable(!c);
        }
    }
    
    
    private void refreshTable()
    {
        stageManager.populateTable(table, Main.getInstance().getPhotos());
    }
}
