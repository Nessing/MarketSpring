package ru.nessing.orders.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
public class Cart {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id")
    private UUID id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items;

    @Column(name = "price")
    private int price;

    @Column
    private long userId;

    public void add(ru.nessing.orders.models.CartItem cartItem) {
        for (ru.nessing.orders.models.CartItem ci : this.items) {
            if (ci.getProductId() == cartItem.getProductId()) {
                ci.incrementQuantity(cartItem.getQuantity());
                recalculate();
                return;
            }
        }

        this.items.add(cartItem);
        cartItem.setCart(this);
        recalculate();
    }

    public void recalculate() {
        price = 0;
        for (ru.nessing.orders.models.CartItem ci : items) {
            price += ci.getPrice();
        }
    }

    public void clear() {
        for (ru.nessing.orders.models.CartItem ci : items) {
            ci.setCart(null);
        }
        items.clear();
        recalculate();
    }

    public ru.nessing.orders.models.CartItem getItemByProductId(Long productId) {
        for (ru.nessing.orders.models.CartItem ci : items) {
            if (ci.getProductId() == productId) {
                return ci;
            }
        }
        return null;
    }

    public void merge(Cart another) {
        for (ru.nessing.orders.models.CartItem ci : another.items) {
            add(ci);
        }
    }

}
