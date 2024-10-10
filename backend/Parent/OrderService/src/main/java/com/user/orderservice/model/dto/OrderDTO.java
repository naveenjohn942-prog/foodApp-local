package com.user.orderservice.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class OrderDTO {
    private Long userId;
    private List<OrderItemDTO> orderItems;
}
