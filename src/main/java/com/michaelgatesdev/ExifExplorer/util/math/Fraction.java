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

import com.michaelgatesdev.ExifExplorer.util.SimpleComparable;

public class Fraction implements SimpleComparable<Fraction>
{
    private int dividend;
    private int divisor;
    
    
    public Fraction(int dividend, int divisor)
    {
        this.dividend = dividend;
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
    
    
    public float getQuotient()
    {
        return (float) (dividend / divisor);
    }
    
    
    @Override
    public String toString()
    {
        return dividend + "/" + divisor;
    }
    
    
    @Override
    public boolean lessThan(Fraction f)
    {
        return (float) (dividend / divisor) < (float) (f.dividend / f.divisor);
    }
    
    
    @Override
    public boolean greaterThan(Fraction f)
    {
        return (float) (dividend / divisor) > (float) (f.dividend / f.divisor);
    }
    
    
    @Override
    public boolean equalTo(Fraction f)
    {
        return (dividend == f.dividend) && (divisor == f.divisor) || getQuotient() == f.getQuotient();
    }
}
