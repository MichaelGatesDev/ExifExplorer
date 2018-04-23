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
import com.michaelgatesdev.ExifExplorer.photo.FilteredPhotosList;
import com.michaelgatesdev.ExifExplorer.photo.SizeDimensions;
import com.michaelgatesdev.ExifExplorer.photo.filters.Criteria;
import com.michaelgatesdev.ExifExplorer.photo.filters.datetime.*;
import com.michaelgatesdev.ExifExplorer.photo.filters.sizedimensions.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Control;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableView;
import org.apache.log4j.Logger;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class MainSceneController implements Initializable
{
    private final static Logger logger = Logger.getLogger(MainSceneController.class.getSimpleName());
    
    private final StageManager stageManager;
    
    private Set<Criteria> activeFilters;
    
    @FXML
    private TableView<PhotoRow> table;
    
    @FXML
    private
    JFXRadioButton beforeDateTimeRadioBtn, afterDateTimeRadioBtn, betweenDateTimeRadioBtn, equalDateBtn;
    @FXML
    private
    JFXDatePicker datePickerA, datePickerB;
    @FXML
    private
    JFXTimePicker timePickerA, timePickerB;
    
    @FXML
    private JFXRadioButton smallerThanSizeBtn, largerThanSizeBtn, betweenSizeBtn, equalSizeBtn;
    @FXML
    private JFXTextField widthFieldA, heightFieldA, sizeFieldA, widthFieldB, heightFieldB, sizeFieldB;
    
    
    @FXML
    private JFXTextField isoField, shutterSpeedField, apertureField, focalLengthField;
    
    
    @FXML
    private Hyperlink resetFiltersButton;
    
    
    public MainSceneController(StageManager stageManager)
    {
        this.stageManager = stageManager;
        this.activeFilters = new HashSet<>();
    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // DATE & TIME
        beforeDateTimeRadioBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(beforeDateTimeRadioBtn, newValue, afterDateTimeRadioBtn, false, equalDateBtn, false, betweenDateTimeRadioBtn, false, datePickerB, timePickerB));
        afterDateTimeRadioBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(beforeDateTimeRadioBtn, false, afterDateTimeRadioBtn, newValue, equalDateBtn, false, betweenDateTimeRadioBtn, false, datePickerB, timePickerB));
        equalDateBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(beforeDateTimeRadioBtn, false, afterDateTimeRadioBtn, false, equalDateBtn, newValue, betweenDateTimeRadioBtn, false, datePickerB, timePickerB));
        betweenDateTimeRadioBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(beforeDateTimeRadioBtn, false, afterDateTimeRadioBtn, false, equalDateBtn, false, betweenDateTimeRadioBtn, newValue, datePickerB, timePickerB));
        
        // SIZE & DIMENSIONS
        smallerThanSizeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(smallerThanSizeBtn, newValue, largerThanSizeBtn, false, equalSizeBtn, false, betweenSizeBtn, false, widthFieldB, heightFieldB, sizeFieldB));
        largerThanSizeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(smallerThanSizeBtn, false, largerThanSizeBtn, newValue, equalSizeBtn, false, betweenSizeBtn, false, widthFieldB, heightFieldB, sizeFieldB));
        equalSizeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(smallerThanSizeBtn, false, largerThanSizeBtn, false, equalSizeBtn, newValue, betweenSizeBtn, false, widthFieldB, heightFieldB, sizeFieldB));
        betweenSizeBtn.selectedProperty().addListener((observable, oldValue, newValue) -> toggleGroupRadioButtons(smallerThanSizeBtn, false, largerThanSizeBtn, false, equalSizeBtn, false, betweenSizeBtn, newValue, widthFieldB, heightFieldB, sizeFieldB));
        
        
        // Update table with imported photo data
        stageManager.repopulateTable(table, Main.getInstance().getPhotos());
    }
    
    
    void checkFilters()
    {
        //TODO user property change listeners instead
        
        
        // datetime and time
        DateTimeCriteria dtCrit = null;
        LocalDate date = datePickerA.getValue() != null ? datePickerA.getValue() : LocalDate.MIN;
        LocalTime time = timePickerA.getValue() != null ? timePickerA.getValue() : LocalTime.MIN;
        LocalDateTime ldt = LocalDateTime.of(date, time);
        if (beforeDateTimeRadioBtn.isSelected())
        {
            dtCrit = new BeforeDateTimeCriteria(ldt);
        }
        else if (afterDateTimeRadioBtn.isSelected())
        {
            dtCrit = new AfterDateTimeCriteria(ldt);
        }
        else if (equalDateBtn.isSelected())
        {
            dtCrit = new ExactDateTimeCriteria(ldt);
        }
        else if (betweenDateTimeRadioBtn.isSelected())
        {
            LocalDate dateB = datePickerB.getValue() != null ? datePickerB.getValue() : LocalDate.MIN;
            LocalTime timeB = timePickerB.getValue() != null ? timePickerB.getValue() : LocalTime.MIN;
            LocalDateTime ldtB = LocalDateTime.of(dateB, timeB);
            
            dtCrit = new BetweenDateTimeCriteria(ldt, ldtB);
        }
        if (dtCrit != null)
        {
            activeFilters.add(dtCrit);
        }
        
        
        // Dimensions & Size
        SizeDimensionsCriteria sdCrit = null;
        long width = !widthFieldA.getText().isEmpty() ? Long.parseLong(widthFieldA.getText()) : 0;
        long height = !heightFieldA.getText().isEmpty() ? Long.parseLong(heightFieldA.getText()) : 0;
        long size = !sizeFieldA.getText().isEmpty() ? Long.parseLong(sizeFieldA.getText()) : 0;
        if (smallerThanSizeBtn.isSelected())
        {
            sdCrit = new SmallerThanSizeDimensionsCriteria(new SizeDimensions(width, height, size));
        }
        else if (largerThanSizeBtn.isSelected())
        {
            sdCrit = new LargerThanSizeDimensionsCriteria(new SizeDimensions(width, height, size));
        }
        else if (equalSizeBtn.isSelected())
        {
            sdCrit = new ExactSizeDimensionsCriteria(new SizeDimensions(width, height, size));
        }
        else if (betweenSizeBtn.isSelected())
        {
            long widthB = !widthFieldB.getText().isEmpty() ? Long.parseLong(widthFieldB.getText()) : 0;
            long heightB = !heightFieldB.getText().isEmpty() ? Long.parseLong(heightFieldB.getText()) : 0;
            long sizeB = !sizeFieldB.getText().isEmpty() ? Long.parseLong(sizeFieldB.getText()) : 0;
            
            sdCrit = new BetweenSizeDimensionsCriteria(new SizeDimensions(width, height, size), new SizeDimensions(widthB, heightB, sizeB));
        }
        if (sdCrit != null)
        {
            activeFilters.add(sdCrit);
        }
        
        
        // repopulate table
        FilteredPhotosList fpl = new FilteredPhotosList(Main.getInstance().getPhotos(), activeFilters);
        stageManager.repopulateTable(table, fpl.getResult());
    }
    
    
    /**
     * @param buttonA                 The first button in the trifecta
     * @param a                       If the first button is selected
     * @param buttonB                 The second button in the trifecta
     * @param b                       If the second button is selected
     * @param buttonC                 The third button in the trifecta, which has added behavior of enabling/disabling the specified controls
     * @param c                       If the third button is selected
     * @param controlsToEnableDisable Controls which should be enabled/disabled based on 'c'
     */
    private void toggleGroupRadioButtons(JFXRadioButton buttonA, boolean a, JFXRadioButton buttonB, boolean b, JFXRadioButton buttonC, boolean c, JFXRadioButton buttonD, boolean d, Control... controlsToEnableDisable)
    {
        if (a)
        {
            buttonB.setSelected(false);
            buttonC.setSelected(false);
            buttonD.setSelected(false);
        }
        if (b)
        {
            buttonA.setSelected(false);
            buttonC.setSelected(false);
            buttonD.setSelected(false);
        }
        if (c)
        {
            buttonA.setSelected(false);
            buttonB.setSelected(false);
            buttonD.setSelected(false);
        }
        if (d)
        {
            buttonA.setSelected(false);
            buttonB.setSelected(false);
            buttonC.setSelected(false);
        }
        for (Control control : controlsToEnableDisable)
        {
            control.setDisable(!d);
        }
    }
}
