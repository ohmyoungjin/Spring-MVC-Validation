package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Item {

    //@NotBlank : 빈값 + 공백만 있는 경우를 허용하지 않는다.
    //@NotBlank(message="{}") 안에 내가 원하는 기본 메세지 설정도 가능하다.
    //@NotNull : null 을 허용하지 않는다.
    //@Range(min = 1000, max = 1000000) : 범위 안의 값이어야 한다.
    //@Max(9999) : 최대 9999까지만 허용한다.

    private Long id;
    @NotBlank(message = "공백XX")
    private String itemName;
    @NotNull
    @Range(min = 1000, max = 10000)
    private Integer price;
    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
