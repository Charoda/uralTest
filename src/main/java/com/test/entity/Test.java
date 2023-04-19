package com.test.entity;

import com.test.entity.tasks.ManagerTask;

import java.time.ZonedDateTime;

public class Test {
    public static void main(String[] args) {
    ManagerTask managerTask = ManagerTask.builder()
            .taskName("taskN1")
            .creationDate(ZonedDateTime.now())
            .build();
        System.out.println(managerTask.getCreationDate());
        System.out.println(managerTask);



    }
}
