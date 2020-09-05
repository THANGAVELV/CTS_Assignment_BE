package com.rabobank.pojo.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "records")
public class Records {
    @XmlAnyElement(lax = true)
    private List records;
}
