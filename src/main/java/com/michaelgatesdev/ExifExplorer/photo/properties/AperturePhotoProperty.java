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

import com.michaelgatesdev.ExifExplorer.exceptions.InvalidApertureException;

public class AperturePhotoProperty extends PhotoProperty<Float>
{
    private static final float MIN_APERTURE = 1.0F;
    private static final float MAX_APERTURE = 256.0F;
    private              float value;
    
    
    public AperturePhotoProperty(float value) throws InvalidApertureException
    {
        if (value < MIN_APERTURE || value > MAX_APERTURE)
        {
            throw new InvalidApertureException();
        }
        this.value = value;
    }
    
    
    @Override
    public Float getValue()
    {
        return value;
    }
    
    
    @Override
    public String toString()
    {
        return "F" + value;
    }
}
