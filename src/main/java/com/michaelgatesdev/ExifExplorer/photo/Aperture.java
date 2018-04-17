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

import com.michaelgatesdev.ExifExplorer.util.SimpleComparable;
import com.michaelgatesdev.ExifExplorer.util.math.MathUtil;

public class Aperture implements SimpleComparable<Aperture>
{
    private double value;
    
    
    public Aperture(double amt)
    {
        this.value = MathUtil.roundPrecise(amt, 1);
    }
    
    
    public double getValue()
    {
        return value;
    }
    
    
    @Override
    public boolean lessThan(Aperture o)
    {
        return value < o.getValue();
    }
    
    
    @Override
    public boolean greaterThan(Aperture o)
    {
        return value > o.getValue();
    }
    
    
    @Override
    public boolean equalTo(Aperture o)
    {
        return value == o.getValue();
    }
}
