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

import com.michaelgatesdev.ExifExplorer.photo.Photo;
import com.michaelgatesdev.ExifExplorer.photo.properties.PhotoPropertyType;
import javafx.beans.property.SimpleStringProperty;

public class PhotoRow
{
    // ============================================================================================================================================ \\
    
    private final SimpleStringProperty dateTaken      = new SimpleStringProperty("");
    private final SimpleStringProperty sizeDimensions = new SimpleStringProperty("");
    private final SimpleStringProperty manufacturer   = new SimpleStringProperty("");
    private final SimpleStringProperty model          = new SimpleStringProperty("");
    private final SimpleStringProperty iso            = new SimpleStringProperty("");
    private final SimpleStringProperty aperture       = new SimpleStringProperty("");
    private final SimpleStringProperty shutterSpeed   = new SimpleStringProperty("");
    private final SimpleStringProperty focalLength    = new SimpleStringProperty("");
    
    // ============================================================================================================================================ \\
    
    
    public PhotoRow()
    {
    }
    
    
    public PhotoRow(Photo p)
    {
        if (p.hasProperty(PhotoPropertyType.DATE_TIME))
        {
            setDateTaken(p.getProperty(PhotoPropertyType.DATE_TIME).asString());
        }
        if (p.hasProperty(PhotoPropertyType.SIZE_DIMENSIONS))
        {
            setSizeDimensions(p.getProperty(PhotoPropertyType.SIZE_DIMENSIONS).asString());
        }
        if (p.hasProperty(PhotoPropertyType.MANUFACTURER))
        {
            setManufacturer(p.getProperty(PhotoPropertyType.MANUFACTURER).asString());
        }
        if (p.hasProperty(PhotoPropertyType.MODEL))
        {
            setModel(p.getProperty(PhotoPropertyType.MODEL).asString());
        }
        if (p.hasProperty(PhotoPropertyType.ISO))
        {
            setIso(p.getProperty(PhotoPropertyType.ISO).asString());
        }
        if (p.hasProperty(PhotoPropertyType.APERTURE))
        {
            setAperture(p.getProperty(PhotoPropertyType.APERTURE).asString());
        }
        if (p.hasProperty(PhotoPropertyType.SHUTTER_SPEED))
        {
            setShutterSpeed(p.getProperty(PhotoPropertyType.SHUTTER_SPEED).asString());
        }
        if (p.hasProperty(PhotoPropertyType.FOCAL_LENGTH))
        {
            setFocalLength(p.getProperty(PhotoPropertyType.FOCAL_LENGTH).asString());
        }
    }
    
    
    // ============================================================================================================================================ \\
    
    
    public PhotoRow(String dateTaken, String sizeDimensions, String manufacturer, String model, String iso, String aperture, String shutterSpeed, String focalLength)
    {
        setDateTaken(dateTaken);
        setSizeDimensions(sizeDimensions);
        setManufacturer(manufacturer);
        setModel(model);
        setIso(iso);
        setAperture(aperture);
        setShutterSpeed(shutterSpeed);
        setFocalLength(focalLength);
    }
    
    // ============================================================================================================================================ \\
    
    
    public void setDateTaken(String dateTaken)
    {
        this.dateTaken.set(dateTaken);
    }
    
    
    public void setSizeDimensions(String sizeDimensions)
    {
        this.sizeDimensions.set(sizeDimensions);
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
    
    // ============================================================================================================================================ \\
    
    
    public String getDateTaken()
    {
        return dateTaken.get();
    }
    
    
    public SimpleStringProperty dateTakenProperty()
    {
        return dateTaken;
    }
    
    
    public String getSizeDimensions()
    {
        return sizeDimensions.get();
    }
    
    
    public SimpleStringProperty sizeDimensionsProperty()
    {
        return sizeDimensions;
    }
    
    
    public String getManufacturer()
    {
        return manufacturer.get();
    }
    
    
    public SimpleStringProperty manufacturerProperty()
    {
        return manufacturer;
    }
    
    
    public String getModel()
    {
        return model.get();
    }
    
    
    public SimpleStringProperty modelProperty()
    {
        return model;
    }
    
    
    public String getIso()
    {
        return iso.get();
    }
    
    
    public SimpleStringProperty isoProperty()
    {
        return iso;
    }
    
    
    public String getAperture()
    {
        return aperture.get();
    }
    
    
    public SimpleStringProperty apertureProperty()
    {
        return aperture;
    }
    
    
    public String getShutterSpeed()
    {
        return shutterSpeed.get();
    }
    
    
    public SimpleStringProperty shutterSpeedProperty()
    {
        return shutterSpeed;
    }
    
    
    public String getFocalLength()
    {
        return focalLength.get();
    }
    
    
    public SimpleStringProperty focalLengthProperty()
    {
        return focalLength;
    }
    
    // ============================================================================================================================================ \\
    
    
    @Override
    public String toString()
    {
        return "PhotoRow{" +
                "dateTaken=" + dateTaken +
                ", size/dimensions=" + sizeDimensions +
                ", manufacturer=" + manufacturer +
                ", model=" + model +
                ", iso=" + iso +
                ", aperture=" + aperture +
                ", shutterSpeed=" + shutterSpeed +
                ", focalLength=" + focalLength +
                '}';
    }
    
    
    // ============================================================================================================================================ \\
}
