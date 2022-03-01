package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
//@Setter
public class Orders implements Model{
    private Integer id;
    private Boolean driver;
    private Float bill;
    private Float add_bill;
    private Auto id_auto;
    private User id_user;
    private Timestamp order_date;
    private Timestamp return_date;
    private String denial;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDriver(Boolean driver) {
        this.driver = driver;
    }

    public void setBill(Float bill) {
        if (bill < 0){
            throw new IllegalArgumentException("Negative value exception");
        }
        this.bill = bill;
    }

    public void setAdd_bill(Float add_bill) {
        if (add_bill < 0){
            throw new IllegalArgumentException("Negative value exception");
        }
        this.add_bill = add_bill;
    }

    public void setDenial(String denial) {
        this.denial = denial;
    }

    public void setId_auto(Auto id_auto) {
        this.id_auto = id_auto;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }

    public void setReturn_date(Timestamp return_date) {
        this.return_date = return_date;
    }
}
