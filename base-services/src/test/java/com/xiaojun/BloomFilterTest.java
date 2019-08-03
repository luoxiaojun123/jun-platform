package com.xiaojun;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author long.luo
 * @date 2019/3/14 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class BloomFilterTest {

    private static int size = 1000000;

    private static BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), size);


    @Test
    public void test() {

        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }

        long startTime = System.nanoTime();

        if (bloomFilter.mightContain(29999)) {
            System.out.println("命中了");
        }

        long endTime = System.nanoTime();

        System.out.println("程序运行时间： " + (endTime - startTime) + "纳秒");
    }

    @Test
    public void test2() {
        for (int i = 0; i < size; i++) {
            bloomFilter.put(i);
        }
        List<Integer> list = new ArrayList<Integer>(1000);
        // 故意取10000个不在过滤器里的值，看看有多少个会被认为在过滤器里
        for (int i = size + 10000; i < size + 20000; i++) {
            if (bloomFilter.mightContain(i)) {
                System.out.println("误判值" + i);
                list.add(i);
            }
        }
        System.out.println("误判的数量：" + list.size());
    }

}
