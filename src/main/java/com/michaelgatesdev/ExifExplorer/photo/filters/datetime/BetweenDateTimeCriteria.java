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


package com.michaelgatesdev.ExifExplorer.photo.filters.datetime;

import java.time.LocalDateTime;

public class BetweenDateTimeCriteria extends DateTimeCriteria
{
    public BetweenDateTimeCriteria(LocalDateTime earliest, LocalDateTime latest)
    {
        super(DateTimeCriteriaCondition.BETWEEN, new LocalDateTime[]{ earliest, latest });
    }
    
    
    @Override
    public boolean compare(LocalDateTime ldt)
    {
        return ldt.isAfter(this.getDates()[0]) && ldt.isBefore(this.getDates()[1]);
    }
}