package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//사용하기도 번거롭고 , 제공하는 기능이 빈약하므로 이 기능을 사용하는 것은 추천하지 않는다.
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총 합이 10000원을 넘어야 합니다.")
public class Item_group {

    //@NotBlank : 빈값 + 공백만 있는 경우를 허용하지 않는다.
    //@NotBlank(message="{}") 안에 내가 원하는 기본 메세지 설정도 가능하다.
    //@NotNull : null 을 허용하지 않는다.
    //@Range(min = 1000, max = 1000000) : 범위 안의 값이어야 한다.
    //@Max(9999) : 최대 9999까지만 허용한다.
    @NotNull(groups = UpdateCheck.class)
    private Long id;
    @NotBlank(groups = {SaveCheck.class, UpdateCheck.class})
    private String itemName;
    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Range(min = 1000, max = 10000)
    private Integer price;
    @NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    @Max(value = 9999, groups = {SaveCheck.class})
    private Integer quantity;

    public Item_group() {
    }

    public Item_group(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
