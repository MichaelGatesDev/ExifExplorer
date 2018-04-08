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


package com.michaelgatesdev.ExifExplorer.util;

import com.michaelgatesdev.ExifExplorer.Main;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ResourceBundle;

public class FileUtil
{ // ============================================================================================================================================ \\
    
    private static final Logger logger = Logger.getLogger(FileUtil.class);
    
    // ============================================================================================================================================ \\
    
    
    /**
     * Creates a {@link File} directory.
     *
     * @param destDir the directory which will parent the created directory
     * @param name    the name of the directory
     * @param log     log the result to console
     *
     * @return created directory
     */
    public static File createDirectory(File destDir, String name, boolean log)
    {
        ResourceBundle locale = Main.getInstance().getLocale();
        
        if (destDir == null || !destDir.exists())
        {
            if (log)
            {
                logger.error(String.format(locale.getString("File.Directory.DestinationNonexistent"), destDir));
            }
            return null;
        }
        
        File folder = new File(destDir, "/" + name + "/");
        if (!folder.exists())
        {
            boolean result = folder.mkdir();
            
            if (log)
            {
                if (result)
                {
                    logger.info(String.format(locale.getString("File.Directory.Created"), name, destDir.toString()));
                }
                else
                {
                    logger.error(String.format(locale.getString("File.Directory.ErrorCreating"), name, destDir.toString()));
                }
            }
        }
        return folder;
    }
    
    
    // ============================================================================================================================================ \\
}
