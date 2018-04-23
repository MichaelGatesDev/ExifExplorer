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

import com.michaelgatesdev.ExifExplorer.exceptions.InvalidApertureException;
import com.michaelgatesdev.ExifExplorer.photo.Aperture;
import com.michaelgatesdev.ExifExplorer.photo.FilteredPhotosList;
import com.michaelgatesdev.ExifExplorer.photo.Photo;
import com.michaelgatesdev.ExifExplorer.photo.SizeDimensions;
import com.michaelgatesdev.ExifExplorer.photo.filters.Criteria;
import com.michaelgatesdev.ExifExplorer.photo.filters.datetime.BetweenDateTimeCriteria;
import com.michaelgatesdev.ExifExplorer.photo.filters.sizedimensions.BetweenSizeDimensionsCriteria;
import com.michaelgatesdev.ExifExplorer.photo.properties.AperturePhotoProperty;
import com.michaelgatesdev.ExifExplorer.photo.properties.DateTimePhotoProperty;
import com.michaelgatesdev.ExifExplorer.photo.properties.SizeDimensionsPhotoProperty;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhotoFilterTest
{
    @Test
    public void testFilteredPhotosList() throws InvalidApertureException
    {
        Photo photoA = new Photo();
        Photo photoB = new Photo();
        Photo photoC = new Photo();
        Photo photoD = new Photo();
        Photo photoE = new Photo();
        Photo photoF = new Photo();
        Photo photoG = new Photo();
        Photo photoH = new Photo();
        
        
        // Apertures
        photoA.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
        photoB.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
        photoC.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
        photoD.addProperty(new AperturePhotoProperty(new Aperture(5.6)));
        
        // Dates and Times
        photoA.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2018, 1, 1, 12, 30, 0))); // 01/01/2018 12:30:00
        photoB.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2018, 1, 1, 12, 30, 0))); // 01/01/2018 12:30:00
        photoC.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2012, 12, 31, 11, 59, 59))); // 12/31/2012 11:59:59
        photoD.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2012, 12, 31, 11, 59, 59))); // 12/31/2012 11:59:59
        
        // Sizes & Dimensions
        photoA.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
        photoB.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
        photoC.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
        photoD.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
        
        
        List<Photo> photos = Arrays.asList(photoA, photoB, photoC, photoD, photoE, photoF, photoG, photoH);
        Set<Criteria> betweenCriteria = Stream.of(
                new BetweenDateTimeCriteria(LocalDateTime.MIN, LocalDateTime.MAX),
                new BetweenSizeDimensionsCriteria(new SizeDimensions(0, 0, 0), new SizeDimensions(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE))
        ).collect(Collectors.toSet());
        
        FilteredPhotosList filteredPhotosList = new FilteredPhotosList(photos, betweenCriteria);
        
        Assert.assertEquals(filteredPhotosList.getResult().size(), 4);
        Assert.assertTrue(filteredPhotosList.getResult().contains(photoA));
        Assert.assertTrue(filteredPhotosList.getResult().contains(photoB));
    }
    
}
