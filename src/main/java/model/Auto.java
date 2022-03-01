package model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
//@Setter
public class Auto implements Model{
    private Integer id;
    private String name;
    private String clazz;
    private String mark;
    private Float price;
    private String registration;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        if (!name.matches(REGEX_NUMBERS)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.name = name;
    }

    public void setClazz(String clazz) {
        if (!clazz.matches(REGEX)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.clazz = clazz;
    }

    public void setMark(String mark) {
        if (!name.matches(REGEX)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.mark = mark;
    }

    public void setPrice(Float price) {
        if (price < 0){
            throw new IllegalArgumentException("Negative value exception");
        }
        this.price = price;
    }

    public void setRegistration(String registration) {
        if (!name.matches(REGEX_NUMBERS)){
            throw new IllegalArgumentException("Illegal symbols in string");
        }
        this.registration = registration;
    }
}
