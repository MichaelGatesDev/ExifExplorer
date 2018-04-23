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

public class SizeDimensions
{
    private long width;
    private long height;
    private long size;
    
    
    public SizeDimensions(long width, long height)
    {
        this.width = width;
        this.height = height;
    }
    
    
    public SizeDimensions(long size)
    {
        this.size = size;
    }
    
    
    public SizeDimensions(long width, long height, long size)
    {
        this.width = width;
        this.height = height;
        this.size = size;
    }
    
    
    public long getWidth()
    {
        return width;
    }
    
    
    public long getHeight()
    {
        return height;
    }
    
    
    public long getSize()
    {
        return size;
    }
    
    
    @Override
    public String toString()
    {
        return String.format("%d x %d %f MB", width, height, ((float) size / 1000.0));
    }
}
