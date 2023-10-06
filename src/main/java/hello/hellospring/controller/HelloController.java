package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    /*@getmapping() -> ()의 안은 url의 엔드포인트임, get은 get메소드를 뜻함*/
    @GetMapping("hello") // 웹에서 /hello라고 들어오면 아래를 호출해줌
    public String hello(Model model){
    /*model */
    model.addAttribute("data","hello!!"); // attributeValue의 값이 hello.html의 {data}로 치환되어 웹페이지에 표출됨
    /*기본적으로 스프링은 return 은 resources의 templates의 아래 파일을 찾는다.
    retrun hello는 reources의 templates의 hello.html로 가라는 뜻 */
    return "hello";
    // 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰리졸버(viewResolve)가 화면을 찾아서 처리한다.
  }

  @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
  }


  @GetMapping("hello-string")
  @ResponseBody//responsebody란 바디부에 아래의 데이터를 내가 직접 넣어주겠다라는 뜻
    public String helloString(@RequestParam("name") String name){
        return "hello!!" + name;
  }

  @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
      Hello hello = new Hello();
      hello.setName(name);
      return  hello;
  }

  //단축키 command+N getter setter 프로퍼티 접근 방식
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
