package indi.yume.tools.avocado.model;

import android.os.Parcel;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;

import java.util.Calendar;

/**
 * Created by yume on 16-5-4.
 */
public class ImmutableDayDate extends DayDate {

    public ImmutableDayDate() {
        super();
    }

    public ImmutableDayDate(int year,
                   @IntRange(from = 0, to = 11) int month,
                   int day) {
        super(year, month, day);
    }

    public ImmutableDayDate(DayDate day){
        super(day);
    }

    public ImmutableDayDate(Calendar day){
        super(day);
    }

    public static ImmutableDayDate of(DayDate dayDate) {
        ImmutableDayDate immutableDayDate = new ImmutableDayDate();
        immutableDayDate.setValue(dayDate);
        return immutableDayDate;
    }

    @Override
    public void setDay(@Nullable Integer day) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMonth(@Nullable @IntRange(from = 0, to = 11) Integer month) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setYear(@Nullable Integer year) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(long timeInMillis) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(DayDate date) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setValue(int year, @IntRange(from = 0, to = 11) int month, int day) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addDay(int dayOffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addMonth(int monthOffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addYear(int yearOffset) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.year);
        dest.writeInt(this.month);
        dest.writeInt(this.day);
        dest.writeSerializable(this.calendar);
    }

    protected ImmutableDayDate(Parcel in) {
        super(in);
        this.year = in.readInt();
        this.month = in.readInt();
        this.day = in.readInt();
        this.calendar = (Calendar) in.readSerializable();
    }

    public static final Creator<ImmutableDayDate> CREATOR = new Creator<ImmutableDayDate>() {
        @Override
        public ImmutableDayDate createFromParcel(Parcel source) {
            return new ImmutableDayDate(source);
        }

        @Override
        public ImmutableDayDate[] newArray(int size) {
            return new ImmutableDayDate[size];
        }
    };
}
