package com.github.nexus.config;

import com.github.nexus.config.util.PathUtil;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.nio.file.Path;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(factoryMethod = "create")
public class PublicKey {

    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(PathAdapter.class)
    private final Path path;

    @XmlSchemaType(name = "anyURI")
    private String value;

    public PublicKey(Path path, String value) {
        this.path = path;
        this.value = PathUtil.readData(path, value);
    }

    public PublicKey() {
        this(null, null);
    }

    private static PublicKey create() {
        return new PublicKey(null, null);
    }

    public Path getPath() {
        return path;
    }

    public String getValue() {
        if (this.value == null) {
            this.value = PathUtil.readData(path, null);
        }

        return value;
    }

}