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


import com.michaelgatesdev.ExifExplorer.photo.filters.Criteria;
import com.michaelgatesdev.ExifExplorer.photo.properties.PhotoProperty;
import com.michaelgatesdev.ExifExplorer.photo.properties.PhotoPropertyType;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FilteredPhotosList
{
    private final static Logger logger = Logger.getLogger(FilteredPhotosList.class.getSimpleName());
    
    private List<Photo>   sourcePhotos;
    private Set<Criteria> criteria;
    
    private List<Photo> result;
    
    
    public FilteredPhotosList(List<Photo> sourcePhotos, Set<Criteria> criteria)
    {
        this.sourcePhotos = sourcePhotos;
        this.criteria = criteria;
        
        
        this.result = new ArrayList<>();
        
        for (Photo p : this.sourcePhotos)
        {
            boolean meetsReqs = true;
            for (Criteria c : this.criteria)
            {
                PhotoPropertyType ppt = c.getPropertyType();
                if (!p.hasProperty(ppt))
                {
                    logger.debug(String.format("%s does not have property %s", p.getFile().getName(), ppt.toString()));
                    meetsReqs = false;
                    break;
                }
                
                PhotoProperty pp = p.getProperty(c.getPropertyType());
                if (!c.compare(pp.getValue()))
                {
                    logger.debug(String.format("%s property %s has value of %s but does not meet requirements for %s", p.getFile().getName(), PhotoPropertyType.DATE_TIME.toString(), pp.getValue().toString(), c.getClass().getName()));
                    meetsReqs = false;
                    break;
                }
            }
            if (meetsReqs)
            {
                result.add(p);
                logger.debug(String.format("%s meets all of the the criteria!", p.getFile().getName()));
            }
        }
    }
    
    
    public List<Photo> getResult()
    {
        return result;
    }
}
