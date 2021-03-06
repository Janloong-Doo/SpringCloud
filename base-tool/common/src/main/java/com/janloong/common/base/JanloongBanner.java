/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: JanloongBanner.java
 : Author: janloongdoo@gmail.com
 : Date: 19-6-10 下午4:24
 : LastModify: 19-5-22 上午11:55
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.common.base;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @author <a href ="https://blog.janloong.com">Janloong Doo</a>
 * @version 1.0.0
 * @since 2019-05-22 11:00
 */
public class JanloongBanner implements Banner {

    private static final String[] BANNER = {"",
            "      _             _                               ____              ",
            "     | | __ _ _ __ | | ___   ___  _ __   __ _      |  _ \\  ___   ___  ",
            "  _  | |/ _` | '_ \\| |/ _ \\ / _ \\| '_ \\ / _` |_____| | | |/ _ \\ / _ \\ ",
            " | |_| | (_| | | | | | (_) | (_) | | | | (_| |_____| |_| | (_) | (_) |",
            "  \\___/ \\__,_|_| |_|_|\\___/ \\___/|_| |_|\\__, |     |____/ \\___/ \\___/ ",
            "                                        |___/                         "
    };
    private static final String SPRING_BOOT = " :: Spring Boot :: ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass,
                            PrintStream printStream) {
        for (String line : BANNER) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE
                - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }
        String JDKVersion = environment.getProperty("java.version");
        String port = environment.getProperty("server.port");
        port = port == null || "null".equals(port) ? "8080" : port;
        printStream.println(AnsiOutput.toString(AnsiColor.BLUE, SPRING_BOOT, AnsiColor.RED, padding.toString(), AnsiStyle.FAINT, version));
        printStream.println(AnsiOutput.toString(AnsiColor.BLUE, " :: JDK: " + JDKVersion + " ::\n",
                AnsiColor.BLUE, " :: port: " + port + " ::\n"));
        printStream.println();
    }
}
