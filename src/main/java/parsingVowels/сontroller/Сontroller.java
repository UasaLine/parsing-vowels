package parsingVowels.сontroller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import parsingVowels.Parsing;
import parsingVowels.SimbolForFront;

import java.util.List;

@Controller
public class Сontroller {

    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping(value = "/data",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SimbolForFront> Parsing(@RequestParam(required = false) String val) {

        return Parsing.parsingVowels(val);
    }

}
