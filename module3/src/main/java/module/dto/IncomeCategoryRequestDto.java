package module.dto;

public record IncomeCategoryRequestDto(String incomeType, double incomeAmount) {
    private static Long userId;

    public static Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        IncomeCategoryRequestDto.userId = userId;
    }

}
