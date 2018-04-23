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

import java.time.LocalDateTime;

public class AfterDateTimeCriteria extends DateTimeCriteria
{
    public AfterDateTimeCriteria(LocalDateTime ldt)
    {
        super(DateTimeCriteriaCondition.AFTER, new LocalDateTime[]{ ldt });
    }
    
    
    @Override
    public boolean compare(LocalDateTime ldt)
    {
        return ldt.isAfter(this.getObjects()[0]);
    }
}
