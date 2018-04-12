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
import com.michaelgatesdev.ExifExplorer.photo.properties.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Photo
{
    // ============================================================================================================================================ \\
    
    private File                                  file;
    private Metadata                              metadata;
    private Map<PhotoPropertyType, PhotoProperty> properties;
    
    // ============================================================================================================================================ \\
    
    
    public Photo(File f)
    {
        this.file = f;
        this.properties = new HashMap<>();
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
                        case "Shutter Speed Value":
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
                            int second = Integer.parseInt(rawTime[2]);
                            
                            
                            setDateTime(LocalDateTime.of(year, month, day, hour, minute, second));
                            break;
                        }
                        case "Focal Length":
                        {
                            // 200 mm
                            String s = tag.getDescription();
                            String[] ss = s.split(" ");
                            int length = Integer.parseInt(ss[0]);
                            
                            setFocalLength(length);
                            break;
                        }
                    }
                }
            }
        }
        catch (ImageProcessingException | IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
    // ============================================================================================================================================ \\
    
    
    private void setDateTime(LocalDateTime date)
    {
        this.properties.remove(PhotoPropertyType.DATE_TIME);
        this.properties.put(PhotoPropertyType.DATE_TIME, new DateTimePhotoProperty(date));
    }
    
    
    private void setDimensions(int width, int height)
    {
        this.properties.remove(PhotoPropertyType.DIMENSIONS);
        this.properties.put(PhotoPropertyType.DIMENSIONS, new DimensionsPhotoProperty(width, height));
    }
    
    
    private void setSize(long sizeInBytes)
    {
        this.properties.remove(PhotoPropertyType.SIZE);
        this.properties.put(PhotoPropertyType.SIZE, new SizePhotoProperty(sizeInBytes));
    }
    
    
    private void setManufacturer(String s)
    {
        this.properties.remove(PhotoPropertyType.MANUFACTURER);
        this.properties.put(PhotoPropertyType.MANUFACTURER, new ManufacturerPhotoProperty(s));
    }
    
    
    private void setModel(String s)
    {
        this.properties.remove(PhotoPropertyType.MODEL);
        this.properties.put(PhotoPropertyType.MODEL, new ModelPhotoProperty(s));
    }
    
    
    private void setISO(int n)
    {
        this.properties.remove(PhotoPropertyType.ISO);
        this.properties.put(PhotoPropertyType.ISO, new ISOPhotoProperty(n));
    }
    
    
    private void setAperture(float f)
    {
        this.properties.remove(PhotoPropertyType.APERTURE);
        this.properties.put(PhotoPropertyType.APERTURE, new AperturePhotoProperty(f));
    }
    
    
    private void setShutterSpeed(int dividend, int divisor)
    {
        this.properties.remove(PhotoPropertyType.SHUTTER_SPEED);
        this.properties.put(PhotoPropertyType.SHUTTER_SPEED, new ShutterSpeedPhotoProperty(dividend, divisor));
    }
    
    
    private void setFocalLength(int mm)
    {
        this.properties.remove(PhotoPropertyType.FOCAL_LENGTH);
        this.properties.put(PhotoPropertyType.FOCAL_LENGTH, new FocalLengthPhotoProperty(mm));
    }
    
    
    // ============================================================================================================================================ \\
    
    
    public boolean hasProperty(PhotoPropertyType prop)
    {
        return properties.containsKey(prop);
    }
    
    
    public PhotoProperty getProperty(PhotoPropertyType type)
    {
        return properties.get(type);
    }
    
    
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
