package study.demo.item.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Data
@NoArgsConstructor
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "item_name", length = 10)
    private String itemName;

    private Integer price;

    private Integer quantity;

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }



}
