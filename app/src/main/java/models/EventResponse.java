package models;

import java.util.List;

/**
 * Created by chhavi on 10/6/15.
 */
public class EventResponse {

    public List<EventModel> getEvents() {
        return events;
    }

    public void setEvents(List<EventModel> events) {
        this.events = events;
    }

    private List<EventModel> events;


    public class EventModel{

        private String date;
        private String time;
        private String venue;
        private String status;
        private int seats;

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        private String contact;
        private String duration;

        public int getEid() {
            return Eid;
        }

        public void setEid(int eid) {
            Eid = eid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getVenue() {
            return venue;
        }

        public void setVenue(String venue) {
            this.venue = venue;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getSeats() {
            return seats;
        }

        public void setSeats(int seats) {
            this.seats = seats;
        }

        private int Eid;

        private String name;

        private String description;



    }
}
