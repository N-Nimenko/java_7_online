package module.dto;

import module.entity.Account;

public record OperationResponseDto(Long id, String comment, double amount, Account receiverUserId, Account senderUserId) {
}
