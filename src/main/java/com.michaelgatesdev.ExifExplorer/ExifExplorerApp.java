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


package com.michaelgatesdev.ExifExplorer;

public class ExifExplorerApp
{
    private final Application application;
    
    
    /**
     * The entrypoint to the binary
     *
     * @param args any arguments specified by the command line
     */
    public static void main(String[] args)
    {
        new ExifExplorerApp();
    }
    
    
    private ExifExplorerApp()
    {
        application = new ExifExplorer(); // instantiate application
        application.onEnable(); // call start function
        
        Runtime.getRuntime().addShutdownHook(new Thread(application::onDisable)); // when program shuts down, call disable function
    }
}
