package br.com.jsonreadinsertmongo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Embeddable
public class Company implements Serializable {

    @Column(name = "company_name")
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {
    }
}
