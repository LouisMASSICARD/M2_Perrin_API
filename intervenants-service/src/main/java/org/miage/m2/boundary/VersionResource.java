package org.miage.m2.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class VersionResource {

    private final String version;

    @Autowired
    public VersionResource(@Value("${version}") String value) {
        this.version = value;
    }

    @GetMapping(value = "/version")
    String getVersion() {
        return "Version: " + this.version;
    }
}
