package com.dgomezt.practicaintegradora.entities;

import com.dgomezt.practicaintegradora.entities.helpers.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", foreignKey = @ForeignKey(name = "FK_orders_clientId"))
    private Client client;

    @OneToMany(mappedBy = "orderId")
    private Set<ProductOrderDetails> productOrderDetailses = new LinkedHashSet<>();

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_state_id", foreignKey = @ForeignKey(name = "FK_orders_orderStateId"))
    private OrderState orderState;

    @ManyToOne
    @JoinColumn(name = "user_admin_id", foreignKey = @ForeignKey(name = "FK_orders_userAdminId"))
    private UserAdmin userAdmin;
}