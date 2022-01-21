package com.example.project5.models;

import java.util.ArrayList;
import java.util.Objects;

public class Member {
    public String name;
    public String surname;
    public ArrayList<MemberSub> subList;

    public Member(String name, String surname, ArrayList<MemberSub> subList) {
        this.name = name;
        this.surname = surname;
        this.subList = subList;
    }

    public Member() {
    }

    @Override
    public String toString() {
        return "Member{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", subList=" + subList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(name, member.name) && Objects.equals(surname, member.surname) && Objects.equals(subList, member.subList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, subList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ArrayList<MemberSub> getSubList() {
        return subList;
    }

    public void setSubList(ArrayList<MemberSub> subList) {
        this.subList = subList;
    }
}
