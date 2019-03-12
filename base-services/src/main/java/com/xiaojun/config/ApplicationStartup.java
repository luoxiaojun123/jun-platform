package com.xiaojun.config;

import com.aspose.words.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

/**
 * 加载 aspose license
 */
@Slf4j
public class ApplicationStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            InputStream isWords = new ClassPathResource("templatefile/signature/license.xml").getInputStream();
            License wordsLicense = new License();
            wordsLicense.setLicense(isWords);
        } catch (Exception e) {
            log.error("aspose words 组件的注册文件装载异常", e);
        }

        try {
            InputStream isCells = new ClassPathResource("templatefile/signature/license.xml").getInputStream();
            com.aspose.cells.License cellsLicense = new com.aspose.cells.License();
            cellsLicense.setLicense(isCells);
        } catch (Exception e) {
            log.error("aspose cells 组件的注册文件装载异常", e);
        }
    }
}
