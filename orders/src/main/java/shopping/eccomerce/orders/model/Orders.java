package shopping.eccomerce.orders.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    @Column(name = "customer_code", nullable = false)
    private Long customerCode;

    @Column(name = "date_customers", nullable = false)
    private LocalDateTime dateCustomers;

    @Column(name = "payment_key", columnDefinition = "text")
    private String paymentKey;

    @Column(columnDefinition = "text")
    private String observation;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private OrderStatus status;

    @Column(nullable = false, precision = 16, scale = 2)
    private BigDecimal total;

    @Column(name = "tracking_number", length = 255)
    private String trackingNumber;

    @Column(name = "url_nf", columnDefinition = "text")
    private String urlNf;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersItem> items;

}
