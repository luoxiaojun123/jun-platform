package xiaojun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import xiaojun.entity.Person;
import xiaojun.utils.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author long.luo
 * @date 2018/8/7 15:12
 */
@Controller
public class ExcelController {

    @RequestMapping("export")
    public void export(HttpServletResponse response){

        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞","1",new Date());
        Person person2 = new Person("娜美","2", new Date());
        Person person3 = new Person("索隆","1", new Date());
        Person person4 = new Person("小狸猫","1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        ExcelUtil.exportExcel(personList,"花名册","草帽一伙",Person.class,"test.xls",response);
    }

    @RequestMapping("importExcel")
    @ResponseBody
    public String importExcel(MultipartFile file){
        //String filePath = "C:\\Users\\long.luo\\Downloads\\test.xls";
        //解析excel，
        List<Person> personList = ExcelUtil.importExcel(file,0,1,Person.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");
        personList.stream().forEach(person -> System.out.println(person.toString()));
        //TODO 保存数据库
        return "success";
    }

}
