package shopping.eccomerce.orders.model;

import lombok.Data;
import shopping.eccomerce.orders.model.enums.PaymentType;

@Data
public class PaymentData {
    private String data;
    private PaymentType paymentType;
}
