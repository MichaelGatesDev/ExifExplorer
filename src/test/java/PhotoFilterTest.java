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
import com.michaelgatesdev.ExifExplorer.photo.criteria.Criteria;
import com.michaelgatesdev.ExifExplorer.photo.criteria.aperture.BetweenApertureCriteria;
import com.michaelgatesdev.ExifExplorer.photo.criteria.aperture.ExactApertureCriteria;
import com.michaelgatesdev.ExifExplorer.photo.criteria.aperture.LargerThanApertureCriteria;
import com.michaelgatesdev.ExifExplorer.photo.criteria.aperture.SmallerThanApertureCriteria;
import com.michaelgatesdev.ExifExplorer.photo.properties.AperturePhotoProperty;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PhotoFilterTest
{
//    @Test
//    public void testFilteredPhotosList() throws InvalidApertureException
//    {
//        Photo photoA = new Photo();
//        Photo photoB = new Photo();
//        Photo photoC = new Photo();
//        Photo photoD = new Photo();
//        Photo photoE = new Photo();
//        Photo photoF = new Photo();
//        Photo photoG = new Photo();
//        Photo photoH = new Photo();
//
//
//        // Apertures
//        photoA.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
//        photoB.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
//        photoC.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
//        photoD.addProperty(new AperturePhotoProperty(new Aperture(5.6)));
//
//        // Dates and Times
//        photoA.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2018, 1, 1, 12, 30, 0))); // 01/01/2018 12:30:00
//        photoB.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2018, 1, 1, 12, 30, 0))); // 01/01/2018 12:30:00
//        photoC.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2012, 12, 31, 11, 59, 59))); // 12/31/2012 11:59:59
//        photoD.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2012, 12, 31, 11, 59, 59))); // 12/31/2012 11:59:59
//
//        // Sizes & Dimensions
//        photoA.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
//        photoB.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
//        photoC.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
//        photoD.addProperty(new SizeDimensionsPhotoProperty(new SizeDimensions(100, 100, 35000)));
//
//
//        List<Photo> photos = Arrays.asList(photoA, photoB, photoC, photoD, photoE, photoF, photoG, photoH);
//        Set<Criteria> betweenCriteria = Stream.of(
//                new BetweenDateTimeCriteria(LocalDateTime.MIN, LocalDateTime.MAX)
//        ).collect(Collectors.toSet());
//
//        FilteredPhotosList filteredPhotosList = new FilteredPhotosList(photos, betweenCriteria);
//
//        Assert.assertEquals(filteredPhotosList.getResult().size(), 4);
//        Assert.assertTrue(filteredPhotosList.getResult().contains(photoA));
//        Assert.assertTrue(filteredPhotosList.getResult().contains(photoB));
//    }
//
//
//    @Test
//    public void testMissingProperties() throws InvalidApertureException
//    {
//        Photo photoA = new Photo();
//        Photo photoB = new Photo();
//        Photo photoC = new Photo();
//        Photo photoD = new Photo();
//
//
//        // Apertures
//        photoA.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
////        photoB.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
//        photoC.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
////        photoD.addProperty(new AperturePhotoProperty(new Aperture(5.6)));
//
//        List<Photo> photos = Arrays.asList(photoA, photoB, photoC, photoD);
//        Set<Criteria> betweenCriteria = Stream.of(
//                new BetweenDateTimeCriteria(LocalDateTime.MIN, LocalDateTime.MAX),
//                new BetweenSizeDimensionsCriteria(new SizeDimensions(0, 0, 0), new SizeDimensions(Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE))
//        ).collect(Collectors.toSet());
//
//        FilteredPhotosList filteredPhotosList = new FilteredPhotosList(photos, betweenCriteria);
//
//        Assert.assertEquals(filteredPhotosList.getResult().size(), 4);
//        Assert.assertTrue(filteredPhotosList.getResult().contains(photoA));
//        Assert.assertTrue(filteredPhotosList.getResult().contains(photoB));
//    }
    
    
    @Test
    public void testApertureFilters() throws InvalidApertureException
    {
        Photo photoA = new Photo();
        photoA.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
        Photo photoB = new Photo();
        photoB.addProperty(new AperturePhotoProperty(new Aperture(2.8)));
        Photo photoC = new Photo();
        photoC.addProperty(new AperturePhotoProperty(new Aperture(5.6)));
        Photo photoD = new Photo();
        photoD.addProperty(new AperturePhotoProperty(new Aperture(11.0)));
        
        List<Photo> photos = Arrays.asList(photoA, photoB, photoC, photoD);
        
        
        // Exact aperture
        Set<Criteria> exactCriteria = Stream.of(
                new ExactApertureCriteria(new Aperture[]{ new Aperture(2.8) })
        ).collect(Collectors.toSet());
        FilteredPhotosList fplExact = new FilteredPhotosList(photos, exactCriteria);
        
        Assert.assertEquals(fplExact.getResult().size(), 2);
        Assert.assertTrue(fplExact.getResult().contains(photoA));
        Assert.assertTrue(fplExact.getResult().contains(photoB));
        Assert.assertFalse(fplExact.getResult().contains(photoC));
        Assert.assertFalse(fplExact.getResult().contains(photoD));
        
        
        // Aperture is less than X
        Set<Criteria> smallerThanCriteria = Stream.of(
                new SmallerThanApertureCriteria(new Aperture[]{ new Aperture(8.0) })
        ).collect(Collectors.toSet());
        FilteredPhotosList fplSmallerThan = new FilteredPhotosList(photos, smallerThanCriteria);
        
        Assert.assertEquals(fplSmallerThan.getResult().size(), 3);
        Assert.assertTrue(fplSmallerThan.getResult().contains(photoA));
        Assert.assertTrue(fplSmallerThan.getResult().contains(photoB));
        Assert.assertTrue(fplSmallerThan.getResult().contains(photoC));
        Assert.assertFalse(fplSmallerThan.getResult().contains(photoD));
        
        
        // Aperture is greater than X
        Set<Criteria> greaterThanCriteria = Stream.of(
                new LargerThanApertureCriteria(new Aperture[]{ new Aperture(2.8) })
        ).collect(Collectors.toSet());
        FilteredPhotosList fplGreaterThan = new FilteredPhotosList(photos, greaterThanCriteria);
        
        Assert.assertEquals(fplGreaterThan.getResult().size(), 2);
        Assert.assertTrue(fplGreaterThan.getResult().contains(photoC));
        Assert.assertTrue(fplGreaterThan.getResult().contains(photoD));
        Assert.assertFalse(fplGreaterThan.getResult().contains(photoA));
        Assert.assertFalse(fplGreaterThan.getResult().contains(photoB));
        
        
        // Aperture is between X and Y
        Set<Criteria> betweenCriteria = Stream.of(
                new BetweenApertureCriteria(new Aperture[]{ new Aperture(1.8), new Aperture(8.0) })
        ).collect(Collectors.toSet());
        FilteredPhotosList fplBetween = new FilteredPhotosList(photos, betweenCriteria);
        
        Assert.assertEquals(fplBetween.getResult().size(), 3);
        Assert.assertTrue(fplBetween.getResult().contains(photoA));
        Assert.assertTrue(fplBetween.getResult().contains(photoB));
        Assert.assertTrue(fplBetween.getResult().contains(photoC));
        Assert.assertFalse(fplBetween.getResult().contains(photoD));
        
        
    }
}
