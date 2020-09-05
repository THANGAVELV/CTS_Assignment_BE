package com.rabobank.pojo.xml;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name = "record")
public class Record {
    @XmlAttribute(name = "reference")
    private long reference;
    @XmlElement(name = "accountNumber")
    private String accountNumber;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "startBalance")
    private String startBalance;
    @XmlElement(name = "mutation")
    private String mutation;
    @XmlElement(name = "endBalance")
    private String endBalance;
}
