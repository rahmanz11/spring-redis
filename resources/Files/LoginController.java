package ssf.miniproject.ssfminiproject.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import ssf.miniproject.ssfminiproject.repositories.RedisRepository;

@Controller
@RequestMapping(path={"/"})
public class LoginController {

    // @Autowired
    // @Qualifier("redislab")
    // private RedisTemplate<String, String> template;

    @Autowired
    private RedisRepository repository;

    //Login Page Controller
    @GetMapping(path={"/login"})
    public String loginPrompt(Model model) {

        return "login";
    }

	//Username Entered
    @PostMapping(path={"/login2"})
    public String postLogin(@RequestBody MultiValueMap<String, String> form, Model model) {
        
        String username = form.getFirst("username");
        List<String> values = repository.get(username);

		
        
        model.addAttribute("username", username);
        model.addAttribute("symbol", values);
        return "login2";
    }

    @PostMapping(path={"/login3"})
    public String postLogin2(@RequestBody MultiValueMap<String, String> form, Model model) {
        
        String username = form.getFirst("username");
        String value = form.getFirst("list");
        
        repository.add(username, value);
		
        model.addAttribute("username", username);
        model.addAttribute("symbol", value);
        return "login2";
    }

    
    // @PostMapping(path={"/login2"})
    // public String postLogin3(@RequestBody MultiValueMap<String, String> form, Model model) {
        
    //     String username = form.getFirst("username");
    //     String value = form.getFirst("list");
        
    //     repository.remove(username, value);
		
    //     model.addAttribute("username", username);
    //     model.addAttribute("symbol", value);
    //     return "login2";
    // }
    

    

}