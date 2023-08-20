package crud.jdbc.entity;

public class Company extends BaseEntity {
    private String name;
    private int employeeQuantity;
    private int companyPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeQuantity() {
        return employeeQuantity;
    }

    public void setEmployeeQuantity(int employeeQuantity) {
        this.employeeQuantity = employeeQuantity;
    }

    public int getCompanyPrice() {
        return companyPrice;
    }

    public void setCompanyPrice(int companyPrice) {
        this.companyPrice = companyPrice;
    }
}
