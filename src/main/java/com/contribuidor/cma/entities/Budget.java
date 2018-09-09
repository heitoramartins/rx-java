package com.contribuidor.cma.entities;

import com.contribuidor.cma.service.budget.TaxEnum;
import com.contribuidor.cma.util.converter.CustomLocalDateTimeDeserialize;
import com.contribuidor.cma.util.converter.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "budget")
@NoArgsConstructor
@AllArgsConstructor
public class Budget {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Client.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client")
    private Client client;

    @Column(name = "total")
    private BigDecimal total;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserialize.class)
    private LocalDateTime dtInclusion;

    @OneToMany(mappedBy = "budget", targetEntity = Items.class, fetch = FetchType.LAZY)
    private List<Items> items = new ArrayList<>();

    @Transient
    private TaxEnum tax;

    @PrePersist
    private void createDate(){
        dtInclusion = LocalDateTime.now();
    }

    public BigDecimal totalCalculates(Items item){
        items.add(item);
        items.forEach(i ->{
             total.add(i.getProduct().getPrice().multiply(new BigDecimal(i.getQuantity())));
        });
        return total;
    }

}
