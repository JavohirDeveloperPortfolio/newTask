package uz.general.generaltask.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attachment extends BaseEntity{
    private String name;
    private String originalName;
    private long size;
    private String contentType;
}
