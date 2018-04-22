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
import com.michaelgatesdev.ExifExplorer.photo.Aperture;

public class AperturePhotoProperty extends PhotoProperty<Aperture>
{
    private static final Aperture MIN_APERTURE = new Aperture(1.0);
    private static final Aperture MAX_APERTURE = new Aperture(256.0F);
    
    
    public AperturePhotoProperty(Aperture value) throws InvalidApertureException
    {
        super(PhotoPropertyType.APERTURE, value);
        if (getValue().lessThan(MIN_APERTURE) || getValue().greaterThan(MAX_APERTURE))
        {
            throw new InvalidApertureException(String.format("Invalid aperture specified (%f). Must be between %f and %f!", value.getValue(), MIN_APERTURE.getValue(), MAX_APERTURE.getValue()));
        }
    }
    
    
    @Override
    public String toString()
    {
        return "F" + getValue();
    }
    
    
    @Override
    public String asString()
    {
        return this.toString();
    }
}
