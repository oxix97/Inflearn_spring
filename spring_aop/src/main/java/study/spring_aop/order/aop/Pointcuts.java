package study.spring_aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {

    @Pointcut("execution(* study.spring_aop.order..*(..))")
    public void allOrders() {} // -> pointcut signature : 메서드 이름과 파라미터 => 포인트컷 시그니처

    //클래스 이름 패턴이 *Service.java인 경우
    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}


    @Pointcut("allOrders() && allService()")
    public void orderAndService(){}
}
