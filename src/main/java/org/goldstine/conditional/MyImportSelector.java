package org.goldstine.conditional;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

//自定义逻辑  返回需要导入的组件
public class MyImportSelector implements ImportSelector {
    /**
     * 返回值，就是导入到容器中的组件全类名
     * @param annotationMetadata  :当前标注@Import注解的类的所有注解信息
     * @return
     */
    public String[] selectImports(AnnotationMetadata annotationMetadata) {

//        annotationMetadata.get
//        return new String[0];//可以返回一个空数组，但是不能返回null
        return new String[]{"org.goldstine.bean.Blue","org.goldstine.bean.Yellow"};
    }

}
