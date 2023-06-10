package com.example.secondhomework.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TaskEnum {
    CONTROL_WORK("����������� ������"),
    WORK_ON_LESSON("����� �� �����"),
    PRESENTATION("������"),
    HOMEWORK("������� ������");

    private final String value;
}
