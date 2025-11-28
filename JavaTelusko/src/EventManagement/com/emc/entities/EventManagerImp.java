package EventManagement.com.emc.entities;

public class EventManagerImp implements EventManager{
    @Override
    public Event create(Long id) {
        return new Event(id,"Priya Lal Event Manager","Started the event","Today","Tomorrow", true);
    }
}
