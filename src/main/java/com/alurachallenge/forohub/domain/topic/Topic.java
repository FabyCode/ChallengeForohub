package com.alurachallenge.forohub.domain.topic;

import com.alurachallenge.forohub.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity(name="Topic")
@Table(name="topics")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String message;
    private LocalDateTime date;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name= "author_id")
    private User author;
    private String course;
    private boolean active;

    public Topic(Long id, String title, String message, LocalDateTime date, Status status, User usuario, String curso) {
        this.id=id;
        this.title=title;
        this.message=message;
        this.date = date;
        this.date=LocalDateTime.now();
        this.status=status;
        this.author=usuario;
        this.course=curso;
    }

    public void topicUpdate(TopicUpdateDTO topicUpdateDTO) {
        if (topicUpdateDTO.title() !=null){
            this.title= topicUpdateDTO.title();
        }
        if (topicUpdateDTO.message() != null){
            this.message= topicUpdateDTO.message();
        }
        if (topicUpdateDTO.status() != null){
            this.status= topicUpdateDTO.status();
        }
        if (topicUpdateDTO.curso() != null){
            this.course= topicUpdateDTO.curso();
        }
    }
    public void diactivateTopic(){
        this.active=false;
    }
}
