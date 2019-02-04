package br.com.rybyalarmmanager.entities.task;


import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static br.com.rybyalarmmanager.entities.task.TypeOfTask.NON_REPETITIVE;
import static br.com.rybyalarmmanager.entities.task.TypeOfTask.REPETITIVE;

@Retention(RetentionPolicy.SOURCE)
@IntDef({REPETITIVE, NON_REPETITIVE})
public @interface TypeOfTask {
    int REPETITIVE = 0, NON_REPETITIVE = 1;
}
