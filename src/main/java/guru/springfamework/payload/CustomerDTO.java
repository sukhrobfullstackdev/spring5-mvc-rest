package guru.springfamework.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstName;
    @ApiModelProperty(required = true )
    private String lastName;
    @JsonProperty("customer_url")
    private String customerURL;
}
