package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
//@Setter
public class User implements Model{
    private Integer id;
    private String name;
    private String surname;
    private String patron;
    private String ipn;
    private String phone;
    private final static String REGEX_PHONE = "\\+([0-9])+";

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        if (!name.matches(REGEX)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.name = name;
    }

    public void setSurname(String surname) {
        if (!surname.matches(REGEX)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.surname = surname;
    }

    public void setPatron(String patron) {
        if (!patron.matches(REGEX)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.patron = patron;
    }

    public void setIpn(String ipn) {
        if (ipn.length() != 10){
            throw new IllegalStateException("IPN length must be 10 symbols");
        }
        if (!ipn.matches(REGEX_IPN)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.ipn = ipn;
    }

    public void setPhone(String phone) {
        if (ipn.length() != 13){
            throw new IllegalStateException("Phone length must be 13 symbols");
        }
        if (!ipn.matches(REGEX_PHONE)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.phone = phone;
    }
}
