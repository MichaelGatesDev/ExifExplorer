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


package com.michaelgatesdev.ExifExplorer.exceptions;

import com.michaelgatesdev.ExifExplorer.photo.properties.InvalidShutterSpeedException;
import com.michaelgatesdev.ExifExplorer.photo.properties.PhotoProperty;
import com.michaelgatesdev.ExifExplorer.util.math.Fraction;

public class ShutterSpeedPhotoProperty extends PhotoProperty<Fraction>
{
    private static final Fraction MIN_SPEED = new Fraction(1, 16000); // The fastest speed available in APS-H or APS-C format DSLR cameras (as of 2012).
    private static final Fraction MAX_SPEED = new Fraction(60, 1); // The fastest speed available in APS-H or APS-C format DSLR cameras (as of 2012).
    private              Fraction value;
    
    
    public ShutterSpeedPhotoProperty(Fraction fraction) throws InvalidShutterSpeedException
    {
        if (fraction.lessThan(MIN_SPEED))
        {
            throw new InvalidShutterSpeedException("Shutter speed " + fraction.getDividend() + "/" + fraction.getDivisor() + " can not be less than " + MIN_SPEED.getDividend() + "/" + MIN_SPEED.getDivisor());
        }
        if (fraction.greaterThan(MAX_SPEED))
        {
            throw new InvalidShutterSpeedException("Shutter speed " + fraction.getDividend() + "/" + fraction.getDivisor() + " can not be greater than " + MIN_SPEED.getDividend() + "/" + MIN_SPEED.getDivisor());
        }
        this.value = fraction;
    }
    
    
    @Override
    public Fraction getValue()
    {
        return value;
    }
    
    
    @Override
    public String toString()
    {
        return value.toString();
    }
}
