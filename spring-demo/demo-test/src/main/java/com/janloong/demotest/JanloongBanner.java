/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 : Copyright (c) 2019  All Rights Reserved.
 : ProjectName: SpringCloud
 : FileName: JanloongBanner.java
 : Author: janloongdoo@gmail.com
 : Date: 19-5-22 上午11:00
 : LastModify: 19-5-22 上午11:00
 :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

package com.janloong.demotest;


import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

/**
 * @author <a href ="mailto: janloongdoo@gmail.com">Janloong</a>
 * @date 2019-05-22 11:00
 */
//@Configuration
public class JanloongBanner implements Banner {


    private static final String[] BANNER2 = {"",
            "  .   ____          _            __ _ _",
            " /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\",
            "( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\",
            " \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )",
            "  '  |____| .__|_| |_|_| |_\\__, | / / / /",
            " =========|_|==============|___/=/_/_/_/"
    };

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

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT,
                AnsiColor.DEFAULT, padding.toString(), AnsiStyle.FAINT, version));
        printStream.println();
    }
}
