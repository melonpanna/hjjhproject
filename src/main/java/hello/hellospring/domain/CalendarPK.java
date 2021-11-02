package hello.hellospring.domain;

import lombok.Builder;

import java.io.Serializable;
import java.util.Objects;

//@Builder
public class CalendarPK implements Serializable {

    private int year;
    private int month;
    private int day;

    public CalendarPK(){}

    @Override
    public boolean equals(Object o) {
        CalendarPK cPK=(CalendarPK)o;
        if (year == cPK.year && month == cPK.month && day == cPK.day) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hash(year, month, day);
    }
}
