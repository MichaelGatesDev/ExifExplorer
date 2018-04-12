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

public class Fraction
{
    private int dividend;
    private int divisor;
    
    
    public Fraction(int dividend, int divisor)
    {
        this.dividend = dividend;
        this.divisor = divisor;
    }
    
    
    public void setDividend(int dividend)
    {
        this.dividend = dividend;
    }
    
    
    public void setDivisor(int divisor)
    {
        this.divisor = divisor;
    }
    
    
    public int getDividend()
    {
        return dividend;
    }
    
    
    public int getDivisor()
    {
        return divisor;
    }
    
    
    public boolean lessThan(Fraction minSpeed)
    {
        return (float) (dividend / divisor) < (float) (minSpeed.dividend / minSpeed.divisor);
    }
    
    
    public boolean greaterThan(Fraction maxSpeed)
    {
        return (float) (dividend / divisor) > (float) (maxSpeed.dividend / maxSpeed.divisor);
    }
    
    
    float getQuotient()
    {
        return (float) (dividend / divisor);
    }
    
    
    @Override
    public String toString()
    {
        return dividend + "/" + divisor;
    }
}
