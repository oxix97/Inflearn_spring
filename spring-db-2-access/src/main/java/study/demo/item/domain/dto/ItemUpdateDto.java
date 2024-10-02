package study.demo.item.domain.dto;

public record ItemUpdateDto(
        String itemName,
        Integer price,
        Integer quantity
) {
}

