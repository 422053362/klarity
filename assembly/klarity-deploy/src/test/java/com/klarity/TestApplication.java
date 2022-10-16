package com.klarity;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.royee.common", "com.klarity"})
public class TestApplication extends SpringBootServletInitializer {
}
