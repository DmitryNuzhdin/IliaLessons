package restExample;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/")
public class SimpleController {

    @GetMapping(value = "/testPath")
    TestDTO testPath(){
        return new TestDTO(5, "qwe");
    }

    @GetMapping(value = "/sum")
    SumDTO sum(@RequestParam(name = "a") int a, @RequestParam(name = "b") int b){
        return new SumDTO(a, b, a + b);
    }

    @GetMapping(value = "/sumPath/{a}/{b}")
    SumDTO sumPath(@PathVariable(value = "a") Integer a, @PathVariable(value = "b") Integer b){
        return new SumDTO(a, b, a + b);
    }

    @PostMapping(value = "/sumBody")
    SumDTO sumBody(@RequestBody SumDTO s){
        s.setSum(s.getA() + s.getB());
        return s;
    }
}
