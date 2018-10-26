package com.xiaojun;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author long.luo
 * @date 2018/10/26 15:41
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Integer> {


}
