package com.lucasverrier.thereu.service;

import android.content.res.Resources;

import com.lucasverrier.thereu.MainActivity;
import com.lucasverrier.thereu.R;
import com.lucasverrier.thereu.model.Meeting;

import org.joda.time.Interval;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterService implements FilterApiService {

    public List<Meeting> placeFilter(List<Meeting> meetingList, String place) {
        List<Meeting> toRemoveList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            if (!meeting.getMeetingPlace().equals(place)) {
                toRemoveList.add(meeting);
            }
        }
        meetingList.removeAll(toRemoveList);
        return meetingList;
    }
    public List<Meeting> dateFilter(List<Meeting> meetingList, String date) {
        Resources res = MainActivity.getContext().getResources();

        LocalDate dateFilter;
        Iterator<Meeting> iterator = meetingList.iterator();
        while(iterator.hasNext()){
            LocalDate meetingDate = iterator.next().getDateTime().toLocalDate();
            if (date.equals(res.getString(R.string.date_spinner_today))){
                dateFilter = LocalDate.now();
                if (!meetingDate.toDateTimeAtStartOfDay().equals(dateFilter.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(res.getString(R.string.date_spinner_tomorrow))){
                dateFilter = LocalDate.now().plusDays(1);
                if (!meetingDate.toDateTimeAtStartOfDay().equals(dateFilter.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(res.getString(R.string.date_spinner_this_week))){
                dateFilter = LocalDate.now().plusWeeks(1);
                LocalDate dateNow = LocalDate.now();
                Interval week = new Interval(dateNow.toDateTimeAtStartOfDay(), dateFilter.toDateTimeAtStartOfDay());
                if (!week.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(res.getString(R.string.date_spinner_next_week))) {
                LocalDate dateAfter = LocalDate.now().plusWeeks(2);
                LocalDate dateBefore = LocalDate.now().plusWeeks(1);
                Interval secondWeek = new Interval(dateBefore.toDateTimeAtStartOfDay(), dateAfter.toDateTimeAtStartOfDay());
                if (!secondWeek.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }else if (date.equals(res.getString(R.string.date_spinner_month))) {
                LocalDate dateNow = LocalDate.now();
                dateFilter = LocalDate.now().plusMonths(1);
                Interval month = new Interval(dateNow.toDateTimeAtStartOfDay(), dateFilter.toDateTimeAtStartOfDay());
                if (!month.contains(meetingDate.toDateTimeAtStartOfDay())) {
                    iterator.remove();
                }
            }
        }
        return meetingList;
    }


}
