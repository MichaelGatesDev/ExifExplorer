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


package com.michaelgatesdev.ExifExplorer.photo.properties;


public class ISOPhotoProperty extends PhotoProperty<Integer>
{
    //    private static final int MAX_ISO = 409600; // Sony A7S, could change later
    private int value;
    
    
    public ISOPhotoProperty(Integer value)
    {
//        if (value < 0 || value > MAX_ISO)
//        {
//            return;
//        }
//        this.value = value;
        super(value);
        this.value = value;
    }
    
    
    public Integer getValue()
    {
        return value;
    }
    
    
    @Override
    public String toString()
    {
        return "ISO-" + value;
    }
    
    
    @Override
    public String asString()
    {
        return this.toString();
    }
}
