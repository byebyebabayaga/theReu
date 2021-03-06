package com.lucasverrier.thereu.service;

import com.lucasverrier.thereu.model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingService  implements MeetingApiService {
    private List<Meeting> mMeetingList = new ArrayList<>();

    public List<Meeting> getMeetingList() { return mMeetingList; }

    public void addNewMeeting(Meeting meeting){ mMeetingList.add(meeting); }

    public void deleteMeeting(Meeting meeting){ mMeetingList.remove(meeting); }
}
