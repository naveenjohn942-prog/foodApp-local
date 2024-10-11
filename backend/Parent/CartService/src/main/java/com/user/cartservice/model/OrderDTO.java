package com.user.cartservice.model;

import com.user.cartservice.model.dto.OrderItemDTO;
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
