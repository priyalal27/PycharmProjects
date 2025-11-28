package EventManagement.com.emc.test;

import EventManagement.com.emc.entities.Event;
import EventManagement.com.emc.entities.EventManager;
import EventManagement.com.emc.entities.EventManagerImp;
import EventManagement.com.emc.entities.Organizer;

public class test {

    public static void main(String[] args) {
        Organizer org=new Organizer(232,"GrandLaunch");
        Event eve = new Event(333,"Pri","Software QA","29/04/2020","05/12/2025",false);

        System.out.println(eve.getDescription());

        EventManager em = new EventManagerImp();
        System.out.println(em.create(22222l).getDescription());

    }
}
