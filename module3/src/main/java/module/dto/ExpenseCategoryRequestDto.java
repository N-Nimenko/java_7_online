package module.dto;

public record ExpenseCategoryRequestDto(String expenseType, double expenseAmount) {
    private static Long userId;

    public static Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        ExpenseCategoryRequestDto.userId = userId;
    }

}
