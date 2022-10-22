package tn.enicarthage.projectBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import tn.enicarthage.projectBackend.enumeration.Status;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;




@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)//can not have more than one ipaddress (by number) , throws
    @NotEmpty(message = "ip address can not be empty or null")//validation
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;

}
