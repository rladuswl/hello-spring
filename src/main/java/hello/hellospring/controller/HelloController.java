package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


// 기능을 찾는 능력이 중요하다. 메뉴얼에서 검색을 잘 할 수 있어야 한다.
// spring.io 홈페이지 들어가기
// 프로젝트 -> 스프링 부트 -> 버전의 Reference Documentation


@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }


    // @RequestParam(value = "name") 의 의미는
    // 괄호 안에서 컨트롤+p 를 눌러보면 value와 required를 써야함을 알 수 있다.
    // required 기본값은 true 이므로 "name" 에 값이 들어가야 한다.
    // required = false 이면 안 넣어도 된다.
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    // API
    // html 로 내릴 것이냐.. API 로 내릴 것이냐..

    // @ResponseBody
    // http에서 헤더와 바디 영역이 있다. 그 중 바디에 이 내용을 직접 넣겠다.


    // < return이 문장 >
    // 뷰 같은게 없고 클라이언트에게 바로 내려간다. 브라우저에서 뷰를 통하지 않고 return의 문장(?) 바로 볼 수 있다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    // < return이 문장이 아닐 때 >
    // JSON으로 반환하는게 기본이다. {키:값}
    // viewResolver 없고 http에 데이터 그냥 넘겨야 한다.
    // 그런데 return이 문자가 아니고 객체일 땐 기본이 JSON 방식으로 데이터를 http에 반환한다.
    // HttpMessageConverter가 동작한다
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

    // 즉, 문자일 땐 HttpMessageConverter의 JsonConverter, 정확히 말하면 MappingJackson2HttpMessageConverter가 동작. (잭슨 라이브러리로 제이슨으로 바꿈. 잭슨이 기본 탑재되어있음.)
    // 객체일 땐 HttpMessageConverter 의 StringHttpMessageConverter가 동작.

    // getter, setter 자동 생성 단축키 -> Alt + Insert

}
