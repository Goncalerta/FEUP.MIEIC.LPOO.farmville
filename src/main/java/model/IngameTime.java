package model;

public class IngameTime {
    private int minute;
    private int hour;
    private int day;

    public IngameTime(int day, int hour, int minute) {
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }

    public IngameTime() {
        this(0,0,0);
    }

    public void advance(int minutes) {
        this.minute += minutes;
        int left = this.minute / 60;
        this.minute %= 60;

        this.hour += left;
        left = this.hour / 24;
        this.hour %= 24;

        this.day += left;
    }

    public int getMinute() {
        return minute;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    @Override
    public String toString() {
        return String.format("Day %03d  %02d:%02d", this.day, this.hour, this.minute);
    }

}