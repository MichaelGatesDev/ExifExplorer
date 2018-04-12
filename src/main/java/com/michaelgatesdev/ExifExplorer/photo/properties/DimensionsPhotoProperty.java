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

public class DimensionsPhotoProperty extends PhotoProperty<Integer[]>
{
    private int width;
    private int height;
    
    
    public DimensionsPhotoProperty(int width, int height)
    {
        super(new Integer[]{ width, height });
        this.width = width;
        this.height = height;
    }
    
    
    @Override
    public String asString()
    {
        return this.toString();
    }
    
    
    @Override
    public String toString()
    {
        return width + " x " + height;
    }
}