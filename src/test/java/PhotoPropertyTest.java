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
import com.michaelgatesdev.ExifExplorer.photo.properties.AperturePhotoProperty;
import org.junit.Test;

public class PhotoPropertyTest
{
    @Test(expected = InvalidApertureException.class)
    public void testInvalidApertureA() throws InvalidApertureException
    {
        Aperture aperture = new Aperture(0.9);
        new AperturePhotoProperty(aperture);
    }
    
    
    @Test(expected = InvalidApertureException.class)
    public void testInvalidApertureB() throws InvalidApertureException
    {
        Aperture aperture = new Aperture(256.1);
        new AperturePhotoProperty(aperture);
    }
    
    
    @Test
    public void testValidApertures() throws InvalidApertureException
    {
        new AperturePhotoProperty(new Aperture(256.0f));
        new AperturePhotoProperty(new Aperture(1.0f));
        new AperturePhotoProperty(new Aperture(2.8f));
        new AperturePhotoProperty(new Aperture(3.4f));
        new AperturePhotoProperty(new Aperture(4.0f));
        new AperturePhotoProperty(new Aperture(5.6f));
        new AperturePhotoProperty(new Aperture(11.0f));
    }
    
    
}
