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


package com.michaelgatesdev.ExifExplorer.photo.filters.sizedimensions;

import com.michaelgatesdev.ExifExplorer.photo.SizeDimensions;
import com.michaelgatesdev.ExifExplorer.photo.filters.Criteria;

public abstract class SizeDimensionsCriteria extends Criteria
{
    public enum SizeDimensionsCriteriaCondition
    {
        SMALLER_THAN,
        LARGER_THAN,
        EQUAL_TO,
        BETWEEN
    }
    
    private SizeDimensionsCriteriaCondition condition;
    private SizeDimensions[]                sizeDimensions;
    
    
    public SizeDimensionsCriteria(SizeDimensionsCriteriaCondition condition, SizeDimensions[] sizeDimensions)
    {
        this.condition = condition;
        this.sizeDimensions = sizeDimensions;
    }
    
    
    public SizeDimensions[] getSizeDimensions()
    {
        return sizeDimensions;
    }
    
    
    @Override
    public boolean compare(Object o)
    {
        if (o instanceof SizeDimensions)
        {
            return compare((SizeDimensions) o);
        }
        return false;
    }
    
    
    abstract boolean compare(SizeDimensions d);
}
