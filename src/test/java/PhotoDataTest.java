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

import java.util.regex.Pattern;

public class PhotoDataTest
{
    private static final Pattern ISO_FILENAME_PATTERN = Pattern.compile("ISO_(\\d+)_.+");
    private static final Pattern SS_FILENAME_PATTERN  = Pattern.compile("SS_(\\d+)-(\\d+)_.+");
    
//    private File testDirISO;
//    private File testDirSS;
//
//
//    @Before
//    public void init()
//    {
//        try
//        {
//            testDirISO = new File(Objects.requireNonNull(PhotoDataTest.class.getClassLoader().getResource("img/test/iso/")).toURI());
//            testDirSS = new File(Objects.requireNonNull(PhotoDataTest.class.getClassLoader().getResource("img/test/ss/")).toURI());
//        }
//        catch (URISyntaxException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//
//    @Test
//    public void testFoldersExist()
//    {
//        Assert.assertTrue(testDirISO.exists());
//        Assert.assertTrue(testDirSS.exists());
//    }
//
//
//    @Test
//    public void testISO()
//    {
//        for (File f : Objects.requireNonNull(testDirISO.listFiles()))
//        {
//            Photo photo = new Photo(f);
//            String name = f.getName();
//
//            if (!name.matches(ISO_FILENAME_PATTERN.pattern()))
//            {
//                continue;
//            }
//
//            Matcher m = ISO_FILENAME_PATTERN.matcher(name);
//            if (!m.find())
//            {
//                continue;
//            }
//
//            if (!photo.hasProperty(PhotoPropertyType.ISO))
//            {
//                continue;
//            }
//
//            ISOPhotoProperty prop = (ISOPhotoProperty) photo.getProperty(PhotoPropertyType.ISO);
//
//            String raw = m.group(1);
//            int expectedISO = Integer.parseInt(raw);
//            int actualISO = prop.getValue();
//
//            System.out.println("[" + name + "] Expected ISO = " + expectedISO + ", actual is " + actualISO);
//            Assert.assertEquals(expectedISO, actualISO);
//        }
//    }
//
//
//    @Test
//    public void testSS()
//    {
//        for (File f : Objects.requireNonNull(testDirSS.listFiles()))
//        {
//            Photo photo = new Photo(f);
//            String name = f.getName();
//
//            if (!name.matches(SS_FILENAME_PATTERN.pattern()))
//            {
//                continue;
//            }
//
//            Matcher m = SS_FILENAME_PATTERN.matcher(name);
//            if (!m.find())
//            {
//                continue;
//            }
//
//            if (!photo.hasProperty(PhotoPropertyType.SHUTTER_SPEED))
//            {
//                continue;
//            }
//
//
//            ShutterSpeedPhotoProperty prop = (ShutterSpeedPhotoProperty) photo.getProperty(PhotoPropertyType.SHUTTER_SPEED);
//
////            if (prop == null)
////            {
////                continue;
////            }
//
//            String rawDividend = m.group(1);
//            int expectedDividend = Integer.parseInt(rawDividend);
//            String rawDivisor = m.group(2);
//            int expectedDivisor = Integer.parseInt(rawDivisor);
//
//            ShutterSpeed speed = prop.getValue();
//            int actualDividend = speed.getDividend();
//            int actualDivisor = speed.getDivisor();
//
//            System.out.println("[" + name + "] Expected SS = " + expectedDividend + "/" + expectedDivisor + ", actual is " + actualDividend + "/" + actualDivisor);
//            Assert.assertEquals(new ShutterSpeed(expectedDividend, expectedDivisor), new ShutterSpeed(actualDividend, actualDivisor));
//        }
//    }

}
