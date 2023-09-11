package module.dto;

import module.entity.Account;

public record OperationRequestDto(String comment, double amount, Account receiverUserId, Account senderUserId) {
}
