package com.xiaojun.repository;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @author long.luo
 * @date 2018/10/11 15:58
 */
@Data
@Document(indexName = "es", type = "article")
public class Article {

    @Id
    private Integer id;

    /**
     * 标题
     */
    @Field
    private String title;

    /**
     * 内容
     */
    @Field
    private String content;
}
