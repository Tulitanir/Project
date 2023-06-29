package edu.nechaev.project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Data
@Builder
public class MemberTraining {
    @Id
    private long id;
    private long training, member;
}
