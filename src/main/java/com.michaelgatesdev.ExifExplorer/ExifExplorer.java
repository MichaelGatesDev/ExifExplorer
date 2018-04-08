/*
 * Copyright (C) Michael Gates (MichaelGatesDev@gmail.com) 2015
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


package com.michaelgatesdev.ExifExplorer;

import org.apache.log4j.Logger;

import java.io.File;

public class ExifExplorer implements Application
{
    private final static Logger logger = Logger.getLogger(ExifExplorer.class.getSimpleName());
    
    private static ExifExplorer instance;
    
    private File rootDir;
    private File defaultExportsDir;
    
    
    @Override
    public void onEnable()
    {
        instance = this;
        
        logger.info("Starting ExifExplorer...");
        
        
    }
    
    
    @Override
    public void onDisable()
    {
        instance = null;
    }
    
    
}
