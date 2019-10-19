package com.readinglist;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by David on 2019/10/15.
 */
/*
默认配置下  这个类必须在加了@SpringBootApplication注解的引导启动类的包下或子级目录
否则组件扫描不到
 */
@Controller//组件扫描将本控制器注册到spring上下文里的一个Bean
@RequestMapping("/readingList")//定义处理器映射路径
public class ReadListController {

    private ReadingListRepository readingListRepository;

    //通过构造器注入仓库值
    @Autowired
    public ReadListController(ReadingListRepository readingListRepository) {
        System.out.println("注入了一个readingListRepository："+ JSONObject.toJSONString(readingListRepository));
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public String readerBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList!=null){
            model.addAttribute("books",readingList);//往构造器获得的仓库里注入值，key是books
        }
        return "readingList";
    }
    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader,Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/readingList/{reader}";
    }
}
