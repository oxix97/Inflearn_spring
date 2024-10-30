package hello.selector;


import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class BeanImportSelector implements ImportSelector {

    /*
     * 설정 정보를 동적으로 선택할 수 있게 해주는 ImportSelector를 구현
     * 여기에 설정된 정보로 사용할 클래스를 동적으로 프로그래밍
     */

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // MEMO : 해당 부분에 조건문을 걸어 동적으로 분기처리 가능

        return new String[]{"hello.selector.HelloConfig"};
    }
}
