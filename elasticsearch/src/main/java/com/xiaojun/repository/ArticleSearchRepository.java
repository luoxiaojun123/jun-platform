package com.xiaojun.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author long.luo
 * @date 2018/10/26 15:41
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Integer> {

    /**
     * 根据标题名称删除文章
     * @param title title
     */
    void deleteArticleByTitle(String title);
}
