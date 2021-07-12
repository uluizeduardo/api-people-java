package project.person.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.person.api.enuns.PhoneType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDTO {

    private Long id;
    
    @Enumerated(EnumType.STRING)
    private PhoneType type;

    //Fazendo a vallidação dos dados
    @NotEmpty
    @Size(min = 13, max = 14)
    private String number;
}
