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
import com.michaelgatesdev.ExifExplorer.photo.criteria.datetime.AfterDateTimeCriteria;
import com.michaelgatesdev.ExifExplorer.photo.criteria.datetime.BeforeDateTimeCriteria;
import com.michaelgatesdev.ExifExplorer.photo.criteria.datetime.BetweenDateTimeCriteria;
import com.michaelgatesdev.ExifExplorer.photo.criteria.datetime.ExactDateTimeCriteria;
import com.michaelgatesdev.ExifExplorer.photo.properties.AperturePhotoProperty;
import com.michaelgatesdev.ExifExplorer.photo.properties.DateTimePhotoProperty;
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
                new ExactApertureCriteria(new Aperture(2.8))
        ).collect(Collectors.toSet());
        FilteredPhotosList fplExact = new FilteredPhotosList(photos, exactCriteria);
        
        Assert.assertEquals(fplExact.getResult().size(), 2);
        Assert.assertTrue(fplExact.getResult().contains(photoA));
        Assert.assertTrue(fplExact.getResult().contains(photoB));
        Assert.assertFalse(fplExact.getResult().contains(photoC));
        Assert.assertFalse(fplExact.getResult().contains(photoD));
        
        
        // Aperture is less than X
        Set<Criteria> smallerThanCriteria = Stream.of(
                new SmallerThanApertureCriteria(new Aperture(8.0))
        ).collect(Collectors.toSet());
        FilteredPhotosList fplSmallerThan = new FilteredPhotosList(photos, smallerThanCriteria);
        
        Assert.assertEquals(fplSmallerThan.getResult().size(), 3);
        Assert.assertTrue(fplSmallerThan.getResult().contains(photoA));
        Assert.assertTrue(fplSmallerThan.getResult().contains(photoB));
        Assert.assertTrue(fplSmallerThan.getResult().contains(photoC));
        Assert.assertFalse(fplSmallerThan.getResult().contains(photoD));
        
        
        // Aperture is greater than X
        Set<Criteria> greaterThanCriteria = Stream.of(
                new LargerThanApertureCriteria(new Aperture(2.8))
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
    
    
    @Test
    public void testDateTimeFilters()
    {
        Photo photoA = new Photo();
        photoA.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2018, 4, 23, 12, 0)));
        Photo photoB = new Photo();
        photoB.addProperty(new DateTimePhotoProperty(LocalDateTime.of(2018, 4, 23, 12, 0)));
        Photo photoC = new Photo();
        photoC.addProperty(new DateTimePhotoProperty(LocalDateTime.of(1999, 11, 15, 1, 45)));
        Photo photoD = new Photo();
        photoD.addProperty(new DateTimePhotoProperty(LocalDateTime.of(1999, 11, 15, 1, 45)));
        
        List<Photo> photos = Arrays.asList(photoA, photoB, photoC, photoD);
        
        
        // Exact date time
        Set<Criteria> exactCriteria = Stream.of(
                new ExactDateTimeCriteria(LocalDateTime.of(2018, 4, 23, 12, 0))
        ).collect(Collectors.toSet());
        FilteredPhotosList fplExact = new FilteredPhotosList(photos, exactCriteria);
        
        Assert.assertEquals(fplExact.getResult().size(), 2);
        Assert.assertTrue(fplExact.getResult().contains(photoA));
        Assert.assertTrue(fplExact.getResult().contains(photoB));
        Assert.assertFalse(fplExact.getResult().contains(photoC));
        Assert.assertFalse(fplExact.getResult().contains(photoD));
        
        
        // Date is before X
        Set<Criteria> beforeCriteria = Stream.of(
                new BeforeDateTimeCriteria(LocalDateTime.of(2020, 5, 5, 12, 30))
        ).collect(Collectors.toSet());
        FilteredPhotosList fplBefore = new FilteredPhotosList(photos, beforeCriteria);
        
        Assert.assertEquals(fplBefore.getResult().size(), 4);
        Assert.assertTrue(fplBefore.getResult().contains(photoA));
        Assert.assertTrue(fplBefore.getResult().contains(photoB));
        Assert.assertTrue(fplBefore.getResult().contains(photoC));
        Assert.assertTrue(fplBefore.getResult().contains(photoD));
        
        
        // Date is after X
        Set<Criteria> afterCriteria = Stream.of(
                new AfterDateTimeCriteria(LocalDateTime.of(2008, 8, 11, 12, 0))
        ).collect(Collectors.toSet());
        FilteredPhotosList fplGreaterThan = new FilteredPhotosList(photos, afterCriteria);
        
        Assert.assertEquals(fplGreaterThan.getResult().size(), 2);
        Assert.assertTrue(fplGreaterThan.getResult().contains(photoA));
        Assert.assertTrue(fplGreaterThan.getResult().contains(photoB));
        Assert.assertFalse(fplGreaterThan.getResult().contains(photoC));
        Assert.assertFalse(fplGreaterThan.getResult().contains(photoD));
        
        
        // Date is between X and Y
        Set<Criteria> betweenCriteria = Stream.of(
                new BetweenDateTimeCriteria(LocalDateTime.of(1999, 1, 1, 12, 0), LocalDateTime.of(2020, 1, 1, 12, 0))
        ).collect(Collectors.toSet());
        FilteredPhotosList fplBetween = new FilteredPhotosList(photos, betweenCriteria);
        
        Assert.assertEquals(fplBetween.getResult().size(), 4);
        Assert.assertTrue(fplBetween.getResult().contains(photoA));
        Assert.assertTrue(fplBetween.getResult().contains(photoB));
        Assert.assertTrue(fplBetween.getResult().contains(photoC));
        Assert.assertTrue(fplBetween.getResult().contains(photoD));
    }
    
    
}
