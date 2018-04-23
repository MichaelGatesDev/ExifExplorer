/*
 * Copyright (C) Michael Gates (MichaelGatesDev@gmail.com) 2015
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


package com.michaelgatesdev.ExifExplorer.photo.criteria.aperture;

import com.michaelgatesdev.ExifExplorer.photo.Aperture;
import com.michaelgatesdev.ExifExplorer.photo.criteria.Criteria;
import com.michaelgatesdev.ExifExplorer.photo.properties.PhotoPropertyType;

public abstract class ApertureCriteria extends Criteria
{
    public enum ApertureCriteriaCondition
    {
        SMALLER_THAN,
        LARGER_THAN,
        EQUAL_TO,
        BETWEEN
    }
    
    private ApertureCriteriaCondition condition;
    private Aperture[]                apertures;
    
    
    public ApertureCriteria(ApertureCriteriaCondition condition, Aperture[] apertures)
    {
        this.condition = condition;
        this.apertures = apertures;
    }
    
    
    public Aperture[] getApertures()
    {
        return apertures;
    }
    
    
    @Override
    public boolean compare(Object o)
    {
        if (!(o instanceof Aperture))
        {
            return false;
        }
        return this.compare((Aperture) o);
    }
    
    
    abstract boolean compare(Aperture dtc);
    
    
    @Override
    public PhotoPropertyType getPropertyType()
    {
        return PhotoPropertyType.APERTURE;
    }
}
