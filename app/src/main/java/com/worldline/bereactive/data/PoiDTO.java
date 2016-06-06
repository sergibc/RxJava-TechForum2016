package com.worldline.bereactive.data;

import java.util.List;

/**
 * Poi pojo
 */
public class PoiDTO {

    private List<Poi> list;

    public List<Poi> getList() {
        return list;
    }

    public void setList(List<Poi> list) {
        this.list = list;
    }

    public class Poi {

        private int id;

        private String title;

        private String address;

        private String transport;

        private String email;

        private String geocoordinates; // "41.391926,2.165208"

        private String description;

        private String phone;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTransport() {
            return transport;
        }

        public void setTransport(String transport) {
            this.transport = transport;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getGeocoordinates() {
            return geocoordinates;
        }

        public void setGeocoordinates(String geocoordinates) {
            this.geocoordinates = geocoordinates;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Poi{");
            sb.append("id=").append(id);
            sb.append(", title='").append(title).append('\'');
            sb.append(", address='").append(address).append('\'');
            sb.append(", transport='").append(transport).append('\'');
            sb.append(", email='").append(email).append('\'');
            sb.append(", geocoordinates='").append(geocoordinates).append('\'');
            sb.append(", description='").append(description).append('\'');
            sb.append(", phone='").append(phone).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
