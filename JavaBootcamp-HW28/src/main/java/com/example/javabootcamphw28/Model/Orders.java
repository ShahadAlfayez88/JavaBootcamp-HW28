package com.example.javabootcamphw28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Quantity field is requierd")
    @Positive
    private Integer quantity;

    private Integer totalPrice;

    @Pattern( regexp = "^New|inProgress|completed$" ,message = "status field only allow input: (new-inProgress-completed)" )
    @NotEmpty(message = "status field is requierd")
    private String status;

    @NotEmpty(message = "status field is requierd")
    private String dateRecevied;

    @ManyToOne
    @JoinColumn(name = "product_id" , referencedColumnName = "id")
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @JsonIgnore
    MyUser myUser;
}
