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
        model.addAttribute("data", "hello!!!");
        return "hello";
    }
    /**
     * 정적컨텐츠
     * html파일을 그대로 넘겨준다.
     */

    /**
     * MVC 방식
     *
     * @param name
     * @param model
     * @return
     */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * API방식
     * ResponsBody - http에서 바디부에 이 데이터를 내가 직접 넣어주겠다.
     * 데이터를 그대로 내려준다.
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    /**
     *  ResponsBody가 있으면 객체를 반환이 되면 기본 디폴트가 json방식으로 데이터를 만들어서 http응답에 반환하겠다.
     *  1. viewResolver대신 HttpMessageConverter 동작
     *  2-1. 단순문자일떄 StringConverter 동작
     *  2-2. 객체일때 JsonConverter 동작 - jackson
     * @param name
     * @return
     */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
