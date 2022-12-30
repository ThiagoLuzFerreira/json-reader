package br.com.jsonreadinsertmongo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
public class Company {

    @Column(name = "company_name")
    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {
    }
}
