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


package com.michaelgatesdev.ExifExplorer.photo.criteria.datetime;

import com.michaelgatesdev.ExifExplorer.photo.criteria.Criteria;
import com.michaelgatesdev.ExifExplorer.photo.properties.PhotoPropertyType;

import java.time.LocalDateTime;

public abstract class DateTimeCriteria extends Criteria
{
    public enum DateTimeCriteriaCondition
    {
        BEFORE,
        AFTER,
        BETWEEN,
        ON
    }
    
    private DateTimeCriteriaCondition condition;
    private LocalDateTime[]           dateTimes;
    
    
    public DateTimeCriteria(DateTimeCriteriaCondition condition, LocalDateTime[] dates)
    {
        this.condition = condition;
        this.dateTimes = dates;
    }
    
    
    @Override
    public boolean compare(Object o)
    {
        if (!(o instanceof LocalDateTime))
        {
            return false;
        }
        return this.compare((LocalDateTime) o);
    }
    
    
    abstract boolean compare(LocalDateTime dtc);
    
    
    @Override
    public PhotoPropertyType getPropertyType()
    {
        return PhotoPropertyType.DATE_TIME;
    }
    
    
    @Override
    public LocalDateTime[] getObjects()
    {
        return dateTimes;
    }
}
