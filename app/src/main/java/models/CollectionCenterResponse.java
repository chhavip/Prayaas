package models;

import java.util.List;

/**
 * Created by shikharkhetan on 7/7/15.
 */
public class CollectionCenterResponse {


    public List<CollectionCenter> getCollectionCenters() {
        return centers;
    }

    public void setEvents(List<CollectionCenter> centers) {
        this.centers = centers;
    }

    private List<CollectionCenter> centers;




    public class CollectionCenter   {

        public CollectionCenter(String name, String address, String contact, String days_active, String timings, String collection_status) {
            this.name = name;
            this.address = address;
            this.contact = contact;
            this.days_active = days_active;
            this.timings = timings;
            this.collection_status = collection_status;
        }

        String name;
        String address;
        String contact;
        String days_active;
        String timings;
        String collection_status;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getDays_active() {
            return days_active;
        }

        public void setDays_active(String days_active) {
            this.days_active = days_active;
        }

        public String getTimings() {
            return timings;
        }

        public void setTimings(String timings) {
            this.timings = timings;
        }

        public String getCollection_status() {
            return collection_status;
        }

        public void setCollection_status(String collection_status) {
            this.collection_status = collection_status;
        }
    }
}

