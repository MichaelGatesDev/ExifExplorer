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


package com.michaelgatesdev.ExifExplorer.photo;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.michaelgatesdev.ExifExplorer.exceptions.InvalidApertureException;
import com.michaelgatesdev.ExifExplorer.exceptions.InvalidShutterSpeedException;
import com.michaelgatesdev.ExifExplorer.photo.properties.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Photo
{
    // ============================================================================================================================================ \\
    
    private File               file;
    private Metadata           metadata;
    private Set<PhotoProperty> properties;
    
    private long size;
    private long width;
    private long height;
    
    // ============================================================================================================================================ \\
    
    
    /**
     * Constructor should only be used for testing
     */
    public Photo()
    {
        this.properties = new HashSet<>();
        this.file = new File(System.getProperty("user.dir") + "/" + UUID.randomUUID().toString().substring(0, 8) + ".jpg"); // assign random file names for testing
    }
    
    
    public Photo(File f) throws InvalidApertureException, InvalidShutterSpeedException
    {
        this.file = f;
        this.properties = new HashSet<>();
        try
        {
            metadata = ImageMetadataReader.readMetadata(f);
            
            for (Directory dir : metadata.getDirectories())
            {
                for (Tag tag : dir.getTags())
                {
                    switch (tag.getTagName())
                    {
                        case "ISO Speed Ratings":
                        {
                            String s = tag.getDescription();
                            int iso = Integer.parseInt(s);
                            setISO(iso);
//                            System.out.println("Set iso to " + this.getProperty(PhotoPropertyType.ISO));
                            break;
                        }
                        case "Aperture Value":
                        {
                            String s = tag.getDescription();
                            setAperture(Float.parseFloat(s.toLowerCase().replace("f/", "")));
//                            System.out.println("Set aperture to " + this.getProperty(PhotoPropertyType.APERTURE));
                            break;
                        }
                        case "Exposure Time" /*"Shutter Speed Value"*/:
                        {
                            String s = tag.getDescription().toLowerCase().replace("sec", "").trim();
                            String[] ss = s.split("/");
                            
                            if (ss.length != 2)
                            {
                                return;
                            }
                            
                            setShutterSpeed(Integer.parseInt(ss[0]), Integer.parseInt(ss[1]));
//                            System.out.println("Set shutter speed to " + this.getProperty(PhotoPropertyType.SHUTTER_SPEED));
                            break;
                        }
                        case "Date/Time Original":
                        {
                            // 2008:09:20 09:01:07
                            String s = tag.getDescription();
                            String[] ss = s.split(" ");
                            String[] rawDate = ss[0].split(":");
                            String[] rawTime = ss[1].split(":");
                            
                            int year = Integer.parseInt(rawDate[0]);
                            int month = Integer.parseInt(rawDate[1]);
                            int day = Integer.parseInt(rawDate[2]);
                            
                            int hour = Integer.parseInt(rawTime[0]);
                            int minute = Integer.parseInt(rawTime[1]);
//                            int second = Integer.parseInt(rawTime[2]);
                            int second = 0; // TODO implement timepicker with seconds
                            
                            
                            setDateTime(LocalDateTime.of(year, month, day, hour, minute, second));
                            break;
                        }
                        case "Focal Length":
                        {
                            // 200 mm
                            String s = tag.getDescription();
                            String[] ss = s.split(" ");
                            float length = Float.parseFloat(ss[0]);
                            
                            setFocalLength(length);
                            break;
                        }
                        case "Image Width":
                        {
                            String s = tag.getDescription().replace(" pixels", "");
                            this.width = Integer.parseInt(s);
                            break;
                        }
                        case "Image Height":
                        {
                            String s = tag.getDescription().replace(" pixels", "");
                            this.height = Integer.parseInt(s);
                            break;
                        }
                        case "File Size":
                        {
                            String s = tag.getDescription().replace(" bytes", "");
                            this.size = Long.parseLong(s);
                            break;
                        }
                        case "Make":
                        {
                            String s = tag.getDescription();
                            setManufacturer(s);
                            break;
                        }
                        case "Model":
                        {
                            String s = tag.getDescription();
                            setModel(s);
                            break;
                        }
                    }
                }
            }
            
            this.setSizeDimensions(width, height, size);
        }
        catch (ImageProcessingException | IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    // ============================================================================================================================================ \\
    
    
    public boolean hasProperty(PhotoPropertyType ppt)
    {
        for (PhotoProperty property : new ArrayList<>(properties))
        {
            if (property.getType() == ppt)
            {
                return true;
            }
        }
        return false;
    }
    
    
    public void addProperty(PhotoProperty prop)
    {
        this.properties.add(prop);
    }
    
    
    private void removeProperty(PhotoPropertyType ppt)
    {
        for (PhotoProperty property : new ArrayList<>(properties))
        {
            if (property.getType() == ppt)
            {
                this.properties.remove(property);
            }
        }
    }
    
    
    public PhotoProperty getProperty(PhotoPropertyType ppt)
    {
        for (PhotoProperty property : new ArrayList<>(properties))
        {
            if (property.getType() == ppt)
            {
                return property;
            }
        }
        return null;
    }
    
    
    private void setDateTime(LocalDateTime date)
    {
        if (this.hasProperty(PhotoPropertyType.DATE_TIME))
        {
            this.removeProperty(PhotoPropertyType.DATE_TIME);
        }
        this.addProperty(new DateTimePhotoProperty(date));
    }
    
    
    private void setSizeDimensions(long width, long height, long sizeInBytes)
    {
        if (this.hasProperty(PhotoPropertyType.SIZE_DIMENSIONS))
        {
            this.removeProperty(PhotoPropertyType.SIZE_DIMENSIONS);
        }
        this.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(width, height, sizeInBytes)));
    }
    
    
    private void setManufacturer(String s)
    {
        if (this.hasProperty(PhotoPropertyType.MANUFACTURER))
        {
            this.removeProperty(PhotoPropertyType.MANUFACTURER);
        }
        this.addProperty(new ManufacturerPhotoProperty(s));
    }
    
    
    private void setModel(String s)
    {
        if (this.hasProperty(PhotoPropertyType.MODEL))
        {
            this.removeProperty(PhotoPropertyType.MODEL);
        }
        this.addProperty(new ModelPhotoProperty(s));
    }
    
    
    private void setISO(int n)
    {
        if (this.hasProperty(PhotoPropertyType.ISO))
        {
            this.removeProperty(PhotoPropertyType.ISO);
        }
        this.addProperty(new ISOPhotoProperty(n));
    }
    
    
    private void setAperture(float f) throws InvalidApertureException
    {
        if (this.hasProperty(PhotoPropertyType.APERTURE))
        {
            this.removeProperty(PhotoPropertyType.APERTURE);
        }
        this.addProperty(new AperturePhotoProperty(new Aperture(f)));
    }
    
    
    private void setShutterSpeed(int dividend, int divisor) throws InvalidShutterSpeedException
    {
        if (this.hasProperty(PhotoPropertyType.SHUTTER_SPEED))
        {
            this.removeProperty(PhotoPropertyType.SHUTTER_SPEED);
        }
        this.addProperty(new ShutterSpeedPhotoProperty(dividend, divisor));
    }
    
    
    private void setFocalLength(float mm)
    {
        if (this.hasProperty(PhotoPropertyType.FOCAL_LENGTH))
        {
            this.removeProperty(PhotoPropertyType.FOCAL_LENGTH);
        }
        this.addProperty(new FocalLengthPhotoProperty(mm));
    }
    
    
    // ============================================================================================================================================ \\
    
    
    public File getFile()
    {
        return file;
    }
    
    
    public Metadata getMetadata()
    {
        return metadata;
    }
    
    
    // ============================================================================================================================================ \\
}
