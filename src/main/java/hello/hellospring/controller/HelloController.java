package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!");
        return "hello"; //resources > templates 에 동일 이름으로 가서 리턴 (뷰 리졸버가 동작)
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name",required = false) String name,Model model){
        //  ctrl + p 옵션보기 , required를 false로 하면 name 값이 필수로 있지 않아도 화면 반환
        model.addAttribute("name",name);
        return "hello-template";
    }
    //위에꺼와 아래의 차이는 위에는 페이지 소스를 보면 html 코드 안에 들어가고 아래는 태그없이 resposebody로 바로 내용을 그대로 넣어줌
    @GetMapping("hello-string")
    @ResponseBody // return 값을 그대로 내려줌
    public String helloString(@RequestParam("name")String name){
        return "hello " + name; // "hello spring"
    }
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //ctrl + shift + enter : 자동완성
        hello.setName(name);
        return hello; //JSON 으로 반환
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}

