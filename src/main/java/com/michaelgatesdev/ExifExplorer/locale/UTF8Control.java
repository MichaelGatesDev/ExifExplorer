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


package com.michaelgatesdev.ExifExplorer.locale;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class UTF8Control extends ResourceBundle.Control
{
    public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IOException
    {
        // The below is a copy of the default implementation.
        String bundleName = toBundleName(baseName, locale);
        String resourceName = toResourceName(bundleName, "properties");
        ResourceBundle bundle = null;
        InputStream stream = null;
        if (reload)
        {
            URL url = loader.getResource(resourceName);
            if (url != null)
            {
                URLConnection connection = url.openConnection();
                if (connection != null)
                {
                    connection.setUseCaches(false);
                    stream = connection.getInputStream();
                }
            }
        }
        else
        {
            stream = loader.getResourceAsStream(resourceName);
        }
        if (stream != null)
        {
            try
            {
                // Only this line is changed to make it to read properties files as UTF-8.
                bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
            }
            finally
            {
                stream.close();
            }
        }
        return bundle;
    }
}