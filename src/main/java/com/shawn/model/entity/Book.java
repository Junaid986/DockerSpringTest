package com.shawn.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author Xiaoyue Xiao
 */
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Component
public class Book implements Serializable {

    private static final long serialVersionUID = 8604990093149376515L;

    private Long id;
    private String name;
    private String author;
    private Double price;
    private String topic;
    private Date publishDate;

    private Long bookStoreId;

}
