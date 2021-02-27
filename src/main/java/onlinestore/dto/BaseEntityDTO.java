package onlinestore.dto;

public class BaseEntityDTO {

    private Long id;

    public BaseEntityDTO(Long id) {
        this.id = id;
    }

    public BaseEntityDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
