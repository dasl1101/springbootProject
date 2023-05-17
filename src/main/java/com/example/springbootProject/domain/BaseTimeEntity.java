package com.example.springbootProject.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //entity 클래스들이 이 클래스를 상속하면 필드들도 칼럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class) //이 클래스에 auditing기능을 포함시킨다
public abstract class BaseTimeEntity {

    @CreatedDate //엔티티가 생성되어 저장될 때 시간이 자동저장된다.
    private LocalDateTime createdDate;

    @LastModifiedDate //조회한 엔티티의 값을 변경할 때 시간이 자동저장된다.
    private LocalDateTime modifiedDate;

}
