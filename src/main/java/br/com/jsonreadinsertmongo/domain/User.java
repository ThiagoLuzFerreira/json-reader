package br.com.jsonreadinsertmongo.domain;



import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Embedded;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Document(collection = "user_any")
public class User implements Serializable {

    @Id
    private String id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    @Embedded
    private Map<String, Object> address;
    @Embedded
    private Map<String, Object> company;

    public User() {
    }
}
