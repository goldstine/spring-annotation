package org.goldstine.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BookDao {
    //名字默认是类名首字母小写

    private String label="1";

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "BookDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
