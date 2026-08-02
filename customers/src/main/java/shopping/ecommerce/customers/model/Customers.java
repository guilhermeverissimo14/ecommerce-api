package shopping.ecommerce.customers.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(length = 100)
    private String street;

    @Column(name = "number", length = 10)
    private String number;

    @Column(length = 100)
    private String neighborhood;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String phone;

}
