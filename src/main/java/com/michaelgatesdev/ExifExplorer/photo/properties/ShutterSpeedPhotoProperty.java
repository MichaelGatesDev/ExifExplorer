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

import com.michaelgatesdev.ExifExplorer.exceptions.InvalidShutterSpeedException;
import com.michaelgatesdev.ExifExplorer.photo.ShutterSpeed;

public class ShutterSpeedPhotoProperty extends PhotoProperty<ShutterSpeed>
{
    private static final ShutterSpeed MIN_SS = new ShutterSpeed(0, 1); // Sony A7S, could change later
    private static final ShutterSpeed MAX_SS = new ShutterSpeed(1, 8000); // Sony A7S, could change later
    
    
    public ShutterSpeedPhotoProperty(int dividend, int divisor) throws InvalidShutterSpeedException
    {
        super(PhotoPropertyType.SHUTTER_SPEED, new ShutterSpeed(dividend, divisor));
        if (getValue().lessThan(MIN_SS) || getValue().greaterThan(MAX_SS))
        {
            throw new InvalidShutterSpeedException(String.format("Invalid shutter speed specified (%d/%d). Must be between %d/%d and %d/%d!", dividend, divisor, MIN_SS.getDividend(), MIN_SS.getDivisor(), MAX_SS.getDividend(), MAX_SS.getDivisor()));
        }
    }
    
    
    @Override
    public String asString()
    {
        return toString();
    }
    
    
    @Override
    public String toString()
    {
        return this.getValue().getDividend() + "/" + this.getValue().getDivisor();
    }
}
