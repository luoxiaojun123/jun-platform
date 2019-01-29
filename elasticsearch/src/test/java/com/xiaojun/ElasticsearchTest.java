package com.xiaojun;

import com.xiaojun.repository.Article;
import com.xiaojun.repository.ArticleSearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.lucene.search.function.FieldValueFactorFunction;
import org.elasticsearch.common.lucene.search.function.FiltersFunctionScoreQuery;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StringUtils;

/**
 * @author long.luo
 * @date 2018/9/26 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ElasticsearchApplication.class)
@Slf4j
public class ElasticsearchTest {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            Article article = new Article();
            article.setId(i);
            article.setTitle("商品" + System.currentTimeMillis());
            article.setContent("这是一个测试商品");
            articleSearchRepository.save(article);
        }
    }

    @Test
    public void testQuery() {
        Pageable pageable = PageRequest.of(0, 20);
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //分页
        nativeSearchQueryBuilder.withPageable(pageable);
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders
                .functionScoreQuery(QueryBuilders.multiMatchQuery("商品", "title", "content"),
                        ScoreFunctionBuilders.fieldValueFactorFunction("id")
                                .modifier(FieldValueFactorFunction.Modifier.LOG1P)
                                .factor(3f))
                .scoreMode(FiltersFunctionScoreQuery.ScoreMode.SUM)
                .maxBoost(3f);

        nativeSearchQueryBuilder.withQuery(functionScoreQueryBuilder);
        nativeSearchQueryBuilder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));

        NativeSearchQuery searchQuery = nativeSearchQueryBuilder.build();

        Page<Article> articlePage = articleSearchRepository.search(searchQuery);

        articlePage.forEach(article -> System.out.println(article.getId() + "---" + article.getTitle()));
    }

    /**
     * and and and ( or or) 格式
     */
    private QueryBuilder getQueryBuilder(String areaType, long startTimeMillis, long endTimeMillis, String keyword) {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(areaType)) {
            queryBuilder.must(QueryBuilders.matchPhraseQuery("areaType", areaType));
        }
        queryBuilder.must(QueryBuilders.rangeQuery("postTimeMillis").gte(startTimeMillis).lte(endTimeMillis));

        BoolQueryBuilder queryBuilder2 = QueryBuilders.boolQuery();
        if (!StringUtils.isEmpty(keyword)) {
            String[] keywordSplit = keyword.split(",");
            for (String key : keywordSplit) {
                queryBuilder2.should(QueryBuilders.matchPhraseQuery("title", key));
            }
        }
        queryBuilder.must(queryBuilder2);
        return queryBuilder;
    }

}
