package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 *  단방향일때는 autowired 안써도 됨.
 * - @Controller, @Service, @Repository + @Autowired = 컴포넌트 스캔: 스프링 빈 자동 등록 방식
 * DI에는 필드주입 setter주입 생성자 주입 방식 3가지가 있음.
 *
 * - @Autowired는 스프링이 관리하는 (bean에 정의된) 코드만 동작을한다.
 *
 * 위 방식중 컨트롤러, 서비스, 리포지토리 같은 정형화 된 코드는 컴포넌트 스캔을 사용합니다.
 * 정형화되지 않거나 상황에 따라 구현 클래스를 변경해야 하면 설정을통해(SpringConfig.java) 스프링빈으로 등록한다.
 */
@Controller
public class MemberController {

    //@Autowired - 필드주입 방식 - 별로안좋은 - 바꿀 수 있는 방법이 없음. 스프링 뜰때만 넣어주고 바꿔치기 할 수 없음.
    private final MemberService memberService;


    // setter주입방식 - final이 아니어야함. - 단점: 누군가가 컨트롤러를 불렀을때 public하게 노출되어있어야한다. - 잘못바꾸면 문제가 생김.
    // 누군가가 memberservice. 하면 아무 곳에서 호출할 수 있다.
//    @Autowired
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }

    //이런 방법이 생성자 주입 방식 - 처음에 애플리케이션이 조립되는 시점에 한번 들어오고 끝.
    // 생성자 주입을 권장함.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
