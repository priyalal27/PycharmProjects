package EventManagement.com.emc.entities;

import java.util.Date;

public class Event extends EMBase{


        private String description ;

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getStarttime() {
                return starttime;
        }

        public void setStarttime(String starttime) {
                this.starttime = starttime;
        }

        public String getEndtime() {
                return endtime;
        }

        public void setEndtime(String endtime) {
                this.endtime = endtime;
        }

        public boolean isStarted() {
                return started;
        }

        public void setStarted(boolean started) {
                this.started = started;
        }

        private String starttime;
        private String endtime ;
        private boolean started;

        public Event(long id, String name, String description, String starttime, String endtime, boolean started) {
                this.id = id;
                this.name = name;
                this.description = description;
                this.starttime = starttime;
                this.endtime = endtime;
                this.started = started;
        }


}
