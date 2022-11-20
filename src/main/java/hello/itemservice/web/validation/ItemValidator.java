package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

//의존성 주입으로 사용 되기 때문에 @Componant를 붙여줘야 한다.
@Component
public class ItemValidator implements Validator {

    //컨트롤러에서 검증로직이 많아지면 컨트롤러가 처리 하는 부분이 너무 크다 SRP , OCP 에 위배 됨.
    //ISP? 방식을 사용해서 따로 소스를 분리 하자

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        if (item.getPrice() == null || item.getPrice() < 1000 ||
                item.getPrice() > 1000000) {
            errors.rejectValue("price", "range", new Object[]{1000, 1000000},
                    null);
        }
        if (item.getQuantity() == null || item.getQuantity() > 10000) {
            errors.rejectValue("quantity", "max", new Object[]{9999}, null);
        }
        //특정 필드 예외가 아닌 전체 예외
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultPrice = item.getPrice() * item.getQuantity();
            if (resultPrice < 10000) {
                errors.reject("totalPriceMin", new Object[]{10000,
                        resultPrice}, null);
            }
        }
    }
}
