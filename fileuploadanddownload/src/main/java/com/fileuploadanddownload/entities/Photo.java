package com.fileuploadanddownload.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Photo {
    @Id
    private String id;
    private String path;
    private BigInteger anncId;
}
