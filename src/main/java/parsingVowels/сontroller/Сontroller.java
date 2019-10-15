package parsingVowels.сontroller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import parsingVowels.Parsing;
import parsingVowels.SimbolForFront;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Сontroller {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping(value = "/data", produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<SimbolForFront> Parsing(@RequestBody ArrayList<ArrayList<SimbolForFront>> objectJson) {

        //return Parsing.parsingVowels(val,Integer.parseInt(1));
        return null;
    }

    @GetMapping(value = "/analyze",produces= MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<List<SimbolForFront>> Parsing(@RequestParam(required = false) String val) {

        return  Parsing.splitTheStringIntoWords(val);

    }

}
