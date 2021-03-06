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


package com.michaelgatesdev.ExifExplorer.photo.criteria.sizedimensions;

import com.michaelgatesdev.ExifExplorer.photo.SizeDimensions;

public class ExactSizeDimensionsCriteria extends SizeDimensionsCriteria
{
    public ExactSizeDimensionsCriteria(SizeDimensions sd)
    {
        super(SizeDimensionsCriteriaCondition.EQUAL_TO, new SizeDimensions[]{ sd });
    }
    
    
    @Override
    boolean compare(SizeDimensions d)
    {
        SizeDimensions sda = this.getObjects()[0];
        
        return (d.getWidth() == sda.getWidth()) &&
                (d.getHeight() == sda.getHeight()) &&
                (d.getSize() == sda.getHeight());
    }
}
