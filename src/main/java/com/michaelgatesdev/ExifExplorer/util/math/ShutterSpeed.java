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


package com.michaelgatesdev.ExifExplorer.util.math;

public class ShutterSpeed extends Fraction
{
    // http://i0.wp.com/c710720.r20.cf2.rackcdn.com/wp-content/uploads/2011/08/ISO-Shutter-Speeds-Fstops-Copyright-2009-2011-photographyuncapped.gif
//    private static final ShutterSpeed[] FULL_STOPS  = new ShutterSpeed[]{
//            new ShutterSpeed(1, 8000),
//            new ShutterSpeed(1, 4000),
//            new ShutterSpeed(1, 2000),
//            new ShutterSpeed(1, 1000),
//            new ShutterSpeed(1, 500),
//            new ShutterSpeed(1, 250),
//            new ShutterSpeed(1, 125),
//            new ShutterSpeed(1, 60),
//            new ShutterSpeed(1, 30),
//            new ShutterSpeed(1, 15),
//            new ShutterSpeed(1, 8),
//            new ShutterSpeed(1, 4),
//            new ShutterSpeed(1, 2),
//            new ShutterSpeed(1, 1),
//            new ShutterSpeed(2, 1),
//            new ShutterSpeed(4, 1),
//            new ShutterSpeed(8, 1),
//            new ShutterSpeed(15, 1),
//            new ShutterSpeed(30, 1),
//            new ShutterSpeed(60, 1),
//    };
//    private static final ShutterSpeed[] HALF_STOPS  = new ShutterSpeed[]{
//            new ShutterSpeed(1, 8000),
//            new ShutterSpeed(1, 6000),
//            new ShutterSpeed(1, 4000),
//            new ShutterSpeed(1, 3000),
//            new ShutterSpeed(1, 2000),
//            new ShutterSpeed(1, 1500),
//            new ShutterSpeed(1, 1000),
//            new ShutterSpeed(1, 750),
//            new ShutterSpeed(1, 500),
//            new ShutterSpeed(1, 350),
//            new ShutterSpeed(1, 250),
//            new ShutterSpeed(1, 180),
//            new ShutterSpeed(1, 125),
//            new ShutterSpeed(1, 90),
//            new ShutterSpeed(2, 60),
//            new ShutterSpeed(4, 45),
//            new ShutterSpeed(8, 30),
//            new ShutterSpeed(1, 20),
//            new ShutterSpeed(1, 15),
//            new ShutterSpeed(1, 10),
//            new ShutterSpeed(1, 8),
//            new ShutterSpeed(1, 6),
//            new ShutterSpeed(1, 4),
////            new ShutterSpeed(1, 0.3f),
////            new ShutterSpeed(1, 0.5f),
////            new ShutterSpeed(1, 0.7f),
//            new ShutterSpeed(1, 1),
////            new ShutterSpeed(1, 1.5f),
//            new ShutterSpeed(1, 2),
//            new ShutterSpeed(1, 3),
//            new ShutterSpeed(1, 4),
//    };
//    private static final ShutterSpeed[] THIRD_STOPS = new ShutterSpeed[]{
//            new ShutterSpeed(1, 8000),
//            new ShutterSpeed(1, 6400),
//            new ShutterSpeed(1, 5000),
//            new ShutterSpeed(1, 4000),
//            new ShutterSpeed(1, 3200),
//            new ShutterSpeed(1, 2500),
//            new ShutterSpeed(1, 2000),
//            new ShutterSpeed(1, 1600),
//            new ShutterSpeed(1, 1000),
//            new ShutterSpeed(1, 800),
//            new ShutterSpeed(1, 640),
//            new ShutterSpeed(1, 500),
//            new ShutterSpeed(1, 400),
//            new ShutterSpeed(1, 320),
//            new ShutterSpeed(1, 250),
//            new ShutterSpeed(1, 200),
//            new ShutterSpeed(1, 160),
//            new ShutterSpeed(1, 125),
//            new ShutterSpeed(1, 100),
//            new ShutterSpeed(1, 80),
//            new ShutterSpeed(1, 60),
//            new ShutterSpeed(1, 50),
//            new ShutterSpeed(1, 40),
//            new ShutterSpeed(1, 30),
//            new ShutterSpeed(1, 25),
//            new ShutterSpeed(1, 20),
//            new ShutterSpeed(1, 15),
//            new ShutterSpeed(1, 13),
//            new ShutterSpeed(1, 10),
//            new ShutterSpeed(1, 8),
//            new ShutterSpeed(1, 6),
//            new ShutterSpeed(1, 5),
//            new ShutterSpeed(1, 4),
////            new ShutterSpeed(1, 0.3f),
////            new ShutterSpeed(1, 0.4f),
////            new ShutterSpeed(1, 0.5f),
////            new ShutterSpeed(1, 0.6f),
//    };
    
    
    public ShutterSpeed(int dividend, int divisor)
    {
        super(dividend, divisor);
//        this.roundToNearest();
    }


//    private void roundToNearest()
//    {
//        ShutterSpeed nearest = this.getNearest(this);
//        if (nearest != null)
//        {
//            this.setDividend(nearest.getDividend());
//            this.setDivisor(nearest.getDivisor());
//        }
//    }
//
//
//    private ShutterSpeed getNearest(ShutterSpeed s)
//    {
//        ShutterSpeed nearest = null;
//        float shortestDistance = -1;
//
//        float myQuotient = s.getQuotient();
//        for (ShutterSpeed f : FULL_STOPS)
//        {
//            float otherResult = f.getQuotient();
//            float calcDist = Math.abs(myQuotient - otherResult);
//
//            if (shortestDistance == -1 || calcDist < shortestDistance)
//            {
//                shortestDistance = calcDist;
//                nearest = f;
//            }
//        }
//        for (ShutterSpeed f : HALF_STOPS)
//        {
//            float otherResult = f.getQuotient();
//            float calcDist = Math.abs(myQuotient - otherResult);
//
//            if (shortestDistance == -1 || calcDist < shortestDistance)
//            {
//                shortestDistance = calcDist;
//                nearest = f;
//            }
//        }
//        for (ShutterSpeed f : THIRD_STOPS)
//        {
//            float otherResult = f.getQuotient();
//            float calcDist = Math.abs(myQuotient - otherResult);
//
//            if (shortestDistance == -1 || calcDist < shortestDistance)
//            {
//                shortestDistance = calcDist;
//                nearest = f;
//            }
//        }
//        return nearest;
//    }
    
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof ShutterSpeed)
        {
            ShutterSpeed ss = (ShutterSpeed) obj;
            return ss.getDividend() == this.getDividend() && ss.getDivisor() == this.getDivisor();
        }
        return super.equals(obj);
    }
}
