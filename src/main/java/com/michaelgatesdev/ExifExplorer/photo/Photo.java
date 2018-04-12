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
//        System.out.println("Put in SS " + dividend + "/" + divisor);
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
