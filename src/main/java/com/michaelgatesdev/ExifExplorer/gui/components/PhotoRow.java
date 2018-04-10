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


package com.michaelgatesdev.ExifExplorer.gui.components;

import javafx.beans.property.SimpleStringProperty;

public class PhotoRow
{
    private final SimpleStringProperty dateTaken    = new SimpleStringProperty("");
    private final SimpleStringProperty dimensions   = new SimpleStringProperty("");
    private final SimpleStringProperty size         = new SimpleStringProperty("");
    private final SimpleStringProperty manufacturer = new SimpleStringProperty("");
    private final SimpleStringProperty model        = new SimpleStringProperty("");
    private final SimpleStringProperty iso          = new SimpleStringProperty("");
    private final SimpleStringProperty aperture     = new SimpleStringProperty("");
    private final SimpleStringProperty shutterSpeed = new SimpleStringProperty("");
    private final SimpleStringProperty focalLength  = new SimpleStringProperty("");
    
    
    public PhotoRow()
    {
    }
    
    
    public PhotoRow(String dateTaken, String dimensions, String size, String manufacturer, String model, String iso, String aperture, String shutterSpeed, String focalLength)
    {
        setDateTaken(dateTaken);
        setDimensions(dimensions);
        setSize(size);
        setManufacturer(manufacturer);
        setModel(model);
        setIso(iso);
        setAperture(aperture);
        setShutterSpeed(shutterSpeed);
        setFocalLength(focalLength);
    }
    
    
    public void setDateTaken(String dateTaken)
    {
        this.dateTaken.set(dateTaken);
    }
    
    
    public void setDimensions(String dimensions)
    {
        this.dimensions.set(dimensions);
    }
    
    
    public void setSize(String size)
    {
        this.size.set(size);
    }
    
    
    public void setManufacturer(String manufacturer)
    {
        this.manufacturer.set(manufacturer);
    }
    
    
    public void setModel(String model)
    {
        this.model.set(model);
    }
    
    
    public void setIso(String iso)
    {
        this.iso.set(iso);
    }
    
    
    public void setAperture(String aperture)
    {
        this.aperture.set(aperture);
    }
    
    
    public void setShutterSpeed(String shutterSpeed)
    {
        this.shutterSpeed.set(shutterSpeed);
    }
    
    
    public void setFocalLength(String focalLength)
    {
        this.focalLength.set(focalLength);
    }
}
