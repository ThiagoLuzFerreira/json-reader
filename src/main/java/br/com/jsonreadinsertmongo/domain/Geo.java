package br.com.jsonreadinsertmongo.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class Geo implements Serializable {

    private String lat;
    private String lng;

    public Geo() {
    }
}
